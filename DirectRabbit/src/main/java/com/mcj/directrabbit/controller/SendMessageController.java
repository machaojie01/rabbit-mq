package com.mcj.directrabbit.controller;

import com.mcj.directrabbit.sender.MessageSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author machaojie
 * email machaojie@utry.cn
 * @Date 2021/10/28 16:42
 */
@RestController
public class SendMessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    MessageSender messageSender;

    @GetMapping("/send/direct/message")
    public String sendDirectMessage(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        messageSender.sendMessage("TestDirectExchange", "TestDirectRouting", map);
        return "OK";
    }

    @GetMapping("send/topic/message1")
    public String sendTopicMessage1() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test topic message1, hello";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        messageSender.sendMessage("TopicExchange", "topic.man", map);
        return "OK";
    }

    @GetMapping("send/topic/message2")
    public String sendTopicMessage2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test topic message2, hello";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        messageSender.sendMessage("TopicExchange", "topic.women", map);
        return "OK";
    }

    @GetMapping("/send/fanout/message")
    public String fanoutTopicMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test fanout message, hello";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        messageSender.sendMessage("FanoutExchange", map);
        return "OK";
    }

}
