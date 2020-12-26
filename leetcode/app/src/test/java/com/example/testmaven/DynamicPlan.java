package com.example.testmaven;

/**
 * @author hesf001
 * @description:
 * @date :2020/10/19 11:12
 */
public class DynamicPlan {
    public static void main(String[] args) {

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

                }
            }
        }
        return dp[W.length - 1][MAX];
    }
}
