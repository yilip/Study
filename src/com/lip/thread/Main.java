package com.lip.thread;
import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
   public static void main(String[] args) {
      System.out.println(testPool());
      System.out.println("main thread finished");
   }
   public static String testPool()
   {
      ExecutorService es= Executors.newSingleThreadExecutor();
      es.submit(new Runnable() {
         @Override
         public void run() {
            for(int i=0;i<5;i++) {
               try {
                  Thread.sleep(100);
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
               System.out.println("-------------" + i + "---------------------");
            }
         }
      });
      //es.shutdown();
      return "finished";
   }

}
