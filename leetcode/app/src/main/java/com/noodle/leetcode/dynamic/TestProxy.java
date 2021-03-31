package com.noodle.leetcode.dynamic;

import java.lang.reflect.Proxy;

/**
 * @author heshufan
 * @date 2021/3/18
 */

class TestProxy {
    public static void main(String[] args){
        CookFeng cookFeng = new CookFeng();
        CookProxy cookProxy = new CookProxy(cookFeng);
        ICook cook = (ICook) Proxy.newProxyInstance(cookProxy.getClass().getClassLoader(),
                cookFeng.getClass().getInterfaces(),cookProxy);

        //动态代理与静态代理的区别就在这里，同样是持有被代理对象cookFeng
        //对于静态代理，如果被代理人有两个方法需要代理，那么静态代理就需要写两个方法，分别调用被代理人的方法
        //而动态代理就不用写两个方法，一个invoke方法就可以搞定！！！
        //动态代理本质上是生成一个Proxy0 继承了Proxy，实现了ICook接口，调用cook就会去调用Proxy0的方法，
        // 最后调用我们传入的cookProxy的invoke方法
        cook.cook();
        cook.dealWithFood();
    }
}
