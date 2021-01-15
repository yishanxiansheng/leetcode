package com.example.testmaven.thread.dead_thread;

/**
 * @author heshufan
 * @date 2021-01-12
 */
public class TestDeadThread {
    static String objectA = "a";
    static String objectB = "b";
    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (objectA){
                    try {
                        Thread.sleep(2000);
                        System.out.println(Thread.holdsLock(objectA));
                        synchronized (objectB){
                            System.out.println("this is thread a");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadA.start();

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (objectB){
                    try {
                        Thread.sleep(2000);
                        synchronized (objectA){
                            System.out.println("this is thread a");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadB.start();
    }
}
