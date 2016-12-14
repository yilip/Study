package com.lip.designpatten.proxy.statics;

import com.lip.designpatten.proxy.GamePlayer;
import com.lip.designpatten.proxy.IGamePlayer;

/**
 * Created by Lip on 2016/7/29 0029.
 */
public class GamePlayerProxy implements IGamePlayer {
    private IGamePlayer gamePlayer=null;
    public GamePlayerProxy(String name)
    {
        gamePlayer=new GamePlayer(this,name);
    }
    public void login(String user, String pwd) {
        gamePlayer.login(user,pwd);
    }

    public void killBoss() {
        gamePlayer.killBoss();
    }

    public void upgrade() {
        gamePlayer.upgrade();
    }

    public IGamePlayer getProxy() {
        return null;
    }
}
