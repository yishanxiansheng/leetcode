package com.example.testmaven.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author heshufan
 * @date 2021/3/16
 */

public class TestMultiThread {

    private int a = 0;
    public static void main(String[] args) throws InterruptedException {
        TestMultiThread thread = new TestMultiThread();
        Object object = new Object();
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            new Thread(new ThreadObservanle(thread,countDownLatch,object)).start();
        }
        countDownLatch.await();
        System.out.println(thread.a);
    }

    public void receiveMsg(){
        a++;
    }

}
