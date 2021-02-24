package com.example.testmaven;

import android.telephony.emergency.EmergencyNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author heshufan
 * @date 2021/2/20
 */

public class TestTreeNode2 {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(4);
        TreeNode b = new TreeNode(3);
        isSubStructure(treeNode, b);
        String ss = "1,2,3,,4";
        String[] aa = ss.split(",");
        System.out.println(Arrays.toString(ss.split(",")));
    }


    /**
     * 二叉树的所有路径
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {

        List<String> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfs3(res, root, list);
        return res;
    }

    public void dfs3(List<String> res, TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            list.add(root.val);
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                buffer.append("->" + list.get(i));
            }
            res.add(buffer.toString().substring(2));
            return;
        }
        list.add(root.val);
        dfs3(res, root.left, list);
        if (root.left != null) {
            list.remove(list.size() - 1);
        }
        dfs3(res, root.right, list);
        if (root.right != null) {
            list.remove(list.size() - 1);
        }
    }

    public static boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        dfs(A, B);
        return result2;
    }

    static boolean result2 = false;

    /**
     * dfs搜索找到A中结点值与与B根节点相等的结点
     *
     * @param a
     * @param b
     */
    private static void dfs(TreeNode a, TreeNode b) {
        if (a == null) {
            return;
        }
        if (a.val == b.val) {
            /**
             * 找到跟B相等的结点，找到后开始递归判断两个树是否相等
             */
            if (isSubTreeHelper(a, b)) {
                //如果存在就立刻返回
                result2 = true;
                return;
            }
        }
        //继续遍历
        dfs(a.left, b);
        dfs(a.right, b);
    }

    /**
     * 判断B是否为A的子树，并且二者根节点值相同
     *
     * @param A
     * @param B
     * @return
     */
    public static boolean isSubTreeHelper(TreeNode A, TreeNode B) {
        if (A == null && B != null) {
            return false;
        }
        if (A != null && B == null) {
            return true;
        }
        if (A == null && B == null) {
            return true;
        }
        return A.val == B.val && isSubTreeHelper(A.left, B.left) && isSubTreeHelper(A.right, B.right);
    }

    /**
     * 二叉搜索树的后序遍历序列
     *
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        for (int i = 0; i < postorder.length; i++) {
            int j = 0;
            while (j < i && postorder[j] < postorder[i]) {
                j++;
            }
            for (int k = j; k < i; k++) {
                if (postorder[k] < postorder[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
