package com.mcj.directrabbit2.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author machaojie
 * email machaojie@utry.cn
 * @Date 2021/10/29 11:47
 */
@Component
public class TopicReceiver {

    @RabbitListener(queues = "FirstQueue")
    @RabbitHandler
    public void queue1Listener(Map  map) {
        System.out.println("队列1监听" + map.toString());
    }

    @RabbitListener(queues = "SecondQueue")
    @RabbitHandler
    public void queue2Listener(Map map) {
        System.out.println("队列2监听" + map.toString());
    }

    @RabbitListener(queues = "ThirdQueue")
    @RabbitHandler
    public void queue3Listener(Map map) {
        System.out.println("队列3监听" + map.toString());
    }
}
