package com.lip.rabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Scanner;

/**
 * Created by Lip on 2016/8/3 0003.
 * 生产者队列，消息发送者
 */
public class Producer1 {
    private final static String QUEUE_NAME = "hello";
    public static void main(String[] args)throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String cmd="";
        Scanner scanner=new Scanner(System.in);
        while(!"q".equalsIgnoreCase(cmd)) {
            String message = scanner.nextLine();
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent :" + message );
        }
    }
}
