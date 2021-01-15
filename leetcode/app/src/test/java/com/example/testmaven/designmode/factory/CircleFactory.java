package com.example.testmaven.designmode.factory;

import android.os.AsyncTask;

import java.util.concurrent.ThreadFactory;

/**
 * @author heshufan
 * @date 2021-01-10
 */
public class CircleFactory implements Factory {
    String string;
    public CircleFactory(String s) {
        string = s;
    }


    @Override
    public void process() {
        System.out.println(string);
    }
}
