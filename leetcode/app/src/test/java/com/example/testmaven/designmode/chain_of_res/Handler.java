package com.example.testmaven.designmode.chain_of_res;

/**
 * @author heshufan
 * @date 2021-01-08
 */
public interface Handler {

    /**
     *
     * @param request
     * @return true 处理成功 false 交给下一个处理
     */
    boolean process(Request request);
}
