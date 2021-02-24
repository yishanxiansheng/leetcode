package com.noodle.leetcode.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author heshufan
 * @date 2021/2/3
 */

class MyHandler implements InvocationHandler {

   private Animal animal;

    public MyHandler(Animal animal) {
        this.animal = animal;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object object = method.invoke(animal, args);
        return object;
    }
}
