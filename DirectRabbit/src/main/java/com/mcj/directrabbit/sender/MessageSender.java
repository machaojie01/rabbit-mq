package com.mcj.directrabbit.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author machaojie
 * email machaojie@utry.cn
 * @Date 2021/11/3 9:36
 * 消息发送
 */
@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 消息发送
     * @param exchange 交换机
     * @param routingKey 路由键
     * @param message 消息体
     */
    public void sendMessage(String exchange, String routingKey, Map<String, Object> message) {
        // 使其能够对json序列化与反序列化
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

    /**
     * 消息发送的重载函数，适用于扇形交换机，没有路由键
     * @param exchange  交换机
     * @param message   消息体
     */
    public void sendMessage(String exchange, Map<String, Object> message) {
        // 是能够对json进行序列化与反序列化
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend(exchange, null, message);
    }

}
