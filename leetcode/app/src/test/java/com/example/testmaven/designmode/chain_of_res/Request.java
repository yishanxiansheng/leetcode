package com.example.testmaven.designmode.chain_of_res;

import java.math.BigDecimal;

/**
 * @author heshufan
 * @date 2021-01-08
 */
public class Request {
    private String name;
    private BigDecimal amount;

    public Request(String name, BigDecimal amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
