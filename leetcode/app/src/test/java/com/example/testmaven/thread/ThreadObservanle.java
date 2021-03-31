package com.example.testmaven.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author heshufan
 * @date 2021/3/16
 */

public class ThreadObservanle implements Runnable {
    TestMultiThread testMultiThread;
    CountDownLatch countDownLatch;
    Object object;

    public ThreadObservanle(TestMultiThread testMultiThread, CountDownLatch countDownLatch,Object object){
        this.testMultiThread = testMultiThread;
        this.countDownLatch = countDownLatch;
        this.object = object;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            synchronized (object){
                testMultiThread.receiveMsg();
                countDownLatch.countDown();
                object.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
