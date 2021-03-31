package com.example.testmaven.thread;

import java.util.HashMap;

/**
 * @author heshufan
 * @date 2021/2/26
 */

class SingleInstance {
    private static SingleInstance instance = null;

    public static SingleInstance getInstance() {
        if (instance == null) {
            synchronized (SingleInstance.class) {
                if (instance == null) {
                    instance = new SingleInstance();
                }
            }
        }
        return instance;
    }
}
