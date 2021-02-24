package com.example.testmaven.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author heshufan
 * @date 2021-01-07
 */
public class TestFuture {

    public static void main(String[] args) throws InterruptedException, Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<String> future = executorService.submit(new Task());
        System.out.println("main Thread");
        //get方法会阻塞主线程
        String result = future.get();
        System.out.println(result);
        System.out.println("main thread stop");

        order();

        try {
            FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Thread.sleep(2000);
                    return "heshufan";
                }
            });
            new Thread(futureTask).start();
            String s = futureTask.get(1, TimeUnit.SECONDS);
            System.out.println(s);
        } catch (Exception e) {
            System.out.println("error");
        }


    }

    private static void order() throws InterruptedException {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println("thread1");
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                System.out.println("thread2");

            }
        };
        Thread thread3 = new Thread() {
            @Override
            public void run() {
                System.out.println("thread3");

            }
        };
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
    }
}

class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(3000);
        return "result from Task";
    }
}
