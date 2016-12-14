package com.lip.designpatten.chain;

/**
 * Created by Lip on 2016/7/21 0021.
 */
public class TopDnsServer extends DnsServer {
    protected void sign(Recorder recorder) {
        recorder.setOwner("全球顶级DNS服务器");
    }

    protected boolean isLocal(Recorder recorder) {
        return true;
    }
}
