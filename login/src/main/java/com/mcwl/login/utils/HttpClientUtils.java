package com.mcwl.login.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.concurrent.Future;

/**
 * @author Jerry
 * @date 2018/7/25
 * @description 工具类httpclient去读取rabbitmq消息
 */
@Component
public class HttpClientUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    @Async("returnTaskExecutor")
    public Future<String> httpclientToMq(String mqName, String hostname, int port, String uri) throws IOException {
        String threadName = Thread.currentThread().getName();
        logger.info("=====当前线程名称HttpClient："+threadName+"========");
        HttpHost host = new HttpHost(hostname, port);
        HttpPost httpGet = new HttpPost(uri);
        JSONObject params = new JSONObject();
        params.put("ackmode", "ack_requeue_false");
        params.put("count", 1);
        params.put("encoding", "auto");
        params.put("truncate", 50000);
        params.put("vhost", "/");
        params.put("name", mqName);
        StringEntity stringEntity = new StringEntity(params.toJSONString(), "utf-8");
        httpGet.setEntity(stringEntity);
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(new AuthScope(hostname, port), new UsernamePasswordCredentials("guest", "guest"));
        CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
        HttpResponse response = client.execute(host, httpGet);
        HttpEntity entity = response.getEntity();
        String message = EntityUtils.toString(entity);
        String m = null;
        JSONArray jsonArray = JSONArray.parseArray(message);
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            m = jsonObject.getString("payload");
        }
        return new AsyncResult<String>(m);
    }
}
