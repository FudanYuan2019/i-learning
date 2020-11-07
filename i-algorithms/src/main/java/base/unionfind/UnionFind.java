package base.unionfind;

import util.PrintUtil;

/**
 * 并查集算法
 *
 * @Author: Jeremy
 * @Date: 2020/9/14 14:08
 */
public class UnionFind {
    // 记录连通分量
    private int count;
    // 节点 x 的节点是 parent[x]
    private int[] parent;

    public UnionFind(int n) {
        this.count = n;
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
    }

    /**
     * 返回x的父亲节点
     *
     * @param x
     * @return
     */
    public int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    /**
     * x 与 y 进行连接
     *
     * @param x
     * @param y
     */
    public boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[x] = y;
            return false;
        }
        this.count--;
        return true;
    }

    /**
     * 判断 x 与 y 是否相连
     *
     * @param x
     * @param y
     * @return
     */
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    /**
     * 获取连通图的个数
     *
     * @return
     */
    public int getCount() {
        return this.count;
    }

    /**
     * 获取各个节点的父亲节点
     *
     * @return
     */
    public int[] getParents() {
        return this.parent;
    }

    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind(9);
        int[][] list = new int[][]{{1, 2}, {1, 3}, {5, 6}, {6, 7}, {5, 7}, {8, 9}};

        for (int[] tuple : list) {
            unionFind.union(tuple[0], tuple[1]);
        }
        PrintUtil.print(unionFind.getParents());

        boolean connected = unionFind.connected(2, 3);
        PrintUtil.print(connected);

        int count = unionFind.getCount();
        PrintUtil.print("连通图数量：" + count);
    }
}
