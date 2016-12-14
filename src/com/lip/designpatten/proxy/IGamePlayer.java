package com.lip.designpatten.proxy;

/**
 * Created by Lip on 2016/7/29 0029.
 */
public interface IGamePlayer {
    public void login(String user,String pwd);
    public void killBoss();
    public void upgrade();
    public IGamePlayer getProxy();
}
