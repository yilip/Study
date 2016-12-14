package com.lip.redis;

import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lip on 2016/7/18 0018.
 */
public class TestRedis {
    public static  void main(String args[])
    {
        final  Jedis jedis = new Jedis("localhost",9379);
        Student student=new Student("xiejia");
        jedis.auth("123456");
        jedis.set("foo", "bar");
        //jedis.set("xie".getBytes(),SerializeUtil.serialize(student));
        Student s=(Student) SerializeUtil.unserialize(jedis.get("xie".getBytes()));
        String value = jedis.get("foo");
        System.out.println(value);
        System.out.println("studet name:"+s.getName());
        new Thread(new Runnable() {
            public void run() {
                jedis.subscribe(new JedisPubSub() {
                    @Override
                    public void onMessage(String channel, String message) {
                        super.onMessage(channel, message);
                        System.out.println("message from channel:"+message);
                    }
                },"a");
            }
        }).start();

        System.out.println("test");
/*
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //Jedis Cluster will attempt to discover cluster nodes automatically
        HostAndPort host=new HostAndPort("localhost", 9379);
        jedisClusterNodes.add(host);
        JedisPoolConfig poolConfig=new JedisPoolConfig();
        JedisCluster jc = new JedisCluster(jedisClusterNodes);
        //jc.auth("123456");
        jc.set("b", "bar");
        System.out.println(jc.get("b"));*/
    }
}
