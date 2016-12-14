package com.lip.study;

/**
 * Created by Lip on 2016/7/18 0018.
 */
public class TestString {
    public static  void main(String args[])
    {
        String rootUrl ="http://www.ccecsh.com:443/a/b/c.html";
        if(rootUrl.contains("443"))//https
        {
            rootUrl = rootUrl.replaceFirst("http(://[^:]*).*", "https$1");
        }else {//http
            rootUrl = rootUrl.replaceFirst("(http://[^/]*).*", "$1");
        }
        //rootUrl = rootUrl.replaceFirst("https://[^/]*/?", "/");
        System.out.println(rootUrl);
    }
}
