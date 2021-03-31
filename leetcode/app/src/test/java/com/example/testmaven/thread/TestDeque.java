package com.example.testmaven.thread;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author heshufan
 * @date 2021/3/13
 */

class TestDeque {

    public static void main(String[] agrs){
        Deque<Integer> stack = new ArrayDeque<>();
        /**
         * 当做栈
         */
        //入栈
        stack.push(1);
        stack.pop();
        stack.peek();
        stack.isEmpty();

        /**
         * 当做队列
         */
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(1);
        queue.addFirst(2);

        //下面是一样的  移除队列头部
        queue.pollFirst();
        queue.removeFirst();

        queue.size();

        //移除队列尾部
        queue.pollLast();
        queue.removeLast();
    }
}
