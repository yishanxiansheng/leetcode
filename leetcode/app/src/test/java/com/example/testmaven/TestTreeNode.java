package com.example.testmaven;

import android.content.Intent;

import java.sql.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class TestTreeNode {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(
                new Integer[]{3, 9, 10, null, null, 15, 7}));
        TreeNode node2 = createBianryTree(linkedList);
        System.out.println(serialize(node2));
        preOrderTraveralWithStack(node2);
        TreeNode node = sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});


    }

    /**
     * 有序数组转化为二叉搜索树
     *
     * @param nums
     * @return
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    public static TreeNode sortedArrayToBSTHelper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int middle = (left + right) / 2;
        TreeNode node = new TreeNode(nums[middle]);
        node.left = sortedArrayToBSTHelper(nums, left, middle - 1);
        node.right = sortedArrayToBSTHelper(nums, middle + 1, right);
        return node;
    }

    /**
     * 前序遍历
     *
     * @param treeNode
     */
    public static void preOrderTraveralWithStack(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);
        TreeNode node = stack.peek();
        while (!stack.empty()) {
            while (node != null) {
                //入栈之前打印
                System.out.println(node.val);
                stack.push(node);
                node = node.left;
            }

            if (!stack.empty()) {
                node = stack.pop();
                node = node.right;

            }
        }
    }

    /**
     * 构造完全二叉树
     *
     * @return
     */
    public static TreeNode createBianryTree(LinkedList<Integer> inputLists) {
        TreeNode node = null;
        if (inputLists == null || inputLists.size() == 0) {
            return null;
        }
        Integer data = inputLists.removeFirst();
        if (data != null) {
            node = new TreeNode(data);
            node.left = createBianryTree(inputLists);
            node.right = createBianryTree(inputLists);
        }

        return node;
    }

    /**
     * 判断是否为一个树的子树
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t != null) {
            return false;
        }

        return isSameTree(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    /**
     * 判断两个树是否相同
     *
     * @param a
     * @param b
     * @return
     */
    public boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        return a.val == b.val && isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }

    /**
     * 对称二叉树
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isSymmetric2(root.left, root.right);
    }

    /**
     * 两个树是否对称
     *
     * @param a
     * @param b
     * @return
     */
    public boolean isSymmetric2(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }

        return a.val == b.val && isSymmetric2(a.left, b.right) && isSymmetric2(a.right, b.left);
    }

    /**
     * 翻转二叉树
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root != null) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
    }

    /**
     * 二叉搜索树转换累加树
     *
     * @param root
     * @return
     */
    int num = 0;

    public TreeNode bstToGst(TreeNode root) {
        //中序遍历
        if (root == null) {
            return null;
        }
        bstToGst(root.right);
        num = root.val + num;
        root.val = num;
        bstToGst(root.left);
        return root;
    }

    /**
     * 合并两个二叉树
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }

        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.val = t1.val + t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    /**
     * 递增顺序查找树
     *
     * @param root
     * @return
     */
    TreeNode head = new TreeNode(0);
    TreeNode real = head;

    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        root.right = increasingBST(root.left);
        real.right = new TreeNode(root.val);
        real = real.right;
        increasingBST(root.right);
        return head.right;
    }

    /**
     * 二叉树的坡度
     *
     * @param root
     * @return
     */
    int num2 = 0;

    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        num2 += Math.abs(findTilt2(root.right) - findTilt2(root.left));
        findTilt(root.right);
        findTilt(root.left);
        return num2;
    }

    /**
     * 计算所有子结点和
     *
     * @param a
     * @return
     */
    public int findTilt2(TreeNode a) {
        if (a == null) {
            return 0;
        }
        return a.val + findTilt2(a.left) + findTilt2(a.right);
    }

    /**
     * 单值二叉树
     *
     * @param root
     * @return
     */
    int num3 = 0;

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        num3 = root.val;
        return isUnivalTree2(root);
    }

    public boolean isUnivalTree2(TreeNode root) {
        if (root != null) {
            return root.val == num3 && isUnivalTree2(root.left) && isUnivalTree2(root.right);
        }
        return true;
    }

    public char firstUniqChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            if (s.indexOf(a) == i && s.indexOf(a, i) == -1) {
                return a;
            }
        }
        return ' ';
    }

    /**
     * 单调队列
     * 每次入队列，看队首的值是不是在该i-k,i+1之间,不在则将这出队列
     * 如果队尾小于入队的值，队尾的值出队列直到队尾的值大于将要入队列的值
     * 以此保证队首的值为当前区间的最大值
     *
     * @param nums
     * @param k
     * @return 删除的一段为队首，插入的一段为队尾
     * https://blog.csdn.net/sinat_40471574/article/details/90577147?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        if (k == 0 || nums.length == 0) {
            return new int[0];
        }
        //双端队列,记录的是nums中数字的下标
        Deque<Integer> deque = new ArrayDeque<>();

        //区间在i-k+1,i
        //构造递增队列，队首为最大值
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                deque.offerLast(i);
                result[0] = nums[0];
            } else {
                //检查队首的下标是否在i-k+1到i之间,否则去除
                if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                    deque.removeFirst();
                }

                //将小于nums[i]的值都去掉
                while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                    deque.removeLast();
                }
                deque.offerLast(i);

                //从i-k+1开始有最大值
                if (i - k + 1 >= 0) {
                    result[i - k + 1] = nums[deque.getFirst()];
                }
            }
        }
        return result;
    }

    public boolean isStraight(int[] nums) {
        int zeroNum = 0;
        int num = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroNum += 1;
            } else {
                if (i < 4 && nums[i + 1] != 0) {
                    num += Math.abs(nums[i + 1] - nums[i]);
                    if (nums[i + 1] == nums[i]) {
                        return false;
                    }
                }
            }
        }
        if (zeroNum <= 1 && num == 4) {
            return true;
        }
        if (zeroNum == 2 && num <= 4) {
            return true;
        }
        return false;
    }

    /**
     * 二分法在排序数组中查找数字
     *
     * @param nums
     * @param target
     * @return target数字出现的个数
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        int result = 0;
        //二分法这里是等号
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            }

            if (nums[mid] == target) {
                break;
            }
        }
        int i = mid;
        while (i > -1 && nums[i] == target) {
            result++;
            i--;
        }
        i = mid;
        while (i < nums.length && nums[i + 1] == target) {
            result++;
            i++;
        }
        return result;
    }

    /**
     * 数学解决约瑟夫环问题
     *
     * @param n
     * @param m https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/solution/javajie-jue-yue-se-fu-huan-wen-ti-gao-su-ni-wei-sh/
     * @return
     */
    public int lastRemaining(int n, int m) {
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }

    /**
     * 获取树的最大深度
     *
     * @param treeNode
     * @return
     */
    public static int getMaxDeep(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        return Math.max(getMaxDeep(treeNode.left) + 1, getMaxDeep(treeNode.right) + 1);
    }

    /**
     * 获取二叉树的最小深度
     * 层序遍历遍历到的第一个叶子节点的深度就是最小深度
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        int result = 0;
        treeNodes.add(root);
        while (!treeNodes.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            result++;
            int size = treeNodes.size();
            while (size > 0) {
                TreeNode top = treeNodes.pollFirst();
                temp.add(top.val);
                if (top.left != null) {
                    treeNodes.add(top.left);
                }
                if (top.right != null) {
                    treeNodes.add(top.right);
                }
                if (top.left == null && top.right == null) {
                    return result;
                }
                size--;
            }
        }
        return result;
    }

    /**
     * 层序遍历树,必须分层
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        if (root == null) {
            return result;
        }
        treeNodes.add(root);
        int size = treeNodes.size();
        while (!treeNodes.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            size = treeNodes.size();
            while (size > 0) {
                TreeNode top = treeNodes.pollFirst();
                temp.add(top.val);
                if (top.left != null) {
                    treeNodes.add(top.left);
                }
                if (top.right != null) {
                    treeNodes.add(top.right);
                }
                size--;
            }
            //从尾部依次插入
//            result.add(0,temp);
            result.add(temp);
        }
        return result;
    }

    /**
     * 之字型遍历树
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder4(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> treeNodes = new ArrayDeque<>();
        if (root == null) {
            return result;
        }
        treeNodes.add(root);
        int size = treeNodes.size();
        while (!treeNodes.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            size = treeNodes.size();
            Deque<TreeNode> tempStack = new ArrayDeque<>();
            while (size > 0) {
                TreeNode top = treeNodes.poll();
                temp.add(top.val);
                if (top.left != null) {
                    tempStack.add(top.left);
                }
                if (top.right != null) {
                    tempStack.add(top.right);
                }
                size--;
            }
            treeNodes = tempStack;
            //从尾部依次插入
//            result.add(0,temp);
            result.add(temp);
        }
        return result;
    }

    /**
     * 层序遍历树 不分层只打印
     *
     * @param root
     * @return
     */
    public int[] levelOrder2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        if (root == null) {
            return new int[0];
        }
        treeNodes.add(root);
        while (!treeNodes.isEmpty()) {
            TreeNode top = treeNodes.pollFirst();
            list.add(top.val);
            if (top.left != null) {
                treeNodes.add(top.left);
            }
            if (top.right != null) {
                treeNodes.add(top.right);
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 之字型遍历树
     *
     * @param root
     * @return
     */
    public  static List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        if (root == null) {
            return result;
        }
        treeNodes.add(root);
        int size = treeNodes.size();
        //行数  单数从左到右添加   双数从右到左添加
        int rows = 1;
        while (!treeNodes.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            size = treeNodes.size();
            while (size > 0) {
                TreeNode top = treeNodes.pollFirst();
                temp.add(top.val);
                if (top.left != null) {
                    treeNodes.add(top.left);
                }
                if (top.right != null) {
                    treeNodes.add(top.right);
                }
                size--;
            }
            if (rows % 2 == 0) {
                Collections.reverse(temp);
            }
            rows++;
            result.add(temp);
        }
        return result;
    }

    /**
     * 树的镜像
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(left);
        return root;
    }

    /**
     * 前序遍历
     *
     * @param root
     */
    private List<Integer> list = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return list;
        }
        list.add(root.val);
        inorderTraversal(root.left);
        inorderTraversal(root.right);
        return list;
    }

    /**
     * 深度优先搜索 利用栈实现
     *
     * @param root
     * @return
     */
    public List<Integer> DFSTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> treeNodes = new Stack<>();
        if (root == null) {
            return list;
        }
        treeNodes.add(root);

        while (!treeNodes.isEmpty()) {
            TreeNode temp = treeNodes.pop();
            list.add(temp.val);
            if (temp.right != null) {
                treeNodes.add(temp.right);
            }
            if (temp.left != null) {
                treeNodes.add(temp.left);
            }
        }
        return list;
    }

    //递归实现
    List<Integer> DFSTraversal2 = new ArrayList<>();

    public List<Integer> DFSTraversal2(TreeNode root) {
        DFSTraversalHelper(root);
        return DFSTraversal2;
    }

    public void DFSTraversalHelper(TreeNode root) {
        if (root == null) {
            return;
        }
        DFSTraversal2.add(root.val);
        DFSTraversal2(root.left);
        DFSTraversal2(root.right);
    }

    /**
     * 广度优先搜索 利用队列实现
     * 其实就是层序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> BFSTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        if (root == null) {
            return list;
        }
        treeNodes.add(root);
        int length = 1;
        while (!treeNodes.isEmpty()) {
            TreeNode temp = treeNodes.pollFirst();
            list.add(temp.val);
            if (temp.left != null) {
                treeNodes.add(temp.left);
            }
            if (temp.right != null) {
                treeNodes.add(temp.right);
            }
        }
        return list;
    }

<<<<<<< HEAD

=======
>>>>>>> callback
    /**
     * 最近公共祖先   后续遍历
     * https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/solution/mian-shi-ti-68-ii-er-cha-shu-de-zui-jin-gong-gon-7/
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //后序遍历 一般是结点a的子树遍历完了后会执行到这里
        //最近公共祖先 、p、q三个结点必定在一个结点的左子树或者右子树
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    /**
     * 二叉搜索树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }

    /**
     * 二叉搜索树的第k大节点 二叉搜索树的中序编列是递增的
     *
     * @param root
     * @param k
     * @return
     */

    List<Integer> lists = new ArrayList<>();

    public int kthLargest(TreeNode root, int k) {
        if (root == null) return 0;
        kthLargestHelper(root);
        return lists.get(lists.size() - k);
    }

    public void kthLargestHelper(TreeNode root) {
        if (root == null) return;
        kthLargestHelper(root.left);
        lists.add(root.val);
        kthLargestHelper(root.right);
    }

