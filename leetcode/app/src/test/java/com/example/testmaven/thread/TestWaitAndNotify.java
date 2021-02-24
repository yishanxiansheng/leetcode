package com.example.testmaven.thread;

/**
 * @author heshufan
 * @date 2021-01-13
 */
public class TestWaitAndNotify {
    static volatile boolean quick = true;
    //利用0长度的数组作为锁对象最为经济
    static volatile int[] object = new int[0];

    public static void main(String[] agrs) throws  InterruptedException{
        Thread thread = new Thread(new WaitRun());
        thread.start();
        Thread.sleep(1000);
        //在main线程里面调用，是让main线程等待thread线程执行完，在继续执行后面的代码
//        thread.join();
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
                        System.out.println("wait thread start wait");
                        object.wait(0);
                        System.out.println("wait thread waited");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("wait thread finish");
            }
        }
    }

    static class NotifyRun implements Runnable {

        @Override
        public void run() {
            synchronized (object) {
                System.out.println("notify thread");
                quick = false;
                object.notifyAll();
            }

        }
    }
}
