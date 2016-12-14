package com.lip.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Lip on 2016/7/19 0019.
 */
public class JedisFactory {
    private static JedisPool jedisPool=null;
    public static  JedisPool getJedisPool()
    {
        if(jedisPool==null)
        {
            JedisPoolConfig poolConfig=new JedisPoolConfig();
            poolConfig.setMaxTotal(500);
            poolConfig.setMaxIdle(5);
            jedisPool=new JedisPool(poolConfig,"localhost",9379,30000,"123456");
        }
        return jedisPool;
    }
    public static Jedis getJedis()
    {
        return getJedisPool().getResource();
    }
    public static  void close()
    {
        jedisPool.close();
    }
}
