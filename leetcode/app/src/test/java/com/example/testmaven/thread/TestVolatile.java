package com.example.testmaven.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author heshufan
 * @date 2021-01-14
 */
public class TestVolatile {
    static volatile int i = 0;
    static AtomicInteger  j = new AtomicInteger(0);

    public static void main(String[] agrs) throws InterruptedException{
        for (int i = 0; i < 100000; i++) {
            new Thread(new MyRunnable()).start();
        }
        //保证子线程都执行完
        Thread.sleep(10000);
        System.out.println(i);
        System.out.println(j.get());
    }

    static class MyRunnable implements Runnable{
        int a = 0;
        @Override
        public void run() {
            try {
                Thread.sleep(1);
                //a++操作速度速度太快，可能新的线程还没有开始运行旧的线程就已经运行结束了，所以需要加点时间
                a++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            j.getAndAdd(1);
        }
    }
}
