package com.example.testmaven.thread;

/**
 * @author heshufan
 * @date 2021-01-14
 */
public class TestInterrupted {
    public static void main(String[] args) throws InterruptedException{
        Thread thread = new MyThread();
        thread.start();
        Thread.sleep(10);
        thread.interrupt();
        thread.join();
        System.out.println("end");
    }

    static class MyThread extends Thread{
        int n = 0;
        @Override
        public void run() {
            //不会清除中断状态
            while (!isInterrupted()){
                n++;
                System.out.println(n);
            }
        }
    }
}
