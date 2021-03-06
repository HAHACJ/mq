package com.mqtest.rabbitmq.publishsubscribe;

import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiveLogs {
    private static final String EXCHANGE_NAME = "logs";

    private static final String QUEUE_NAME = "queue_logs";

    public static void main(String[] argv) throws Exception {
        String host = "192.168.78.128";

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost(host);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //绑定交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Thread.sleep(5000);

        //定义队列消费者
        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);


    }
}