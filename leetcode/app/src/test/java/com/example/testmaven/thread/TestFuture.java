package com.example.testmaven.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
    }
}

class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(3000);
        return "result from Task";
    }
}