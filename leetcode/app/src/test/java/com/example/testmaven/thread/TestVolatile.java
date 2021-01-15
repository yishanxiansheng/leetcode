package com.example.testmaven.thread;

/**
 * @author heshufan
 * @date 2021-01-14
 */
public class TestVolatile {
    static volatile int i = 0;

    public static void main(String[] agrs) {
        for (int i = 0; i < 100000; i++) {
            new Thread(new MyRunnable()).start();
        }
        System.out.println(i);
    }
    static class MyRunnable implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
