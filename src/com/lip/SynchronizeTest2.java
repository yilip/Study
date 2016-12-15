package com.lip;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Lip on 2016-12-15 15:51
 */
public class SynchronizeTest2 {
    public static void main(String[] args) {
        final SynchronizedTest test1 = new SynchronizedTest();
        ExecutorService ES = Executors.newFixedThreadPool(2);
        ES.submit(new Runnable() {
            @Override
            public void run() {
                test1.test1();
            }
        });
        ES.submit(new Runnable() {
            @Override
            public void run() {
                final SynchronizedTest test2 = new SynchronizedTest();
                test2.test1();
            }
        });
        ES.shutdown();
    }
}
