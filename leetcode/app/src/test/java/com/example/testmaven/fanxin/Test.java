package com.example.testmaven.fanxin;

import com.example.testmaven.NodeUtil;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author heshufan
 * @date 2021/3/25
 */

class Test {
    public static void main(String[] agrs){

    }

    public NodeUtil.ListNode reverseListNode(NodeUtil.ListNode node){
        NodeUtil.ListNode pre = null;
        NodeUtil.ListNode cur = node;
        while( cur != null){
            NodeUtil.ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public List<Integer> getReverseNum(NodeUtil.ListNode node){
        Deque<Integer> deque = new ArrayDeque<>();
        while (node != null){
            deque.push(node.val);
            node = node.next;
        }
        List<Integer> res = new ArrayList<>();
        while (!deque.isEmpty()){
            res.add(deque.pop());
        }
        return  res;
    }


}