<<<<<<< HEAD

=======
>>>>>>> callback
    /**
     * 根据前序遍历以及中序遍历构建树
     *
     * @param preorder 前序遍历
     * @param inorder  中序遍历
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        //存储中序遍历的结点数据
        Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        int length = preorder.length;
        for (int i = 0; i < length; i++) {
            indexMap.put(inorder[i], i);
        }
        TreeNode root = buildTree(preorder, 0,
                length - 1, 0, length - 1, indexMap);
        return root;
    }

    /**
     * @param preorder
     * @param preorderStart
     * @param preorderEnd
     * @param inorderStart
     * @param inorderEnd
     * @param indexMap
     * @return
     */
    public TreeNode buildTree(int[] preorder, int preorderStart, int preorderEnd, int inorderStart,
                              int inorderEnd, Map<Integer, Integer> indexMap) {
        if (preorderStart > preorderEnd) {
            return null;
        }
        int rootVal = preorder[preorderStart];
        TreeNode root = new TreeNode(rootVal);
        //只剩下一个结点，代表结点为叶子
        if (preorderStart == preorderEnd) {
            return root;
        } else {
            //找出根结点在中序遍历数组中的位置
            int rootIndex = indexMap.get(rootVal);
            //root的左子树的结点的个数   root的右子树的结点的个数
            int leftNodes = rootIndex - inorderStart, rightNodes = inorderEnd - rootIndex;
            //root 左子树的前序遍历区间 preorderStart + 1： preorderStart + leftNodes，中序遍历区间：inorderStart,：rootIndex - 1
            //root 右子树的前序遍历区间 preorderEnd - rightNodes + 1： preorderEnd，中序遍历区间：rootIndex + 1, inorderEnd
            TreeNode leftSubtree = buildTree(preorder, preorderStart + 1, preorderStart + leftNodes, inorderStart, rootIndex - 1, indexMap);
            TreeNode rightSubtree = buildTree(preorder, preorderEnd - rightNodes + 1, preorderEnd, rootIndex + 1, inorderEnd, indexMap);
            root.left = leftSubtree;
            root.right = rightSubtree;
            return root;
        }
    }

