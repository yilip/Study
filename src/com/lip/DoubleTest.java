package com.lip;


/**
 * @Author Lip
 * @Date 2016-10-31 11:38
 */
public class DoubleTest {
    public static void main(String[] args) {
        Double d=new Double("1.834");
        System.out.println(d.longValue());
        for(int i=0;i<1;i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    long wait=10l;
                    while (true)
                    {
                        long start=System.currentTimeMillis();
                        while (System.currentTimeMillis()-start<4*wait)
                            ;
                        try {
                            Thread.sleep(wait);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            t.start();
        }
    }
}
