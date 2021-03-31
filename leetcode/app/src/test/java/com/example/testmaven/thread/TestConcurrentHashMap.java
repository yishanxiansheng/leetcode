package com.example.testmaven.thread;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author heshufan
 * @date 2021/3/25
 */

public class TestConcurrentHashMap {
    public static Map<Integer, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        ExecutorService pool1 = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            pool1.execute(new Runnable() {
                @Override
                public void run() {
                    Random random = new Random();
                    int randomNum = random.nextInt(10);
                    if (map.containsKey(randomNum)) {
                        map.put(randomNum, map.get(randomNum) + 1);
                    } else {
                        map.put(randomNum, 1);
                    }
                }
            });
        }
    }
}
