package com.lip.rabbitmq.route;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Scanner;

public class EmitLogDirect {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv)
                  throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String cmd="";
        Scanner scanner=new Scanner(System.in);
        while(!"q".equalsIgnoreCase(cmd)) {
            String line[]=scanner.nextLine().split(" ");
            channel.basicPublish(EXCHANGE_NAME, line[0], null, line[1].getBytes());
            System.out.println(" [x] Sent to[" + line[0] + "]:" + line[1] + "");
        }

        channel.close();
        connection.close();
    }
    //..
}