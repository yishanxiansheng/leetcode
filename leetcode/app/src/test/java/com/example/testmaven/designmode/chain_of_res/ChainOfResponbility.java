package com.example.testmaven.designmode.chain_of_res;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 责任链
 *
 * @author heshufan
 * @date 2021-01-08
 */
public class ChainOfResponbility {

    public static void main(String[] args) {
        Request request = new Request("heshufan", BigDecimal.valueOf(2000));
        List<Handler> lists = new ArrayList<>();
        lists.add(new FirstHandler());
        lists.add(new SecondHandler());
        lists.add(new ThirdHandler());
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).process(request)) {
                System.out.println("request handled");
                break;
            }
        }
    }
}
