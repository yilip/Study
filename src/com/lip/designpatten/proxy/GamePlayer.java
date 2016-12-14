package com.lip.designpatten.proxy;

/**
 * Created by Lip on 2016/7/29 0029.
 */
public class GamePlayer implements IGamePlayer{
    String user;
    String pwd;
    public GamePlayer(String _user)
    {
        this.user=_user;
    }
    public GamePlayer(IGamePlayer _gamePlayer,String _user)
    {
        if(_gamePlayer==null)
            System.out.println("can not create a user....");
        else
        {
            this.user=_user;
        }
    }
    public void login(String user, String pwd) {
        System.out.println(user+" is logining...");
    }

    public void killBoss() {
        System.out.println(user+" is killing the boss...");
    }

    public void upgrade() {
        System.out.println(user+" is upgrading...");
    }

    public IGamePlayer getProxy() {
        return null;
    }
}
