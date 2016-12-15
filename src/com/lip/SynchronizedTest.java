package com.lip;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Lip on 2016-12-15 09:47
 */
public class SynchronizedTest {
    public  void test1() {
        System.out.println("********method 1 start**********");
        try {
            System.out.println(String.format("******current thread:%s************", Thread.currentThread().getName()));
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("********method 1 end**********");
    }

    public  void test2() {
        System.out.println("********method 2 start**********");
        try {
            System.out.println(String.format("******current thread:%s************", Thread.currentThread().getName()));
            Thread.sleep(2* 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("********method 2 end**********");
    }
    public  void test3() {
        System.out.println("********method 3 start**********");
        try {
            System.out.println(String.format("******current thread:%s************", Thread.currentThread().getName()));
            Thread.sleep(2* 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("********method 3 end**********");
    }

    public static void main(String[] args) {
        final SynchronizedTest synchronizedTest = new SynchronizedTest();
        ExecutorService ES = Executors.newFixedThreadPool(4);
        ES.submit(new Runnable() {
            @Override
            public void run() {
                synchronized ("100".intern()) {
                    synchronizedTest.test1();
                }
            }
        });
        ES.submit(new Runnable() {
            @Override
            public void run() {
                synchronized ("101".intern()) {
                    synchronizedTest.test2();
                }
            }
        });
        ES.submit(new Runnable() {
            @Override
            public void run() {
                synchronized ("101".intern()) {
                    synchronizedTest.test3();
                }
            }
        });
        ES.shutdown();
    }
}
