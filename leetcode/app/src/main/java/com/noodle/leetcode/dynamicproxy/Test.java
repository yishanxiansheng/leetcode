package com.noodle.leetcode.dynamicproxy;

import android.os.Handler;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author heshufan
 * @date 2021/2/3
 */

class Test {

    public static void main(String[] args){
        Animal animal = (Animal)
                Proxy.newProxyInstance(Animal.class.getClassLoader(),
                        new Class[]{Animal.class},new MyHandler(new Bird()));
        animal.eat();
    }
}
