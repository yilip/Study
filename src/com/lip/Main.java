package com.lip;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Lip on 2016/8/12 0012.
 */
public class Main {
    private static  int num=3;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool= Executors.newFixedThreadPool(num);
        for(int i=0;i<5;i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName()+"["+Thread.currentThread().hashCode()+"]正在运行...");
                        Thread.sleep(2*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        Thread.sleep(6*1000);
        for(int i=0;i<5;i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName()+"["+Thread.currentThread().hashCode()+"]正在运行...");
                        Thread.sleep(2*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        pool.shutdown();

    }
}
