package com.lip.redis;

import javafx.application.Application;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by Lip on 2016/7/19 0019.
 */
public class MyCache<T1, T2> {
    private  Jedis jedis;
    private static  JedisPool jedisPool;
    //过期时间
    private int EXPIRE_TIME=60*60;

    static {
        if (jedisPool==null) {
            jedisPool = JedisFactory.getJedisPool();
        }
    }
    public  MyCache()
    {
        init();
    }
    public MyCache<T1, T2> setExpireTime(int seconds)
    {
        this.EXPIRE_TIME=seconds;
        return this;
    }
    public void init()
    {
        if(jedis==null)
            jedis= jedisPool.getResource();
    }

    /**
     *
     * @param key
     * @return
     */
    public T2 get(T1 key)
    {
        if(jedis==null)
            throw new NullPointerException("jedis does not open...");
        if(key instanceof String)
        {
            return (T2)SerializeUtil.unserialize(jedis.get(((String) key).getBytes()));
        }
        return (T2)SerializeUtil.unserialize(jedis.get(SerializeUtil.serialize(key)));
    }

    /**
     *
     * @param key
     * @param value
     */
    public void put(T1 key,T2 value)
    {
        if(jedis==null)
            throw new NullPointerException("jedis does not open...");
        if(key instanceof  String)
        {
            jedis.set(((String) key).getBytes(),SerializeUtil.serialize(value));
            jedis.expire(((String) key).getBytes(),EXPIRE_TIME);
            return;
        }
        byte[]keyByte=SerializeUtil.serialize(key);
        jedis.set(keyByte,SerializeUtil.serialize(value));
        jedis.expire(keyByte,EXPIRE_TIME);
    }

    /**
     *
     * @param key
     */
    public void clear(T1 key)
    {
        if(jedis==null)
            throw new NullPointerException("jedis does not open...");
        if(key instanceof  String)
        {
            jedis.expire(((String) key).getBytes(),0);
            return;
        }
        byte[]keyByte=SerializeUtil.serialize(key);
        jedis.expire(keyByte,0);
    }
    public void close()
    {
        jedis.close();
    }
}
