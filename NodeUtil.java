package com.cmrh.msp.android.msp.msp;

/**
 * @author hesf001
 * @description:
 * @date :2020/10/12 14:24
 */
public class NodeUtil {
    /**
     * 打印listnode的值
     *
     * @param listNode listnode
     */
    public static void printListNode(ListNode listNode) {
        ListNode node = listNode;
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }


    /**
     * 创建一个listnode
     *
     * @param numbers numbers
     */
    public static ListNode createListNode(int[] numbers) {
        ListNode result = new ListNode(2);
        ListNode real = result;
        for (int i = 0; i < numbers.length; i++) {
            ListNode node = new ListNode(numbers[i]);
            real.next = node;
            real = real.next;
        }
        return result.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
