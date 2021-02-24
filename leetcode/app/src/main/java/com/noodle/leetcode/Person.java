package com.noodle.leetcode;

import java.io.Serializable;

/**
 * @author heshufan
 * @date 2021/2/17
 */

public class Person {
    public static void main(String[] args){
        ss sss = new ss();
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("ss");
    }

    public static class ss implements Serializable{
        private One one;
        private Two two;
    }

    public static class One implements Serializable{

    }
    public static class Two {

    }
}


