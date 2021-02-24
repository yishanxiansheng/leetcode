package com.example.testmaven;

/**
 * 并查集
 *
 * @author heshufan
 * @date 2021/2/15
 */

class TestMergeFindList {


    public static void main(String[] args) {
        minSwapsCouples(new int[]{0, 2, 1, 3});
    }

    /**
     * 情侣
     * 如果情侣都没有坐在一起，那么最少交换次数 = 情侣对数-1
     * 最小交换次数= 交换之后连通块的个数  - 交换之前连通块的个数
     * 交换之后连通块的个数就是情侣的对数
     * 计算交换之前连通块的个数
     *
     * @param row
     * @return
     */
    public static int minSwapsCouples(int[] row) {
        int length = row.length;
        int N = length / 2;
        UnionFind find = new UnionFind(N);
        for (int i = 0; i < length; i += 2) {
            find.merge(row[i] / 2, row[i + 1] / 2);
        }
        return N - find.getCount();

    }

    static class UnionFind {
        //parent[i]存放的为i的父元素
        int[] parent;
        //连通块的个数
        int count = 0;

        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int getCount() {
            return count;
        }

        /**
         * 找到最终的父结点，也就是树的根结点
         *
         * @param x
         * @return
         */
        public int find(int x) {
            if (parent[x] == x) {
                return x;
            } else {
                //将x的最终根结点直接设置为x的父节点，防止出现链，让树的深度尽可能小
                parent[x] = find(parent[x]);
                return parent[x];
            }
        }

        /**
         * 合并两个集合
         *
         * @param i
         * @param j
         */
        public void merge(int i, int j) {
            //先找出根结点
            int x = find(i);
            int y = find(j);
            if (x == y) {
                return;
            }
            parent[x] = y;
            count--;
        }
    }
}
