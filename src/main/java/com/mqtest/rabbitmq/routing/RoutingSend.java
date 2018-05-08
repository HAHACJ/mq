package com.mqtest.rabbitmq.routing;

/**
 * 路由模式
 */

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RoutingSend {

    private static final String EXCHANGE_NAME = "routing_logs";


    public static void main(String[] argv) throws Exception {
        String host = "192.168.78.128";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String message = EXCHANGE_NAME + ": hello world";


        channel.basicPublish(EXCHANGE_NAME, "logs", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }

}
