package com.example.testmaven.designmode.chain_of_res;

import java.math.BigDecimal;

/**
 * @author heshufan
 * @date 2021-01-08
 */
public class SecondHandler implements Handler {

    @Override
    public boolean process(Request request) {
        if (request.getAmount().compareTo(BigDecimal.valueOf(2000)) > 0) {
            return false;
        }
        System.out.println("second handler success handled");
        return true;
    }
}
