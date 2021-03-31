package com.example.testmaven;

/**
 * 前缀和
 *
 * @author heshufan
 * @date 2021/3/2
 */

class NumMatrix {
    int[][] dp;

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0){
            dp = null;
            return;
        }
        dp = new int[matrix.length + 1][matrix[0].length];
        for (int i = 1; i < dp[0].length; i++) {
            dp[1][i] = dp[1][i - 1] + matrix[0][i - 1];
        }

        for (int i = 1; i < dp.length; i++) {
            dp[i][1] = dp[i - 1][1] + matrix[i - 1][0];
        }

        for (int i = 2; i < dp.length; i++) {
            for (int j = 2; j < dp[0].length; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i-1][j-1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (dp == null){
            return 0;
        }
        return dp[row2 + 1][col2 + 1] - dp[row2+1][col1]-dp[row1][col2+1]+dp[row1][col1];
    }
}
