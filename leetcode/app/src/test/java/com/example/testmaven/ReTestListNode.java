package com.example.testmaven;

import java.util.Stack;

import static com.example.testmaven.NodeUtil.createListNode;
import static com.example.testmaven.NodeUtil.printListNode;

/**
 * @author hesf001
 * @description:
 * @date :2020/10/12 11:50
 */
public class ReTestListNode {

    public static void main(String[] args) {
        NodeUtil.ListNode node = createListNode(new int[]{1, 2, 3, 4, 5});
        printListNode(deleteNode(node, 1));
    }

    /**
     * 删除链表的节点
     *
     * @param head
     * @param val
     * @return
     */
    public static NodeUtil.ListNode deleteNode(NodeUtil.ListNode head, int val) {
        NodeUtil.ListNode delete = head;
        if (head.val == val) {
            return head.next;
        }
        while (delete.next != null) {
            NodeUtil.ListNode temp = delete.next;
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
    public static NodeUtil.ListNode reverseListNode(NodeUtil.ListNode node) {
        NodeUtil.ListNode current = node;
        NodeUtil.ListNode head = null;

        if (node == null || node.next == null) {
            return node;
        }

        while (current != null) {
            NodeUtil.ListNode next = current.next;
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
    public NodeUtil.ListNode getKthFromEnd(NodeUtil.ListNode head, int k) {
        NodeUtil.ListNode slow = head;
        NodeUtil.ListNode fast = head;
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
    public int[] reversePrint(NodeUtil.ListNode head) {
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
    public NodeUtil.ListNode getIntersectionNode(NodeUtil.ListNode headA, NodeUtil.ListNode headB) {
        NodeUtil.ListNode node1 = headA;
        NodeUtil.ListNode node2 = headB;
        //两个没有结果的链表循环结束后的结果为node1与node2都指向了null
        while (node1 != node2) {
            node1 = node1 == null?headB:node1.next;
            node2 = node2 == null?headA:node2.next;
        }
        return node1;
    }
}
