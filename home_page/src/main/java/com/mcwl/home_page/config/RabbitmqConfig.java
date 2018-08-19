package com.mcwl.home_page.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jerry
 * @date 2018/8/9
 * @description rabbitmq配置类
 */
@Configuration
public class RabbitmqConfig {

    private static String RoutingKey = "mq.homepage";

    private static String RoutingKeyReturn = "mq.homepage.return";

    @Bean
    public Queue homepageQueue(){
        return new Queue(RabbitmqConfig.RoutingKey,true);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("exchange.homepage",true,false);
    }

    @Bean
    public Binding bindHomepageQueue(){
        return BindingBuilder.bind(homepageQueue()).to(exchange()).with(RabbitmqConfig.RoutingKey);
    }

    @Bean
    public Queue homepageReturnQueue(){
        return new Queue(RabbitmqConfig.RoutingKeyReturn,true);
    }

    @Bean
    public TopicExchange returnExchange(){
        return new TopicExchange("exchange.homepageReturn",true,false);
    }

    @Bean
    public Binding bindHomepageReturnQueue(){
        return BindingBuilder.bind(homepageReturnQueue()).to(returnExchange()).with(RabbitmqConfig.RoutingKeyReturn);
    }
}
