package com.mcwl.authentication.receive;

import com.alibaba.fastjson.JSONObject;
import com.mcwl.authentication.dao.TokenDao;
import com.mcwl.authentication.entity.FromLoginUser;
import com.mcwl.authentication.entity.User;
import com.mcwl.authentication.send.LoginSend;
import com.mcwl.authentication.utils.JwtToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jerry
 * @date 2018/8/10
 * @description 接收来自login的消息并处理
 */
@Component
@RabbitListener(queues = "mq.login")
public class LoginReceive {

    public static final String SECRET = "Tom";

    private static Logger logger = LoggerFactory.getLogger(LoginReceive.class);

    @Autowired
    private LoginSend loginSend;

    @Autowired
    private TokenDao tokenDao;

    FromLoginUser user;

    Map map = new HashMap();

    @RabbitHandler
    @Async("loginTaskExecutor")
    public void process(String message){
        logger.info("鉴权中心准备发送消息:"+message);
        //转成对象
        user = JSONObject.parseObject(message,FromLoginUser.class);
        //1.将用户数据转为对象属性
        map.put("id", user.getId());
        map.put("username", user.getUsername());
        logger.info("此次传入的用户，用户名称是:"+user.getUsername());
        map.put("auth", user.getAuth());
        //2.调用jwt生成token
        String token = JwtToken.createJWT(map,SECRET);
        logger.info("生成了token:"+token);
        //3.存到redis，并设置过期时间
        tokenDao.set(user.getId(),token,24);
        logger.info("此次插入的key是:"+user.getId());
        logger.info("可以从redis里面取出value是:"+tokenDao.get(user.getId()));
        //4.然后将token返回
        loginSend.send(token);
    }
}
