package com.example.testmaven.thread;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author heshufan
 * @date 2021-01-06
 */
public class TestThreadWaitNotify {
    static TaskQueue q = new TaskQueue();

    public static void main(String[] args) throws InterruptedException{

        ReentrantLock lock = new ReentrantLock();
        lock.tryLock(1, TimeUnit.MILLISECONDS);

        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    // 执行task:
                    while (true) {
                        try {
                            String s = q.get();
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

        Thread add = new Thread(){
            @Override
            public void run() {
                for (int i=0; i<10; i++) {
                    // 放入task:
                    String s = "t-" + Math.random();
                    System.out.println("add task: " + s);
                    q.add(s);
                    try { Thread.sleep(100); } catch(InterruptedException e) {}
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

class TaskQueue {
    Queue<String> queue = new LinkedList<>();

    public synchronized String get() throws InterruptedException {
        while (queue.isEmpty()) {
            //如果数据为空，将当前线程放入等待队列，释放锁
            this.wait();
        }
        return queue.remove();
    }

    public synchronized void add(String s) {
        queue.add(s);
        //唤醒所有在等待队列的线程
        this.notifyAll();
    }
}
