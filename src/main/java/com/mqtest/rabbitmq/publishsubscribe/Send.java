package com.mqtest.rabbitmq.publishsubscribe;

/**
 * 发布订阅模式
 */
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Send {

    private static final String EXCHANGE_NAME = "logs";


    public static void main(String[] argv) throws Exception {
        String host = "192.168.78.128";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String message = EXCHANGE_NAME + ": hello world";


        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }

}
