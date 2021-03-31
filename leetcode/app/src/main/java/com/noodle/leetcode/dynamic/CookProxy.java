package com.noodle.leetcode.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author heshufan
 * @date 2021/3/18
 */

class CookProxy implements InvocationHandler {

    private ICook iCook;

    public CookProxy(ICook iCook) {
        this.iCook = iCook;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke start");
        method.invoke(iCook,args);
        System.out.println("invoke end");
        return null;
    }
}
