package com.mqtest.springbootmq.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSend {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        amqpTemplate.convertAndSend("exchange", "topic.message", "hello topic");
    }
}
