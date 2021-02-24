package com.example.testmaven.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 保证first、second、third依次执行
 * @author heshufan
 * @date 2021/1/27
 */

class Foo {
    AtomicInteger atomicInteger  = new AtomicInteger(0);
    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        while (atomicInteger.get()!=0){
        }
        printFirst.run();
        atomicInteger.addAndGet(1);
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        while (atomicInteger.get()!=1){
        }
        printSecond.run();
        atomicInteger.addAndGet(1);
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        while (atomicInteger.get()!=2){

        }
        printThird.run();
        atomicInteger.addAndGet(1);
    }
}
