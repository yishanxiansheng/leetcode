package com.example.testmaven;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author heshufan
 * @date 2021/1/26
 */

class MaxQueue {
    LinkedList<Integer> listA;
    Deque<Integer> listB;

    public MaxQueue() {
        listA = new LinkedList<>();
        listB = new LinkedList<>();
    }

    public int max_value() {
        return listB.isEmpty() ? -1 : listB.peekLast();
    }

    public void push_back(int value) {

        while (!listB.isEmpty() && listB.peekFirst() < value) {
            listB.pollFirst();
        }
        listB.addFirst(value);
        listA.add(value);

    }

    public int pop_front() {
        if (listA.isEmpty()) {
            return -1;
        }
        int a = listA.poll();
        if (a == listB.peekLast()) {
            listB.pollLast();
        }
        return a;
    }
}
