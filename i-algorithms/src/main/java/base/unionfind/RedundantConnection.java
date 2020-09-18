package base.unionfind;

import util.PrintUtil;

/**
 * 冗余连接
 *
 * @Author: Jeremy
 * @Date: 2020/9/17 18:54
 */
public class RedundantConnection {
    public static void main(String[] args) {
        int[][] edges = new int[][]{{1, 2}, {1, 3}, {2, 3}};
        RedundantConnection redundantConnection = new RedundantConnection();
        int[] res = redundantConnection.findRedundantConnection(edges);
        PrintUtil.print(res);
    }

    /**
     * LeetCode 684. 冗余连接
     * 在本问题中, 树指的是一个连通且无环的无向图。
     * <p>
     * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。
     * 附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
     * <p>
     * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
     * <p>
     * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。
     * 答案边 [u, v] 应满足相同的格式 u < v。
     * <p>
     * 示例 1：
     * <p>
     * 输入: [[1,2], [1,3], [2,3]]
     * 输出: [2,3]
     * 解释: 给定的无向图为:
     * 1
     *  / \
     * 2 - 3
     * 示例 2：
     * <p>
     * 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
     * 输出: [1,4]
     * 解释: 给定的无向图为:
     * 5 - 1 - 2
     * |   |
     * 4 - 3
     * 注意:
     * <p>
     * 输入的二维数组大小在 3 到 1000。
     * 二维数组中的整数在1到N之间，其中N是输入数组的大小。
     */
    public int[] findRedundantConnection(int[][] edges) {
        int[] res = new int[2];

        int N = edges.length;
        UnionFind unionFind = new UnionFind(N);

        for(int[] edge : edges) {
            if (unionFind.union(edge[0], edge[1])) {
                res = new int[]{edge[0], edge[1]};
            }
        }
        return res;
    }

    /**
     * LeetCode 685. 冗余连接II
     * 在本问题中，有根树指满足以下条件的有向图。该树只有一个根节点，所有其他节点都是该根节点的后继。
     * 每一个节点只有一个父节点，除了根节点没有父节点。
     * <p>
     * 输入一个有向图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。
     * 附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
     * <p>
     * 结果图是一个以边组成的二维数组。
     * 每一个边 的元素是一对 [u, v]，用以表示有向图中连接顶点 u 和顶点 v 的边，其中 u 是 v 的一个父节点。
     * <p>
     * 返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [[1,2], [1,3], [2,3]]
     * 输出: [2,3]
     * 解释: 给定的有向图如下:
     * 1
     * / \
     * v   v
     * 2-->3
     * 示例 2:
     * <p>
     * 输入: [[1,2], [2,3], [3,4], [4,1], [1,5]]
     * 输出: [4,1]
     * 解释: 给定的有向图如下:
     * 5 <- 1 -> 2
     * ^    |
     * |    v
     * 4 <- 3
     * 注意:
     * <p>
     * 二维数组大小的在3到1000范围内。
     * 二维数组中的每个整数在1到N之间，其中 N 是二维数组的大小。
     *
     * @param edges
     * @return
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] res = new int[2];
        return res;
    }
}
