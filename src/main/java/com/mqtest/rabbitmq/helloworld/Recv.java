package com.mqtest.rabbitmq.helloworld;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * 简单模式
 */
public class Recv {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {

        String host = "192.168.78.128";

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost(host);

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        System.out.println("[*] waiting for message...");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String message = new String(body, "utf-8");

                System.out.println("[x] Recv " + message);
            }
        };

        channel.basicConsume(QUEUE_NAME, true, consumer);

    }

}
