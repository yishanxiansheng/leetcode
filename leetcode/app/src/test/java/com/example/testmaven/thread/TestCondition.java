package com.example.testmaven.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author heshufan
 * @date 2021-01-07
 */
public class TestCondition {

    static TaskQueue2 taskQueue2 = new TaskQueue2();

    public static void main(String[] args) throws InterruptedException {
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    // 执行task:
                    while (true) {
                        try {
                            String s = taskQueue2.get();
                            System.out.println("execute task: " + s);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }

            };
            t.start();
            ts.add(t);
        }

        Thread add = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    // 放入task:
                    String s = "t-" + Math.random();
                    System.out.println("add task: " + s);
                    taskQueue2.add(s);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        add.start();

        //主线程等待add线程执行完
        add.join();
        Thread.sleep(100);

        //关闭ts中的线程????
        for (Thread t : ts) {
            t.interrupt();
        }
    }
}

class TaskQueue2 {

    private ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    Queue<String> queue = new LinkedList<>();

    public String get() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                //如果数据为空，将当前线程放入等待队列，释放锁
                condition.await();
            }
            System.out.println("获取数据");
            return queue.remove();
        } finally {
            System.out.println("get 释放锁");
            lock.unlock();
        }
    }

    public void add(String s) {
        lock.lock();
        try {
            queue.add(s);
            //唤醒所有在等待队列的线程
            condition.signalAll();
        } finally {
            System.out.println("add 释放锁");
            lock.unlock();
        }


    }
}
