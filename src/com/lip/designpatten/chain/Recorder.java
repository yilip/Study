package com.lip.designpatten.chain;

/**
 * Created by Lip on 2016/7/20 0020.
 */
public class Recorder {
    private String domain;
    private String ip;
    private String owner;
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("域名:"+this.getDomain()+"\n");
        sb.append("IP:"+this.getIp()+"\n");
        sb.append("解析者:"+this.getOwner());
        return sb.toString();
    }
}
