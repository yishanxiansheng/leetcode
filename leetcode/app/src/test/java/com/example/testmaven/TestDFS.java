package com.example.testmaven;

/**
 * @author heshufan
 * @date 2021/2/19
 */

public class TestDFS {
    public static void main(String[] agrs) {
        exist(new char[][]{{'C', 'A', 'A'}, {'A', 'A', 'A'}, {'B', 'C', 'D'}}, "AAB");
    }

    /**
     * 矩阵中的路径
     *
     * @param board
     * @param word
     * @return
     */
    public static boolean exist(char[][] board, String word) {
        for (int k = 0; k < board.length; k++) {
            for (int l = 0; l < board[0].length; l++) {
                int index = 0;
                boolean[][] visited = new boolean[board.length][board[0].length];
                if (dfs2(k, l, word, index, visited, board)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs2(int i, int j, String word, int index, boolean[][] visited, char[][] board) {
        if (i < 0 || i >= visited.length || j < 0 || j >= visited[0].length || visited[i][j] || board[i][j] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        boolean res1 = dfs2(i - 1, j, word, index + 1, visited, board) ||
                dfs2(i + 1, j, word, index + 1, visited, board) ||
                dfs2(i, j - 1, word, index + 1, visited, board) ||
                dfs2(i, j + 1, word, index + 1, visited, board);
        visited[i][j] = false;
        return res1;
    }

    /**
     * 机器人的运动范围
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        //记录数组是否被访问
        boolean[][] visited = new boolean[m][n];
        return dfs(m, n, k, m, n, visited);
    }

    private int dfs(int i, int j, int k, int m, int n, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= m || j >= n || get(i) + get(j) > k || visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        return 1 + dfs(i - 1, j, k, m, n, visited) + dfs(i, j - 1, k, m, n, visited)
                + dfs(i + 1, j, k, m, n, visited)
                + dfs(i, j + 1, k, m, n, visited);
    }


    //获取位数和
    private int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i + 1 < matrix.length && j + 1 < matrix[0].length && matrix[i][j]!=matrix[i+1][j+1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
