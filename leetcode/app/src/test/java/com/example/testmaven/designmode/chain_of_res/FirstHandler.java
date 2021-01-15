package com.example.testmaven.designmode.chain_of_res;

import java.math.BigDecimal;

/**
 * @author heshufan
 * @date 2021-01-08
 */
public class FirstHandler implements Handler {

    @Override
    public boolean process(Request request) {
        if (request.getAmount().compareTo(BigDecimal.valueOf(1000)) > 0) {
            return false;
        }
        System.out.println("first handler success handled");
        return true;
    }
}
