package com.lip.designpatten.proxy.dynamic;

import com.lip.designpatten.proxy.GamePlayer;
import com.lip.designpatten.proxy.IGamePlayer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by Lip on 2016/7/29 0029.
 */
public class Client {
    public static void main(String[] args) {
        IGamePlayer gamePlayer=new GamePlayer("lip");
        InvocationHandler handler=new GamePlayerH(gamePlayer);
        ClassLoader cls=gamePlayer.getClass().getClassLoader();
        IGamePlayer proxy= (IGamePlayer)Proxy.newProxyInstance(cls,new Class[]{IGamePlayer.class},handler);
        proxy.login("lip","123456");
        proxy.killBoss();
        proxy.killBoss();
    }
}
