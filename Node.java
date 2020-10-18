package com.cmrh.msp.android.msp.msp;

/**
 * @author hesf001
 * @description: 复杂链表结点
 * @date :2020/10/13 9:39
 */
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
