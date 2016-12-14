package com.lip.designpatten.chain;

import java.util.Scanner;

/**
 * Created by Lip on 2016/7/21 0021.
 */
public class Client {
    public static  void main(String arg[])
    {
        DnsServer sh=new SHDnsServer();
        DnsServer china=new ChinaDnsServer();
        DnsServer top=new TopDnsServer();
        china.setUpperServer(top);
        sh.setUpperServer(china);
        System.out.println("=========域名解析服务==========");
        while(true)
        {
            System.out.println("请输入域名(Q退出)");
            Scanner scanner=new Scanner(System.in);
            String domain=scanner.nextLine();
            if(domain.equalsIgnoreCase("q"))
            {
                break;
            }
            Recorder  recorder=new Recorder();
            recorder.setDomain(domain);
            sh.update(null,recorder);
            System.out.println("--------DNS服务器解析结果----------");
            System.out.println(recorder);
        }
    }
}
