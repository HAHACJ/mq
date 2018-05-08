package com.mqtest.rabbitmq.routing;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class RoutingReceiveLogs {
    private static final String EXCHANGE_NAME = "routing_logs";

    private static final String QUEUE_NAME = "routing_queue_logs";

    public static void main(String[] argv) throws Exception {
        String host = "192.168.78.128";

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost(host);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //绑定交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "logs");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Thread.sleep(5000);

        //定义队列消费者
        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(QUEUE_NAME + ":" + message + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);


    }
}