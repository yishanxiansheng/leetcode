package com.example.testmaven.thread;

/**
 * @author heshufan
 * @date 2021/3/25
 */

class TestSynchronized {

    public synchronized void add() {
        System.out.println("add start");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        add2();
        System.out.println("add end");
    }

    public synchronized void add2() {
        System.out.println("add2");
    }

    public static void main(String[] agrs) throws InterruptedException {
        TestSynchronized test = new TestSynchronized();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.add();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.add2();
            }
        }).start();


    }
}
