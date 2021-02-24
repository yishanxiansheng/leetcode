package com.example.testmaven.thread.blockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 *
 * @author heshufan
 * @date 2021/2/1
 */

class TestBlockQueue {
    // 声明一个容量为10的缓存队列
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);
        //new了三个生产者和一个消费者
        Productor producer1 = new Productor(queue);
        Productor producer2 = new Productor(queue);
        Productor producer3 = new Productor(queue);
        Consumer consumer = new Consumer(queue);

        // 借助Executors
        ExecutorService service = Executors.newCachedThreadPool();
        // 启动线程
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer);

        // 执行10s
        Thread.sleep(10 * 1000);
        producer1.stop();
        producer2.stop();
        producer3.stop();

        Thread.sleep(2000);
        // 退出Executor
        service.shutdown();
    }

    public void threadPools(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,2,0L, TimeUnit.MILLISECONDS,new PriorityBlockingQueue<Runnable>());
    }
}
