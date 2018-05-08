package com.mqtest.springbootmq.direct;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectSendConfig {

    @Bean
    public Queue queue() {
        return new Queue("queue");

    }
}

