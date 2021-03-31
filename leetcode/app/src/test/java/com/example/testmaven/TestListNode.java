
package com.example.testmaven;


import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import com.example.testmaven.NodeUtil.ListNode;

public class TestListNode {
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(2);
        node.next.next.next = new ListNode(1);
//        node.next.next.next.next = new ListNode(1);
        insertionSortList(node);

        MyStack queue = new MyStack();
        queue.push(1);
        queue.push(2);
        queue.pop();
        queue.top();

        PrintUtil.print(10 / 10);
        PrintUtil.print(10 % 10);
        isPalindrome(node);

    }

    public static int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][prices.length];
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[i] < prices[j]) {
                    dp[i][j] = prices[j] - prices[i];
                } else {
                    dp[i][j] = 0;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    public static NodeUtil.ListNode insertionSortList(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode cur = head.next;
        return null;
    }

    public static void reverseNode() {

    }

    /**
     * 双栈表示队列：
     */
    static class MyQueue {
        //接受数据
        Stack<Integer> inStack;
        //输出数据
        Stack<Integer> outStack;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            inStack = new Stack<>();
            outStack = new Stack<>();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            inStack.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            //outstack不为空直接出，为空将instack全部接受过来
            if (!outStack.empty()) {
                return outStack.pop();
            } else {
                while (!inStack.empty()) {
                    outStack.push(inStack.pop());
                }
                if (!outStack.empty()) {
                    return outStack.pop();
                }
            }
            return 0;
        }

        /**
         * Get the front element.
         */
        public int peek() {
            if (!outStack.empty()) {
                return outStack.peek();
            } else {
                while (!inStack.empty()) {
                    outStack.push(inStack.pop());
                }
                if (!outStack.empty()) {
                    return outStack.peek();
                }
            }
            return 0;
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            if (outStack.empty() && inStack.empty()) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 双队列实现栈
     */
    static class MyStack {
        //始终保持为空
        Queue<Integer> queue1;
        //存储数据
        Queue<Integer> queue2;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            queue2.add(x);
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            int result = 0;
            while (!queue2.isEmpty() && queue2.size() > 1) {
                queue1.add(queue2.poll());
            }
            if (!queue2.isEmpty()) {
                result = queue2.poll();
            }
            Queue<Integer> temp = queue2;
            queue2 = queue1;
            queue1 = temp;
            return result;
        }

        /**
         * Get the top element.
         */
        public int top() {
            int result = 0;
            while (!queue2.isEmpty() && queue2.size() > 1) {
                queue1.add(queue2.poll());
            }
            if (!queue2.isEmpty()) {
                result = queue2.peek();
                queue1.add(queue2.poll());
            }
            Queue<Integer> temp = queue2;
            queue2 = queue1;
            queue1 = temp;
            return result;
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            if (queue1.isEmpty() && queue2.isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 分割列表
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode dummyFirstHead = new ListNode(0);
        ListNode first = dummyFirstHead;

        ListNode dummySecondHead = new ListNode(0);
        ListNode second = dummySecondHead;

        while (head != null) {
            if (head.val < x) {
                ListNode temp = new ListNode(head.val);
                first.next = temp;
                first = first.next;
            } else {
                ListNode temp = new ListNode(head.val);
                second.next = temp;
                second = second.next;
            }
            head = head.next;
        }
        first.next = dummySecondHead.next;
        return dummyFirstHead.next;
    }

    /**
     * 拆分链表
     *
     * @param root
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode root, int k) {
        //计算链表的长度
        ListNode[] result = new ListNode[k];
        ListNode head = root;
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        head = root;
        if (size <= k) {
            for (int i = 0; i < k; i++) {
                if (i < size) {
                    result[i] = new ListNode(head.val);
                    head = head.next;
                } else {
                    result[i] = head;
                }
            }
        } else {
            //todo
            int length = size / k;
            int b = size % k;
            for (int i = 0; i < k; i++) {
                if (i < b) {
                    //取length + 1
                    ListNode dummy = new ListNode(0);
                    ListNode real = dummy;
                    for (int j = 0; j < length + 1; j++) {
                        real.next = new ListNode(head.val);
                        real = real.next;
                        head = head.next;
                    }
                    result[i] = dummy.next;
                } else {
                    //取length + 1
                    ListNode dummy = new ListNode(0);
                    ListNode real = dummy;
                    for (int j = 0; j < length; j++) {
                        real.next = new ListNode(head.val);
                        real = real.next;
                        head = head.next;
                    }
                    result[i] = dummy.next;
                }
            }
        }
        return result;
    }

    /**
     * 找到链表环的起点
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean isCircle = false;
        //链表中是否有环
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                isCircle = true;
                break;
            }
        }
        //
        if (isCircle) {
            ListNode pre = head;
            while (slow != null && pre != null) {
                slow = slow.next;
                pre = pre.next;
                if (slow == pre) {
                    return slow;
                }
            }
        } else {
            return null;
        }
        return null;
    }

    /**
     * 链表旋转
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int size = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            size++;
        }

        //从第k2个链表断开
        int k2 = size - k % size;
        ListNode dummy1 = new ListNode(0);
        ListNode first = dummy1;
        ListNode dummy2 = new ListNode(0);

        while (k2 != 0) {
            first.next = new ListNode(head.val);
            head = head.next;
            first = first.next;
            k2--;
        }
        dummy2.next = head;
        ListNode real2 = dummy2;
        while (real2.next != null) {
            real2 = real2.next;
        }

        real2.next = dummy1.next;
        return dummy2.next;
    }

    /**
     * 翻转链表
     *
     * @param node
     */
    public static ListNode reverseListNode(ListNode node) {
        //已翻转的链表的头结点
        ListNode cur = node;

        //未翻转的链表的头结点
        ListNode pre = null;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转
     *
     * @param head
     * @param m
     * @param n
     * @return https://leetcode-cn.com/problems/reverse-linked-list-ii/submissions/
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        //
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //第m个结点的前驱结点
        ListNode pre = dummy;
        for (int i = 1; i < m; i++) {
            pre = pre.next;
        }
        head = pre.next;

        for (int i = m; i < n; i++) {
            //链表交换的经典操作
            ListNode temp = head.next;
            head.next = temp.next;
            temp.next = head;
            pre.next = temp;
        }
        return dummy.next;
    }

    /**
     * 复杂链表复制
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        //hashmap法
        Map<Node, Node> map = new HashMap<>();
        //遍历链表新方法
        for (Node node = head; node != null; node = node.next) {
            Node node1 = new Node(node.val);
            map.put(node, node1);
        }
        for (Node node = head; node != null; node = node.next) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
        }
        return map.get(head);
    }

    /**
     * 复杂链表复制
     *
     * @param head
     * @return
     */
    public Node copyRandomList2(Node head) {
        Node node = head;
        if (head == null) {
            return null;
        }
        //将拷贝节点放到原节点后面，例如1->2->3这样的链表就变成了这样1->1'->2->2'->3->3'
        while (node != null) {
            Node node1 = new Node(node.val);
            Node next = node.next;
            node.next = node1;
            node1.next = next;
            node = next;
        }

        //把拷贝节点的random指针安排上
        node = head;
        while (node != null && node.next != null) {
            if (node.random != null) {
                node.next.random = node.random.next;
                node = node.next.next;
            }
        }

        node = head;
        Node result = node.next;
        while (node != null && node.next != null) {
            Node temp = node.next;
            node.next = temp.next;
            node = temp;
        }
        return result;
    }

    /**
     * 两数相加
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode start = new ListNode();
        ListNode end = start;
        int data = 0;
        while (l1 != null || l2 != null || data != 0) {
            if (l1 != null) {
                data += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                data += l2.val;
                l2 = l2.next;
            }
            ListNode temp = new ListNode();
            temp.val = data % 10;
            data = data / 10;
            end.next = temp;
            end = end.next;
        }
        return start.next;
    }

    /**
     * 删除有序链表的重复结点
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            ListNode next = temp;
            while (next.next != null && next.next.val == temp.val) {
                next = next.next;
            }
            temp.next = next.next;
            temp = next.next;
        }
        return head;
    }

    /**
     * 判断是否是回文链表
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        ListNode middle = head;
        ListNode right = head;
        while (right != null && right.next != null) {
            middle = middle.next;
            right = right.next.next;
        }

        boolean isPalindrome = true;
        ListNode a = reverseListNode(middle);
        while (head != null && a != null) {
            if (head.val != a.val) {
                isPalindrome = false;
                break;
            }
            head = head.next;
            a = a.next;
        }
        return isPalindrome;
    }

    /**
     * 判断两个链表是否相交
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode listNodeA = headA;
        ListNode listNodeB = headB;
        while (listNodeA != listNodeB) {
            listNodeA = listNodeA == null ? headB : listNodeA.next;
            listNodeB = listNodeB == null ? headA : listNodeB.next;
        }
        return listNodeA;
    }

    /**
     * 倒数第k个指针
     *
     * @param head
     * @param k
     * @return
     */
    public int kthToLast(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        while (k > 1 && fast != null) {
            fast = fast.next;
            k--;
        }
        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow.val;
    }


    /**
     * 合并两个排序列表
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode pre = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                pre.next = l1;
                pre = pre.next;
                l1 = l1.next;
            } else {
                pre.next = l2;
                pre = pre.next;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            pre.next = l1;
        }
        if (l2 != null) {
            pre.next = l2;
        }
        return head.next;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k) {
                    return true;
                } else {
                    map.put(nums[i], i);
                }
            }
        }
        return false;
    }

    /**
     * 删除链表的结点
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            if (cur.val == val) {
                if (pre == null) {
                    return cur.next;
                } else {
                    pre.next = cur.next;
                }
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }
}
