package com.example.testmaven;

import android.telephony.emergency.EmergencyNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 二叉搜索树转双向链表
     * 中序遍历为递增顺序
     *
     * @param root
     * @return
     */
    TreeNode head, pre;

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }
        treeToDoublyListHelper(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    public void treeToDoublyListHelper(TreeNode cur) {
        if (cur == null) {
            return;
        }
        treeToDoublyListHelper(cur.left);
        if (pre == null) {
            head = cur;
        } else {
            pre.right = cur;
            cur.left = pre;
        }
        pre = cur;
        treeToDoublyListHelper(cur.right);
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


    /**
     * 二叉树中和为某一值的路径
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        int subSum = 0;
        pathSumDfs(root, sum, res, data, subSum);
        return res;
    }

    private void pathSumDfs(TreeNode node, int sum, List<List<Integer>> res, List<Integer> data, int subSum) {
        if (node == null) {
            return;
        }
        data.add(node.val);
        if ((subSum + node.val) == sum && node.left == null && node.right == null) {
            res.add(new ArrayList<>(data));
        }
        pathSumDfs(node.left, sum, res, data, subSum + node.val);
        pathSumDfs(node.right, sum, res, data, subSum + node.val);
        data.remove(data.size() - 1);
    }


    /**
     * 重建二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left,
                                int preorder_right, int inorder_left, int inorder_right, Map<Integer, Integer> map) {
        if (preorder_left > preorder_right) {
            return null;
        }
        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = map.get(preorder[preorder_root]);
        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1,
                preorder_left + size_left_subtree, inorder_left, inorder_root - 1, map);
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder,
                preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right, map);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1, indexMap);
    }

    /**
     * 判断二叉树是否是平衡二叉树
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return false;
        }
        if(root.left == null && root.right == null){
            return true;
        }
        if (Math.abs(getHeight(root.left)-getHeight(root.right))>11){
            return true;
        }
        return isBalanced(root.left)&&isBalanced(root.right);

    }

    //获得结点的最大深度
    public int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int lh = getHeight(node.left) + 1;
        int rh = getHeight(node.right) + 1;
        return Math.max(lh,rh);
    }


    class Solution {

        HashMap<Integer,Integer> memo = new HashMap<>();
        int[] post;

        public TreeNode buildTree(int[] inorder, int[] postorder) {
            for(int i = 0;i < inorder.length; i++) memo.put(inorder[i], i);
            post = postorder;
            TreeNode root = buildTree(0, inorder.length - 1, 0, post.length - 1);
            return root;
        }

        /**
         * \
         * @param is 中序遍历左子树的起点  innerStart
         * @param ie 中序遍历右子树的终点 innerEnd
         *           is-ie就是当前跟结点子树的范围
         * @param ps 后续遍历左子树的起点  postStart
         * @param pe 后序遍历右子树的终点  postEnd
         * @return
         */
        public TreeNode buildTree(int is, int ie, int ps, int pe) {
            if(ie < is || pe < ps) return null;

            //根结点的值
            int root = post[pe];
            //根结点在中序遍历中的索引
            int ri = memo.get(root);

            TreeNode node = new TreeNode(root);
            node.left = buildTree(is, ri - 1, ps, ps + ri - is - 1);
            node.right = buildTree(ri + 1, ie, ps + ri - is, pe - 1);
            return node;
        }
    }

    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
          if(nums[i] != i){
              int index = i;
              if (nums[i]==nums[nums[i]]){
                  return nums[i];
              }
              while (nums[index] != i){
                  index ++;
              }
              int temp = nums[i];
              nums[i] = nums[index];
              nums[index] = temp;
          }
        }
        return 0;
    }
}
