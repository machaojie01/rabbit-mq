package com.mcj.directrabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author machaojie
 * email machaojie@utry.cn
 * @Date 2021/10/29 13:43
 * 扇形交换机配置文件
 */
@Configuration
public class FanoutRabbitConfig {

    /**
     * 第一个队列
     * @return
     */
    @Bean
    public Queue queue1() {
        return new Queue("FanoutQueue1", true);
    }

    /**
     * 第二个队列
     * @return
     */
    @Bean
    public Queue queue2() {
        return new Queue("FanoutQueue2", true);
    }

    /**
     * 第三个队列
     * @return
     */
    @Bean
    public Queue queue3() {
        return new Queue("FanoutQueue3", true);
    }

    /**
     * 创建扇形交换机
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("FanoutExchange");
    }

    /**
     * 将第一个队列绑定到交换机。此时是扇形交换机，不用设置路由键，设置了也没什么用
     * @return
     */
    @Bean
    public Binding bindingQueue1() {
        return BindingBuilder.bind(queue1()).to(fanoutExchange());
    }

    /**
     * 将第二个队列绑定到交换机，同样不需要设置路由键
     * @return
     */
    @Bean
    public Binding bindingQueue2() {
        return BindingBuilder.bind(queue2()).to(fanoutExchange());
    }

    /**
     * 将第三个队列绑定到交换机，同样不需要设置路由键
     * @return
     */
    @Bean
    public Binding bindingQueue3() {
        return BindingBuilder.bind(queue3()).to(fanoutExchange());
    }
}
