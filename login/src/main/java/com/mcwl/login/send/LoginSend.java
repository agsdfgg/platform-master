package com.mcwl.login.send;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Jerry
 * @date 2018/7/24
 * 描述：登陆发送队列消息
 * @description
 */
@Component
public class LoginSend {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Async
    public void send(String user){
        rabbitTemplate.convertAndSend("exchange.login","mq.login",user);
    }
}
