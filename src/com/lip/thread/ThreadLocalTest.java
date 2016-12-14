package com.lip.thread;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Lip on 2016-12-12 10:20
 */
public class ThreadLocalTest {
    private static AtomicInteger threadId=new AtomicInteger(0);
    private final static ThreadLocal<Integer>local=new ThreadLocal<Integer>(){
        @Override
        protected  Integer initialValue()
        {
            return threadId.getAndIncrement();
        }
    };

    public static void main(String[] args) {
        for(int i=0;i<4;i++)
        {
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":"+local.get());
                }
            });
            t.start();
        }
    }
}
