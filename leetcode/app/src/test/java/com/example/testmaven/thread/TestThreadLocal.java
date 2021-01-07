package com.example.testmaven.thread;

/**
 * @author heshufan
 * @date 2021-01-07
 */
public class TestThreadLocal {
    static ThreadLocal<Integer> threadLocals = new ThreadLocal<>();

    public static void main(String[] args){
        threadLocals.set(5);
        final Thread thread = new Thread(){
            @Override
            public void run() {
                threadLocals.set(3);
                System.out.println(threadLocals.get()+":subthread");
            }
        };
        thread.start();
        System.out.println(threadLocals.get()+":mainthread");
    }
}
