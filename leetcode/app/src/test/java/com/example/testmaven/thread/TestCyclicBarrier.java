package com.example.testmaven.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author heshufan
 * @date 2021/3/11
 */

class TestCyclicBarrier {
    public static void main(String[] args){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("到达1");
                        cyclicBarrier.await();
                        Thread.sleep(1000);
                        System.out.println("到达2");
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
