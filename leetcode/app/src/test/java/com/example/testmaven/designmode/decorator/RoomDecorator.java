package com.example.testmaven.designmode.decorator;

/**
 * @author heshufan
 * @date 2021-01-11
 */
public abstract class RoomDecorator extends Room {
    /**
     * 被装饰对象
     */
    private Room room;

    @Override
    void fitment() {
        /**
         * 调用被装饰者的方法
         */
        room.fitment();
    }
}
