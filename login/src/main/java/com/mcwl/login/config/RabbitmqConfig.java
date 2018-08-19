package com.mcwl.login.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jerry
 * @date 2018/7/24
 * @description 描述：mq配置类
 */
@Configuration
public class RabbitmqConfig {

    private static String routingKey = "mq.login";
    private static String routingKeyReturn = "mq.login.return";

    @Bean
    public Queue loginQueue(){
        return new Queue(RabbitmqConfig.routingKey, true);
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("exchange.login",true,false);
    }
    @Bean
    public Binding bindLoginQueue(){
        return BindingBuilder.bind(loginQueue()).to(exchange()).with("mq.login");
    }
    @Bean
    public Queue returnQueue(){
        return new Queue(RabbitmqConfig.routingKeyReturn, false);
    }
    @Bean
    public TopicExchange returnExchange(){
        return new TopicExchange("exchange.login.return",false,false);
    }
    @Bean
    public Binding bindReturnQueue(){
        return BindingBuilder.bind(returnQueue()).to(returnExchange()).with("mq.login.return");
    }
}
