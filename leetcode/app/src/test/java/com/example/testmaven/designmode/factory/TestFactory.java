package com.example.testmaven.designmode.factory;

/**
 * @author heshufan
 * @date 2021-01-10
 */
public class TestFactory {
    public static void main(String[] args){
        Factory factory = Factory.getFactory("Circle");
        factory.process();

        Factory factory2 = Factory.getFactory("Rectangle");
        factory2.process();
    }
}
