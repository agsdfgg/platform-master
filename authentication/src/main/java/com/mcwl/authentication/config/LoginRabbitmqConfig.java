package com.mcwl.authentication.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jerry
 * @date 2018/7/24
 * 描述：
 * @description
 */
@Configuration
public class LoginRabbitmqConfig {

    private static String routingKey = "mq.login";
    private static String routingKeyReturn = "mq.login.return";

    @Bean
    public Queue loginQueue(){
        return new Queue(LoginRabbitmqConfig.routingKey, true);
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
        return new Queue(LoginRabbitmqConfig.routingKeyReturn, false);
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
