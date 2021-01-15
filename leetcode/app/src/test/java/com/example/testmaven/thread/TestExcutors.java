package com.example.testmaven.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author heshufan
 * @date 2021-01-14
 */
public class TestExcutors {
    public static void main(String[] agrs) {
        ExecutorService service =  Executors.newFixedThreadPool(4);
        for (int i = 0; i < 5; i++) {
            //可以有返回结果
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("thread");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            //无返回结果
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("thread");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}
