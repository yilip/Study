package com.lip.rabbitmq.exchange;

import java.io.IOException;
import java.util.Scanner;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class EmitLog {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv)
                  throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String cmd="";
        Scanner scanner=new Scanner(System.in);
        while(!"q".equalsIgnoreCase(cmd)) {
            String message = scanner.nextLine();
            //channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println(" [x] Sent :" + message );
        }
        channel.close();
        connection.close();
    }
    //...
}