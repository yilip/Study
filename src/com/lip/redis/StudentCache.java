package com.lip.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by Lip on 2016/7/19 0019.
 */
public class StudentCache {
    public static  void main(String arg[]) throws InterruptedException {
        MyCache<String,Student>cache=new MyCache<String, Student>().setExpireTime(5);
        cache.put("lip",new Student("libuyi"));
        System.out.println("lip:"+cache.get("lip").getName());
        Thread.sleep(6*1000);
        System.out.println("lip:"+cache.get("lip"));
    }
}
