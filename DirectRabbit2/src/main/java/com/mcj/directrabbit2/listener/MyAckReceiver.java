package com.mcj.directrabbit2.listener;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author machaojie
 * email machaojie@utry.cn
 * @Date 2021/10/29 15:38
 * 消息接收处理类
 * 这个是消费者有消息确认机制的处理类
 */
@Component
public class MyAckReceiver implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            // 因为传递消息的时候用的是map传递，所以将map从message内取出需要做些处理
            String msg = message.toString();
            String[] msgArray = msg.split("'");
            // 将生产者发送得消息由String转为Map，这里可以看一下Message的源码，可以知道消费者那边传递的消息体是在单引号后面的第一个。
            Map msgMap = JSON.parseObject(msgArray[1].trim(), Map.class);
            String messageId = (String) msgMap.get("messageId");
            String messageData = (String) msgMap.get("messageData");
            String createTime = (String) msgMap.get("createTime");
            if (message.getMessageProperties().getConsumerQueue().equals("TestDirectQueue")) {
                System.out.println("  MyAckReceiver  messageId:" + messageId + "  messageData:" + messageData + "  createTime:" + createTime);
                System.out.println("消费的主题消息来自：" + message.getMessageProperties().getConsumerQueue());
                System.out.println("执行TestDirectQueue中的消息的业务处理流程......\n\n\n\n\n");

            } else if (message.getMessageProperties().getConsumerQueue().equals("FanoutQueue1")) {
                System.out.println("  MyAckReceiver  messageId:" + messageId + "  messageData:" + messageData + "  createTime:" + createTime);
                System.out.println("消费的主题消息来自：" + message.getMessageProperties().getConsumerQueue());
                System.out.println("执行FanoutQueue1中的消息的业务处理流程......");
            } else if (message.getMessageProperties().getConsumerQueue().equals("FanoutQueue2")) {
                System.out.println("  MyAckReceiver  messageId:" + messageId + "  messageData:" + messageData + "  createTime:" + createTime);
                System.out.println("消费的主题消息来自：" + message.getMessageProperties().getConsumerQueue());
                System.out.println("执行FanoutQueue2中的消息的业务处理流程......");
            } else if (message.getMessageProperties().getConsumerQueue().equals("FanoutQueue3")) {
                System.out.println("  MyAckReceiver  messageId:" + messageId + "  messageData:" + messageData + "  createTime:" + createTime);
                System.out.println("消费的主题消息来自：" + message.getMessageProperties().getConsumerQueue());
                System.out.println("执行FanoutQueue3中的消息的业务处理流程......");
            } else if (message.getMessageProperties().getConsumerQueue().equals("FirstQueue")) {
                System.out.println("  MyAckReceiver  messageId:" + messageId + "  messageData:" + messageData + "  createTime:" + createTime);
                System.out.println("消费的主题消息来自：" + message.getMessageProperties().getConsumerQueue());
                System.out.println("执行FirstQueue中的消息的业务处理流程......");
            } else if (message.getMessageProperties().getConsumerQueue().equals("SecondQueue")) {
                System.out.println("  MyAckReceiver  messageId:" + messageId + "  messageData:" + messageData + "  createTime:" + createTime);
                System.out.println("消费的主题消息来自：" + message.getMessageProperties().getConsumerQueue());
                System.out.println("执行SecondQueue中的消息的业务处理流程......");
            } else if (message.getMessageProperties().getConsumerQueue().equals("ThirdQueue")) {
                System.out.println("  MyAckReceiver  messageId:" + messageId + "  messageData:" + messageData + "  createTime:" + createTime);
                System.out.println("消费的主题消息来自：" + message.getMessageProperties().getConsumerQueue());
                System.out.println("执行ThirdQueue中的消息的业务处理流程......");
            }
            // 用于肯定确认
            channel.basicAck(deliveryTag, true);  //第二个参数，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
            // channel.basicReject(deliveryTag, true);//第二个参数，true会重新放回队列，所以需要自己根据业务逻辑判断什么时候使用拒绝
        } catch (Exception e) {
            // 用于否定确认 第二个参数时表示拒绝后是否重入队列，true时会冲入队列，当为false是不会重入队列
            channel.basicReject(deliveryTag, false);
            e.printStackTrace();
        }
    }

}
