package com.lip;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Lip on 2016-12-15 17:43
 */
public class VolatileTest {
    public  int num=0;
    public int getNum()
    {
        return num;
    }
    public void setNum(int i)
    {
        this.num=i;
    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ES=Executors.newFixedThreadPool(2);
        final VolatileTest volatileTest=new VolatileTest();

        for(int i=0;i<2;i++)
        {
            ES.execute(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10000;j++)
                        volatileTest.num++;
                }
            });
        }
        ES.shutdown();
        long s=System.currentTimeMillis();
       while (System.currentTimeMillis()-s<2*1000)
            System.out.println(volatileTest.num);
    }
}
