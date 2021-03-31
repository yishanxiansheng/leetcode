package com.example.testmaven;

import com.example.testmaven.NodeUtil;

import java.util.List;

/**
 * @author heshufan
 * @date 2021/3/13
 */

class TestMain {

    public  NodeUtil.ListNode  mergeListNode(NodeUtil.ListNode node1, NodeUtil.ListNode node2){

        if (node1 == null){
            return node2;
        }
        if (node2 == null){
            return node1;
        }

        if(node1.val < node2.val ){
            node1.next = mergeListNode(node1.next,node2);
            return node1;
        }else {
            node2.next = mergeListNode(node1,node2.next);
            return node2;
        }
    }
}
