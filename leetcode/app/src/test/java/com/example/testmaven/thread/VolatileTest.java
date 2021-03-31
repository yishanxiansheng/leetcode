package com.example.testmaven.thread;

/**
 * @author heshufan
 * @date 2021/3/6
 */

class VolatileTest {
     volatile  int a = 1;
     volatile int b = 2;

    public void change() {
        a = 3;
        b = a;
    }

    public void print() {
        //不加volatile
        //正常结果为b=3;a=3 两个修改都刷回
        //有可能是b=2;a=3   a先修改，先刷回了主内存，b修改的值没有刷回主内存
        //有可能是b=2;a=1  两个修改都没有刷回
        //有可能是b=3;a=1  这个最不容易出现，但是也是有可能的，b修改后刷回，虽然a先修改，但是a后刷回
        System.out.println("b=" + b + ";a=" + a);

        //如果都加volatile
        //相比之前只有b=3;a=1 无法出现了 因为线程1对a值进行修改后（单独赋值可以保证原子性），强制刷新到主内存，那么线程2要使用a，自己
        //缓存的a的值失效，必须去拿最新的a的值，所以就不会出现a = 1
    }

    public static void main(String[] args) {
        while (true) {
            final VolatileTest test = new VolatileTest();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.change();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();
                }
            }).start();

        }
    }
}
