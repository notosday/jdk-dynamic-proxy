package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by 淋漓尽致 on 2016/12/7.
 * 本类实现了java反射包中的invocationHandler接口。代理类调用方法时，
 */
public class ProxyHandler implements InvocationHandler {

    private Object targetClass;

    public ProxyHandler(Object targetClass) {
        this.targetClass = targetClass;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy:"+proxy.getClass().getName());
        System.out.println("method:"+method.getName());
        System.out.println("args:"+args[0].getClass().getName());
        System.out.println("Before invoke method...");
        Object invoke = method.invoke(targetClass, args);//反射执行某个类的方法
        System.out.println("After invoke method...");
        return invoke;
    }
}
