package com.lip.designpatten.chain;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Observable;
import java.util.Observer;

/**
* Created by Lip on 2016/7/20 0020.
        */
public abstract  class DnsServer extends Observable implements Observer{
    //处理请求，接收事件后的处理
    public void update(Observable arg0,Object arg1)
    {
        Recorder recorder=(Recorder)arg1;
        if(isLocal(recorder))
        {
            recorder.setIp(getIp());
        }
        else
        {
            responseFromUpperServer(recorder);
        }
        //签名
        sign(recorder);
    }
    //设置下一个应该处理的服务器
    public void setUpperServer(DnsServer dnsServer)
    {
        super.deleteObservers();
        super.addObserver(dnsServer);
    }
    //通知下一个服务器处理
    private void responseFromUpperServer(Recorder recorder)
    {
        super.setChanged();
        super.notifyObservers(recorder);
    }
    protected abstract  void sign(Recorder recorder);
    protected abstract boolean isLocal(Recorder recorder);
    public  String getIp() {
        StringBuilder sb = new StringBuilder();
        int first = 10;
        while (first == 10 || first == 172 || first == 192) {
            first = (int) (Math.random() * 256);
        }
        sb.append(first);
        for (int i = 1; i < 4; i++) {
            sb.append(".").append((int) (Math.random() * 256));
        }
        return sb.toString();
    }
}
