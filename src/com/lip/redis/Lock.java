package com.lip.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;
import java.util.concurrent.Semaphore;

/**
 * Created by Lip on 2016/7/29 0029.
 */
public class Lock<T> {
    public static String LOCK_PREFIX = "lock:";
    private Jedis jedis;
    private int lockTime = 2;
    private static JedisPool jedisPool;
    private String namespace;
    private  Logger logger= LoggerFactory.getLogger(this.getClass());
    static {
        if (jedisPool == null) {
            jedisPool = JedisFactory.getJedisPool();
        }
    }

    public Lock() {
        init();
    }

    private void init() {
        if (jedis == null)
            jedis = jedisPool.getResource();
    }

    public Lock setLockTime(int time) {
        this.lockTime = time;
        return this;
    }
    public Lock setNamespace(String namespace)
    {
        this.namespace=namespace;
        return this;
    }
    /**
     * 锁定资源
     *
     * @param key
     */
    public Lock lock(String key) {
        if(namespace==null) {
            namespace = UUID.randomUUID().toString();
        }
        String lockKey = LOCK_PREFIX +namespace+":"+key;
        long lock = 0;
        while (lock != 1) {
            Long T1 = lockTime * 1000 + System.currentTimeMillis();
            lock = jedis.setnx(lockKey.getBytes(), SerializeUtil.serialize(T1));
            if (lock != 1)//有人持有锁
            {
                logger.info("[{}:{}]is locked...",namespace,key);
                //检查锁是否超时
                Long T2 = (Long) SerializeUtil.unserialize(jedis.get(lockKey.getBytes()));
                long now=System.currentTimeMillis();
                if (now >= T2)//已经超时
                {
                    logger.info("[{}:{}] was timeout...",namespace,key);
                    T1=now+lockTime*1000;
                    Long T3 = (Long) SerializeUtil.unserialize(jedis.getSet(lockKey.getBytes(), SerializeUtil.serialize(T1)));
                    if (T1 > T3)//已经获得该锁
                    {
                        logger.info("success to hold lock [{}:{}]...",namespace,key);
                        break;
                    }
                    else {//锁超时的过程中，又有其他对象获得该锁
                        logger.info("waiting to hold lock [{}:{}]...",namespace,key);
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    logger.info("waiting to hold lock [{}:{}]...",namespace,key);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                logger.info("success to hold lock [{}:{}]...",namespace,key);
                break;
            }
        }
        jedis.expire(lockKey.getBytes(),lockTime);
        return this;
    }

    /**
     * 主动释放锁
     * @param key
     */
    public void unlock(String key) {
        String lockKey = LOCK_PREFIX +namespace+":"+key;
        Long T = (Long) SerializeUtil.unserialize(jedis.get(lockKey.getBytes()));
        if(T>System.currentTimeMillis())
            jedis.del(lockKey.getBytes());
        logger.info("success to  unlock [{}:{}]...",namespace,key);
    }
    public void close()
    {
        if(jedis!=null)
           jedis.close();
    }
}
