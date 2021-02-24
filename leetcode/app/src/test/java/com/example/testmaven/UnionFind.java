package com.example.testmaven;

/**
 * 并查集
 *
 * @author heshufan
 * @date 2021/2/15
 */

class UnionFind {
    //parent[i]存放的为i的父元素
    int[] parent;
    //ran[i]存放的为i作为根结点的树的最大深度（如果不是根结点，则是i作为根结点的子树的最大深度）
    int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
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
            parent[x] = find(find(x));
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
        if (rank[x] <= rank[y]) {
            parent[i] = y;
        } else {
            parent[j] = x;
        }
        if (rank[x] == rank[y] && x != y) {
            rank[y]++;
        }
    }
}
