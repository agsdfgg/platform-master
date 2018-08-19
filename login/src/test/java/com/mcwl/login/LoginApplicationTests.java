package com.mcwl.login;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testJedis(){

		HashOperations<String,String,String> operations = redisTemplate.opsForHash();
		operations.put("hello","redis","ok");
		System.out.println(operations.size("hello"));
	}

	@Test
	public void testJson(){

		String json = "{\"payload_bytes\":17,\"redelivered\":false,\"exchange\":\"return_exchange\",\"routing_key\":\"mq.return\",\"message_count\":0,\"properties\":{\"priority\":0,\"delivery_mode\":2,\"headers\":{},\"content_encoding\":\"UTF-8\",\"content_type\":\"text/plain\"},\"payload\":\"where is the meal\",\"payload_encoding\":\"string\"}";

		Object jsonObject = JSONObject.parseObject(json).get("payload");

		System.out.println(jsonObject);

	}

	@Test
	public void crypto(){

		String str =  DigestUtils.md5DigestAsHex("123456".getBytes());

		System.out.println(str);
	}

	@Test
	public void testRedis(){

		redisTemplate.opsForValue().set("key","hello,world",1,TimeUnit.MINUTES);

		Object value = redisTemplate.opsForValue().get("key");

		System.out.println(value.toString());

	}
}
