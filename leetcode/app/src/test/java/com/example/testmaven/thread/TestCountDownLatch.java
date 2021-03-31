package com.example.testmaven.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author heshufan
 * @date 2021/3/10
 */

public class TestCountDownLatch {
    public static void main(String[] args) throws InterruptedException{
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println("thread");
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println("所有线程执行完成");
    }
}
