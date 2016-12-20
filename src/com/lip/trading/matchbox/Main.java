package com.lip.trading.matchbox;


import com.lip.trading.UserService;
import com.lip.uitl.MathUtil;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Lip on 2016-12-20 17:55
 */
public class Main {
    private static Random random=new Random();
    private static AtomicLong orderId=new AtomicLong(0);
    public static void main(String[] args) {
        final OrderBook orderBook=new OrderBook();
        ExecutorService ES= Executors.newFixedThreadPool(20);
        for(int i=0;i<20;i++) {
            ES.submit(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Order order = getRandomOrder();
                        orderBook.process(order);
                       System.out.println(Thread.currentThread().getName()+":"+order);
                    }
                }
            });
        }
        while (true) {
            try {
                Thread.sleep(1*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
/*            System.out.println("************ask order**************");
            OrderBook.print(orderBook.getAskOrder());
            System.out.println("************bid order**************");
            OrderBook.print(orderBook.getBidOrder());
            System.out.println("************history order**************");
            OrderBook.print(orderBook.orderHistory);*/
        }
    }
    public static Order getRandomOrder()
    {
        long s=System.currentTimeMillis();
        double bPrice=15.0,r=1;

        Order order=new Order();
        if(random.nextBoolean())
            order.direction=Direction.BUY;
        else
            order.direction=Direction.SELL;
        if(random.nextBoolean())
            r=-1;
        order.id=orderId.getAndIncrement();
        order.price= MathUtil.roundup(bPrice+3*random.nextDouble()*r,2);
        order.symbol="5000001";
        order.userId= UserService.getRandomUser();
        order.status=OrderStatus.NEW;
        order.time=new Date();
        order.num=1+random.nextInt(20);
        order.left=order.num;
        System.out.println("generate random order cost:"+(System.currentTimeMillis()-s));
        return order;
    }
}
