package com.example.testmaven.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author heshufan
 * @date 2021/2/24
 */

public class TestCASAndLock {
    private int num = 2;
    private int num3 = 2;
    private AtomicInteger num2 = new AtomicInteger(2);
    private AtomicInteger integer = new AtomicInteger(0);

    /**
     * 这里为对象TestCASAndLock的初始化操作
     * 这里没有加锁，在多线程情况下
     */
    public void add() {
        num2.getAndIncrement();
        while (integer.compareAndSet(0, 1)) {
        }
        System.out.println(Thread.currentThread().getName());
        num++;
        integer.compareAndSet(1, 0);

    }

    public void add2() {
        num3++;
    }

    public String get() {
        return num + ":" + num2 + ":" + num3;
    }

    public static void main(String[] args) throws InterruptedException {
        TestCASAndLock object = new TestCASAndLock();
        TestCASAndLock object2 = new TestCASAndLock();
        AtomicInteger integer = new AtomicInteger(0);
        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                        object.add();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "线程" + i).start();
        }
        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                        object2.add2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "线程" + i).start();
        }
        Thread.sleep(10000);
        System.out.println(object.get());
        System.out.println(object2.get());
>>>>>>> callback
    }
}
