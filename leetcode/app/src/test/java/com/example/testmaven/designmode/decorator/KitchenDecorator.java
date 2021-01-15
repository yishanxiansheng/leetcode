package com.example.testmaven.designmode.decorator;

/**
 * @author heshufan
 * @date 2021-01-11
 */
public class KitchenDecorator extends RoomDecorator {

    @Override
    void fitment() {
        super.fitment();
        System.out.println("add kitchen");
    }
}
