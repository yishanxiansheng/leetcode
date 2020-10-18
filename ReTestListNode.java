package com.cmrh.msp.android.msp.msp;

import com.cmrh.msp.android.msp.msp.NodeUtil.ListNode;

import java.util.Stack;

import static com.cmrh.msp.android.msp.msp.NodeUtil.createListNode;
import static com.cmrh.msp.android.msp.msp.NodeUtil.printListNode;

/**
 * @author hesf001
 * @description:
 * @date :2020/10/12 11:50
 */
public class ReTestListNode {

    public static void main(String[] args) {
        ListNode node = createListNode(new int[]{1, 2, 3, 4, 5});
        printListNode(deleteNode(node, 1));
    }

    /**
     * 删除链表的节点
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode deleteNode(ListNode head, int val) {
        ListNode delete = head;
        if (head.val == val) {
            return head.next;
        }
        while (delete.next != null) {
            ListNode temp = delete.next;
            if (temp.val == val) {
                delete.next = temp.next;
                temp.next = null;
                break;
            } else {
                delete = delete.next;
            }
        }
        return head;
    }

    /**
     * 链表翻转
     */
    public static ListNode reverseListNode(ListNode node) {
        ListNode current = node;
        ListNode head = null;

        if (node == null || node.next == null) {
            return node;
        }

        while (current != null) {
            ListNode next = current.next;
            current.next = head;
            head = current;
            current = next;
        }
        return head;
    }

    /**
     * 链表中倒数第k个节点
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode slow = head;
        ListNode fast = head;
        while (k > 1 && fast != null) {
            fast = fast.next;
            k--;
        }

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 利用栈倒过来打印链表
     *
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int[] res = new int[stack.size()];
        int cnt = 0;
        while (!stack.isEmpty()) {
            res[cnt++] = stack.pop();
        }
        return res;
    }

    /**
     * 寻找两个列表的相交结点
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode node1 = headA;
        ListNode node2 = headB;
        //两个没有结果的链表循环结束后的结果为node1与node2都指向了null
        while (node1 != node2) {
            node1 = node1 == null?headB:node1.next;
            node2 = node2 == null?headA:node2.next;
        }
        return node1;
    }
}
