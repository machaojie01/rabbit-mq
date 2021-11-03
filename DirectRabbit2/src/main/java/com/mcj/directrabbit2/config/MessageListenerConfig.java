package com.mcj.directrabbit2.config;

import com.mcj.directrabbit2.listener.MyAckReceiver;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author machaojie
 * email machaojie@utry.cn
 * @Date 2021/10/29 15:36
 * 消费者一般进行消息接受，手动确认的配置类
 */
@Configuration
public class MessageListenerConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private MyAckReceiver myAckReceiver;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // RabbitMQ 默认是自动确认， 这里改为手动确认消息
        // 设置一个队列,
        // 如果想设置多个队列，直接在后面加逗号和队列名 如：
        // container.setQueueNames("TestDirectQueue", "FanoutQueue1", "FanoutQueue2"); 需要注意的是这里的队列名必须是都要存在的
        container.setQueueNames("TestDirectQueue", "FanoutQueue1");

        //另一种设置队列的方法,如果使用这种情况,那么要设置多个,就使用addQueues
        //container.setQueues(new Queue("TestDirectQueue",true));
        //container.addQueues(new Queue("FanoutQueue1",true));
        //container.addQueues(new Queue("FanoutQueue2",true));

        container.setMessageListener(myAckReceiver);
        return container;
    }

}
