package com.lip.redis;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lip on 2016/7/29 0029.
 */
public class LockTest {
    @Test
    public void testLock()
    {
        Lock<Student>lock=new Lock<Student>().setNamespace("student").setLockTime(30);
        lock.lock("lip");
        //lock.unlock("lip");
        lock.lock("lip");
    }
}