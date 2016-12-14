package com.lip.designpatten.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Lip on 2016/7/29 0029.
 */
public class GamePlayerH implements InvocationHandler{
    //被代理的对象
    Object object;
    public GamePlayerH(Object _object)
    {
        this.object=_object;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result=method.invoke(this.object,args);
        if(method.getName().equalsIgnoreCase("login"))
        {
            System.out.println("someone is login...");
        }
        return result;
    }
}
