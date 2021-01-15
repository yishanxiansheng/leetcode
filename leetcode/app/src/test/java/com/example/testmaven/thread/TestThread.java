package com.example.testmaven.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author heshufan
 * @date 2021-01-05
 */
public class TestThread {
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static int number = 0;

    public static void main(String[] args) throws InterruptedException {
        //一个读锁拿到锁后，其他的读锁也可以拿到锁，而写锁拿到锁后，读锁都必须等待
        Thread thread1 = new Thread(new ReadThread1(), "thread1");
        Thread thread2 = new Thread(new ReadThread2(), "thread2");
        Thread thread3 = new Thread(new WriteThread(), "thread3");
        thread3.start();
        thread1.start();
        try {
            //此时在main线程调用thread1的jion(),main线程会等待thread1运行完后再执行后面的方法
            thread1.join();
            thread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread interThread = new Thread(new InterThread(), "intreThread");
        interThread.start();
        Thread.sleep(100);
        interThread.interrupt();
        Thread.yield();
    }

    static class ReadThread1 implements Runnable {

        @Override
        public void run() {
            try {
                lock.readLock().lock();
                System.out.println(Thread.currentThread().getName() + "num:" + number);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.readLock().unlock();
            }

        }
    }

    static class ReadThread2 implements Runnable {

        @Override
        public void run() {
            try {
                lock.readLock().lock();
                System.out.println(Thread.currentThread().getName() + "num:" + number);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.readLock().unlock();
            }
        }
    }

    static class WriteThread implements Runnable {

        @Override
        public void run() {
            try {
                lock.writeLock().lock();
                number = 3;
                System.out.println(Thread.currentThread().getName() + "num:" + number);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
            }

        }
    }

    static class InterThread implements Runnable {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        ConcurrentHashMap<Integer,Integer> concurrentHashMap = new ConcurrentHashMap<>();

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                System.out.println("1");
                concurrentHashMap.put(1,1);
            }
        }
    }
}
