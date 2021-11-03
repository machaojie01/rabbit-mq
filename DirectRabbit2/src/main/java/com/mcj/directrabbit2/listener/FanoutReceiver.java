package com.mcj.directrabbit2.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author machaojie
 * email machaojie@utry.cn
 * @Date 2021/10/29 14:18
 * 监听扇形交换机对应队列的类，这个是没有对应的消费者消息确认机制的
 */
//@Component
public class FanoutReceiver {

    @RabbitListener(queues = "FanoutQueue1")
    @RabbitHandler
    public void queue1Listener(Map map) {
        System.out.println("监听queue1的消费者收到消息：" + map.toString());
    }

    @RabbitListener(queues = "FanoutQueue2")
    @RabbitHandler
    public void queue2Listener(Map map) {
        System.out.println("监听queue2的消费者收到消息：" + map.toString());
    }

    @RabbitListener(queues = "FanoutQueue3")
    @RabbitHandler
    public void queue3Listener(Map map) {
        System.out.println("监听queue3的消费者收到消息：" + map.toString());
    }

}
