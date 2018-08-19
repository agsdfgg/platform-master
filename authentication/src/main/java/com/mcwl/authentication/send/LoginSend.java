package com.mcwl.authentication.send;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jerry
 * @date 2018/7/24
 * @description
 */
@Component
public class LoginSend {

    private static Logger  logger = LoggerFactory.getLogger(LoginSend.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void send(String token){
        rabbitTemplate.convertAndSend("exchange.login.return","mq.login.return",token);
        logger.info("权限中心发送了消息，内容是:"+token);
    }
}
