package com.example.testmaven.designmode.factory;

/**
 * @author heshufan
 * @date 2021-01-10
 */
public class RectangleFactory implements Factory {
    String string;

    public RectangleFactory(String string) {
        this.string = string;
    }

    @Override
    public void process() {
        System.out.println(string);
    }
}
