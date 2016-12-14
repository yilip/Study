package com.lip.designpatten.chain;

/**
 * Created by Lip on 2016/7/21 0021.
 */
public class SHDnsServer extends DnsServer{
    protected void sign(Recorder recorder) {
        recorder.setOwner("上海DNS服务器");
    }

    protected boolean isLocal(Recorder recorder) {
        return recorder.getDomain().endsWith("sh.cn");
    }
}
