package com.example.testmaven.thread.dead_thread;

import java.util.Stack;
import java.util.concurrent.CountDownLatch;

/**
 * @author heshufan
 * @date 2021/3/12
 */

class TestSychnorized {
    private static int i = 0;
    private static Object object = new Object();

    public static void main(String[] args) {

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
////                try {
////                    Thread.sleep(2000);
//                    synchronized (object){
//                        System.out.println("获得锁");
//                        i = 1;
//                        System.out.println("唤醒");
//                        object.notifyAll();
//                    }
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//            }
//        }).start();
//        synchronized (object){
//            try {
//                System.out.println("释放锁");
//                object.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(i);
//        }

        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                //上面可能会有一些耗时操作
                i = 1;
                countDownLatch.countDown();
            }
        }).start();

        //这里可能有些耗时操作
        try {
            countDownLatch.await();
            System.out.println(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
