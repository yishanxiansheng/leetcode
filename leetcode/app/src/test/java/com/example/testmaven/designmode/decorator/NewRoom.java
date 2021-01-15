package com.example.testmaven.designmode.decorator;

/**
 * 具体实现类
 * @author heshufan
 * @date 2021-01-11
 */
public class NewRoom extends Room {
    @Override
    void fitment() {
        System.out.println("this is a new room");
    }
}
