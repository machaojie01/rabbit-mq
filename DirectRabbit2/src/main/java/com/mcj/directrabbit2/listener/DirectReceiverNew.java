package com.mcj.directrabbit2.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author machaojie
 * email machaojie@utry.cn
 * @Date 2021/10/29 11:16
 * 监听直连型交换机对应队列的类，这个是没有消费者消息确认机制的
 */
//@Component
//@RabbitListener(queues = "TestDirectQueue")
public class DirectReceiverNew {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("第二个DirectReceiver消费者收到消息：" + testMessage.toString());
    }

}