<<<<<<< HEAD

=======
>>>>>>> callback
    /**
     * 中序遍历  非递归
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }


            root = stack.pop();
            list.add(root.val);
            root = root.right;

            //1、root 不为空的时候，将root的左结点依次入栈，最后出栈顶结点，进行打印
            //2、root为空代表栈顶结点的右结点为空，说明这个节点下面的左右结点都已经遍历完，这个时候需要回到这个节点的父节点
            //因为root为null，所以就开始遍历父节点的右结点了
        }
        return list;
    }

    /**
     * 序列化二叉树
     *
     * @param root
     * @return
     */
    public static StringBuffer result = new StringBuffer();

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return null;
    }

    public static String serialize(TreeNode root) {
        result.append("[");
        serializeHelper(root);
        result.append("]");
        return result.toString();
    }

    public static void serializeHelper(TreeNode root) {
        if (root == null) {
            result.append(",null");
            return;
        }
        result.append(root.val + ",");
        serializeHelper(root.left);
        serializeHelper(root.right);
    }

<<<<<<< HEAD

    /**
     * 二叉树的直径
     * 每个结点所构成树的左子树的最大深度+右子树构成的最大深度
=======
    /**
     * 二叉树的层平均值
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> lists = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        //这一层的结点的个数
        int acount = 1;
        //一层的总和
        double total = 0;
        stack.add(root);
        while (!stack.isEmpty()) {
            acount = stack.size();
            total = 0;
            int mAcount = acount;
            while (acount > 0) {
                TreeNode node = stack.pollFirst();
                total += node.val;
                if (node.left != null) {
                    stack.add(node.left);
                }
                if (node.right != null) {
                    stack.add(node.right);
                }
                acount--;
            }
            double a = total / mAcount;
            lists.add(a);
        }
        return lists;
    }

    /**
     * 两数之和
     * 中序遍历加双指针
     *
     * @param root
     * @param k
     * @return
     */
    List<Integer> temp = new ArrayList<>();

    public boolean findTarget(TreeNode root, int k) {
        inOrder(root);
        int left = 0, right = temp.size() - 1;
        while (left < right) {
            if (temp.get(left) + temp.get(right) == k) {
                return true;
            } else if (temp.get(left) + temp.get(right) > k) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        temp.add(root.val);
        inOrder(root.right);

    }

    /**
     * 左叶子之和
>>>>>>> callback
     *
     * @param root
     * @return
     */
<<<<<<< HEAD
    public static int diameterOfBinaryTree(TreeNode root) {
        int max = 0;
        Stack<TreeNode> treeNodes = new Stack<>();
        if (root == null) {
            return 0;
        }
        treeNodes.add(root);
        while (!treeNodes.isEmpty()) {
            TreeNode treeNode = treeNodes.pop();
            int temp = getMaxDeep(treeNode.left) + getMaxDeep(treeNode.right);
            max = max > temp ? max : temp;
            if (treeNode.left != null) {
                treeNodes.add(treeNode.left);
            }
            if (treeNode.right != null) {
                treeNodes.add(treeNode.right);
            }
        }
        return max;

    }

    /**
     * 二叉树转化为单链
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left == null) {
                root = root.right;
            }else {
                TreeNode pre = root.left;
                while (pre.right != null){
                    pre = pre.right;
                }
                pre.right = root.right;
                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }
=======
    private int ss = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left != null && root.left.left == null && root.left.right == null) {
            ss += root.left.val;
        }
        sumOfLeftLeaves(root.left);
        sumOfLeftLeaves(root.right);
        return ss;
    }

    /**
     * 路径总和
     * 深度优先搜索（中序遍历）
     * 递归
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
>>>>>>> callback
    }
}
