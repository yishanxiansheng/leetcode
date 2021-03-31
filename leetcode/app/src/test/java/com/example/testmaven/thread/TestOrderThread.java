package com.example.testmaven.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程顺序执行
 *
 * @author heshufan
 * @date 2021/3/5
 */

class TestOrderThread {
    static int i = 0;
    static Lock lock = new ReentrantLock(true);
    public static void main(String[] args) {
        //公平锁
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 3;) {
                    lock.lock();
                    if ((i % 3) == 0) {
                        System.out.println("A");
                        i++;
                        j++;
                    }
                    lock.unlock();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 3;) {
                    lock.lock();
                    if ((i % 3) == 1) {
                        System.out.println("B");
                        i++;
                        j++;
                    }
                    lock.unlock();
                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 3;) {
                    lock.lock();
                    if ((i % 3) == 2) {
                        System.out.println("C");
                        i++;
                        j++;
                    }
                    lock.unlock();
                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

