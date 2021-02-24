package com.noodle.leetcode;

/**
 * @author heshufan
 * @date 2021/2/7
 */

class Generic<T> {

    public static void main(String[] args) {

    }

    public void print(Generic<? extends Animal> object) {

    }

    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public class Animal {

    }
}
