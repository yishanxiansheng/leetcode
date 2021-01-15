package com.example.testmaven.designmode.decorator;

/**
 * @author heshufan
 * @date 2021-01-11
 */
public class WaterDecorator extends RoomDecorator {

    @Override
    void fitment() {
        super.fitment();
        /**
         * 扩展方法
         */
        System.out.println("add water");
    }
}
