package com.mcwl.home_page.send;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jerry
 * @date 2018/8/9
 * @description
 */
@Component
public class HomepageSend {

    private static Logger logger = LoggerFactory.getLogger(HomepageSend.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void send(String message){
        logger.info("homepageSend准备发消息"+message);
        rabbitTemplate.convertAndSend("exchange.homepage","mq.homepage",message);
        logger.info("homepage发送了消息"+message);
    }
}
