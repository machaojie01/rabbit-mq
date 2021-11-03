package com.mcj.directrabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author machaojie
 * email machaojie@utry.cn
 * @Date 2021/10/29 11:22
 * 主题交换机的配置
 */
@Configuration
public class TopicRabbitConfig {

    // 用于绑定的路由键
    private static final String FIRST_ROUTING = "topic.man";
    private static final String SECOND_ROUTING = "topic.women";

    /**
     * 第一个队列
     * @return
     */
    @Bean
    public Queue firstQueue() {
        return new Queue("FirstQueue", true);
    }

    /**
     * 第二个队列
     * @return
     */
    @Bean
    public Queue secondQueue() {
        return new Queue("SecondQueue", true);
    }

    /**
     * 第三个队列
     * @return
     */
    @Bean
    public Queue thirdQueue() {
        return new Queue("ThirdQueue", true);
    }


    /**
     * 交换机
     * @return
     */
    @Bean
    public TopicExchange topicRabbitExchange() {
        return new TopicExchange("TopicExchange", true, false);
    }

    /**
     * 将第一个队列与交换机进行绑定
     * @return
     */
    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(firstQueue()).to(topicRabbitExchange()).with(FIRST_ROUTING);

    }

    /**
     * 将第二个队列与交换机进行绑定
     * @return
     */
    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(secondQueue()).to(topicRabbitExchange()).with(SECOND_ROUTING);
    }

    /**
     * 将第三个队列与交换机进行绑定
     * @return
     */
    @Bean
    public Binding binding3() {
        return BindingBuilder.bind(thirdQueue()).to(topicRabbitExchange()).with("topic.#");
    }

}
