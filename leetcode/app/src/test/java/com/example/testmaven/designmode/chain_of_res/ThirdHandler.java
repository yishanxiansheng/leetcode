package com.example.testmaven.designmode.chain_of_res;

/**
 * @author heshufan
 * @date 2021-01-08
 */
public class ThirdHandler implements Handler {

    @Override
    public boolean process(Request request) {
        System.out.println("third handler uccess handled");
        return true;
    }
}
