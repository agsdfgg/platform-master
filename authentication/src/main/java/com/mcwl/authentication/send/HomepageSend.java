package com.mcwl.authentication.send;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jerry
 * @date 2018/8/10
 * @description 返回给homepage
 */
@Component
public class HomepageSend {

    private static Logger logger = LoggerFactory.getLogger(HomepageSend.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String token){
       rabbitTemplate.convertAndSend("exchange.homepageReturn","mq.homepage.return",token);
       logger.info("权限中心发送了消息"+token+"到homepage");
    }
}
