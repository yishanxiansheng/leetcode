package com.example.testmaven.thread;

/**
 * @author heshufan
 * @date 2021-01-13
 */
public class TestWaitAndNotify {
    static volatile boolean quick = true;
    static volatile Object object = new Object();

    public static void main(String[] agrs) throws  InterruptedException{
        Thread thread = new Thread(new WaitRun());
        thread.start();
        Thread.sleep(1000);
        thread.join();
        Thread thread1 = new Thread(new NotifyRun());
        thread1.start();
    }

    static class WaitRun implements Runnable {

        @Override
        public void run() {
            synchronized (object) {
                //必须要在循环中检查状态，不能用if
                while (quick) {
                    try {
                        System.out.println("wait thread wait");
                        object.wait(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("wait thread");
            }
        }
    }

    static class NotifyRun implements Runnable {

        @Override
        public void run() {
            synchronized (object) {
                System.out.println("notify thread");
                object.notifyAll();
                quick = false;
            }

        }
    }
}
