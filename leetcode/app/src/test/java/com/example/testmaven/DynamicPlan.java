package com.example.testmaven;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hesf001
 * @description:
 * @date :2020/10/19 11:12
 */
public class DynamicPlan {
    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 2, 5, 7, 6, 9};
        quickSort(array);
        System.out.println(Arrays.toString(array));

        getTotalIsX1(array, 4);

        int a = 2, b = 2;
        System.out.println(b ^ a ^ a);
    }

    /**
     * 剪绳子
     *
     * @param n
     * @return 乘积的最小值
     */
    public int cuttingRope(int n) {
        //dp[i]代表i剪断后的最大值
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                //剩下的j继续分
                int temp = dp[j];
                //剩下的j不分
                int temp1 = j;
                dp[i] = Math.max(dp[i], Math.max(temp1, temp) * (i - j));
            }
        }
        return dp[n];
    }

    /**
     * 01背包问题  放或者不放
     *
     * @param W   W[i]表示第i个物品的重量
     * @param V   V[i]表示第i个物品的价值
     * @param MAX 包的最大容量
     * @return
     */
    public int package01(int[] W, int[] V, int MAX) {
        //dp[i][j]表示前i个物品放入限重为j的包中的最大值
        int[][] dp = new int[W.length - 1][MAX];

        for (int i = 0; i < W.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= MAX; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i < W.length; i++) {
            for (int j = 1; j <= MAX; j++) {
                if (j > W[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W[i]] + V[i]);
//                    完全背包   每种物品有无限个
//                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - W[i]] + V[i]);

                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[W.length - 1][MAX];
    }

    /**
     * 所有路径的数量
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] methods = new int[m][n];
        for (int i = 0; i < m; i++) {
            methods[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            methods[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                methods[i][j] = methods[i - 1][j] + methods[i][j - 1];
            }
        }

        return methods[m - 1][n - 1];
    }


    /**
     * 最小路径
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int[][] mine = new int[grid.length][grid[0].length];

        //行
        for (int i = 0; i < grid.length; i++) {
            mine[i][0] = i == 0 ? grid[0][0] : grid[i][0] + mine[i - 1][0];
        }

        //列
        for (int i = 0; i < grid[0].length; i++) {
            mine[0][i] = i == 0 ? grid[0][0] : grid[0][i] + mine[0][i - 1];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                mine[i][j] = Math.min(mine[i - 1][j], mine[i][j - 1]) + grid[i][j];
            }
        }
        //min[行][列]
        return mine[grid.length - 1][grid[0].length - 1];
    }


    /**
     * @param array
     * @param x
     * @return
     */
    public static List<int[]> getTotalIsX1(int[] array, int x) {
        //方法1 暴力

        //方法2 快速排序
        List<int[]> lists = new ArrayList<>();
        //1、快速排序
        quickSort(array);

        int i = 0;
        int j = array.length - 1;

        while (i < j) {
            if (array[i] + array[j] > x) {
                j--;
            } else if (array[i] + array[j] == x) {
                int[] a = new int[]{array[i], array[j]};
                lists.add(a);
                System.out.println(array[i] + ":" + array[j]);
                i++;
                j--;
            } else {
                i++;
            }
        }

        return lists;
    }

    /**
     * @param array
     * @return
     */
    public static void quickSort(int[] array) {
        quickSortHelper(array, 0, array.length - 1);
    }

    /**
     * @param array
     * @return
     */
    public static void quickSortHelper(int[] array, int start, int end) {

        if (start >= end) {
            return;
        }
        int data = array[start];
        int i = start;
        int j = end;
        while (i < j) {
            //一定要先算j，然后算i
            //找到第一个比array[middle]小的
            while (i < j && array[j] >= data) {
                j--;
            }

            //找到第一个比array[middle]大的
            while (i < j && array[i] <= data) {
                i++;
            }


            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        array[start] = array[i];
        array[i] = data;
        quickSortHelper(array, start, i - 1);
        quickSortHelper(array, i + 1, end);
    }


    public int[] twoSum(int[] nums, int target) {
        int[] lists = new int[2];

        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            if (nums[i] + nums[j] > target) {
                j--;
            } else if (nums[i] + nums[j] == target) {
                int[] a = new int[]{nums[i], nums[j]};
                lists[0] = nums[i];
                lists[1] = nums[j];
                System.out.println(nums[i] + ":" + nums[j]);
                i++;
                j--;
            } else {
                i++;
            }
        }

        return lists;
    }

    /**
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
     * 股票的最大收益
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 1 || prices.length == 0) return 0;
        //a[i]代表第i天卖出股票得到的最大值
        int[] a = new int[prices.length];
        a[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                a[i] = a[i - 1] + prices[i] - prices[i - 1];
            } else {
                int temp = a[i - 1] - (prices[i - 1] - prices[i]);
                a[i] = temp < 0 ? 0 : temp;
            }
        }

        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result = result < a[i] ? a[i] : result;
        }
        return result;

    }
}
