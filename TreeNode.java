package com.cmrh.msp.android.msp.msp;

/**
 * @author hesf001
 * @description:
 * @date :2020/10/14 10:08
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

