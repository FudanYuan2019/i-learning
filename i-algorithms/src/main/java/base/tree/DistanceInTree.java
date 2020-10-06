package base.tree;

import util.PrintUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形DP
 * @Author: Jeremy
 * @Date: 2020/10/6 11:47
 */
public class DistanceInTree {
    private int[] ans;
    private int[] sz;
    private int[] dp;
    private List<List<Integer>> graph;

    public static void main(String[] args) {
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}};
        int N = 6;

        DistanceInTree distanceInTree = new DistanceInTree();
        int[] sumOfDis = distanceInTree.sumOfDistancesInTree(N, edges);
        PrintUtil.print(sumOfDis);
    }

    /**
     * LeetCode 834. 树中距离之和
     * 给定一个无向、连通的树。树中有 N 个标记为 0...N-1 的节点以及 N-1 条边 。
     * <p>
     * 第 i 条边连接节点 edges[i][0] 和 edges[i][1] 。
     * <p>
     * 返回一个表示节点 i 与其他所有节点距离之和的列表 ans。
     * <p>
     * 示例 1:
     * <p>
     * 输入: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
     * 输出: [8,12,6,10,10,10]
     * 解释:
     * 如下为给定的树的示意图：
     *   0
     *  / \
     * 1   2
     *    /|\
     *   3 4 5
     * <p>
     * 我们可以计算出 dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
     * 也就是 1 + 1 + 2 + 2 + 2 = 8。 因此，answer[0] = 8，以此类推。
     *
     * @param N
     * @param edges
     * @return
     */
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        ans = new int[N];
        sz = new int[N];
        dp = new int[N];
        graph = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge: edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        postOrder(0, -1);
        preOrder(0, -1);
        return ans;
    }

    private void postOrder(int u, int f) {
        sz[u] = 1;
        dp[u] = 0;
        for (int v: graph.get(u)) {
            if (v == f) {
                continue;
            }
            postOrder(v, u);
            dp[u] += dp[v] + sz[v];
            sz[u] += sz[v];
        }
    }

    private void preOrder(int u, int f) {
        ans[u] = dp[u];
        for (int v: graph.get(u)) {
            if (v == f) {
                continue;
            }
            int pu = dp[u], pv = dp[v];
            int su = sz[u], sv = sz[v];

            dp[u] -= dp[v] + sz[v];
            sz[u] -= sz[v];
            dp[v] += dp[u] + sz[u];
            sz[v] += sz[u];

            preOrder(v, u);

            dp[u] = pu;
            dp[v] = pv;
            sz[u] = su;
            sz[v] = sv;
        }
    }
}
