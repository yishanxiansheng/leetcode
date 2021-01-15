package com.example.testmaven.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author heshufan
 * @date 2021-01-14
 */
public class TestCallable {
    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask(new MyCall());
        futureTask.run();
        try {
            String result = futureTask.get();
            System.out.println(result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyCall implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "Heshufan";
        }
    }
}
