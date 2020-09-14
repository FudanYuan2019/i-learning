package backtrack;

import util.PrintUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * 不同路径。LeetCode 62, 63, 980
 *
 * @Author: Jeremy
 * @Date: 2020/9/13 16:01
 */
public class UniquePath {
    private static final int[] X = new int[]{0, 0, 1, -1};
    private static final int[] Y = new int[]{1, -1, 0, 0};
    private int path;

    public static void main(String[] args) {
        UniquePath uniquePath = new UniquePath();
        int paths = uniquePath.uniquePaths(3, 2);
        PrintUtil.print(paths);

        paths = uniquePath.uniquePaths(7, 3);
        PrintUtil.print(paths);

        paths = uniquePath.uniquePaths(10, 10);
        PrintUtil.print(paths);

        int[][] board = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        paths = uniquePath.uniquePathsWithObstacles(board);
        PrintUtil.print(paths);

        paths = uniquePath.uniquePathsWithObstaclesI(board);
        PrintUtil.print(paths);

        int[][] grid = new int[][]{
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, -1}
        };
        paths = uniquePath.uniquePathsIII(grid);
        PrintUtil.print(paths);
    }

    /**
     * LeetCode 62. 不同路径
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * 问总共有多少条不同的路径？
     * <p>
     * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
     * <p>
     * 示例 1:
     * <p>
     * 输入: m = 3, n = 2
     * 输出: 3
     * 解释:
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向右 -> 向下
     * 2. 向右 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向右
     * 示例 2:
     * <p>
     * 输入: m = 7, n = 3
     * 输出: 28
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        // return (int)Cab(m + n - 2, n - 1);
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] += dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 求组合数
     *
     * @param a
     * @param b
     * @return
     */
    private long Cab(int a, int b) {
        long m = 1;
        long n = 1;
        for (int i = a; i >= a - b + 1; i--) {
            m *= i;
        }
        for (int i = 1; i <= b; i++) {
            n *= i;
        }
        return m / n;
    }

    /**
     * 63. 不同路径 II
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * <p>
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     * <p>
     * 说明：m 和 n 的值均不超过 100。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * [
     *   [0,0,0],
     *   [0,1,0],
     *   [0,0,0]
     * ]
     * 输出: 2
     * 解释:
     * 3x3 网格的正中间有一个障碍物。
     * 从左上角到右下角一共有 2 条不同的路径：
     * 1. 向右 -> 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右 -> 向右
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int M = obstacleGrid.length;
        int N = obstacleGrid[0].length;
        int[][] dp = new int[M][N];

        dp[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0;

        for (int i = 1; i < M; i++) {
            if (obstacleGrid[i][0] == 0) {
                dp[i][0] = dp[i - 1][0];
            } else {
                dp[i][0] = 0;
            }
        }

        for (int j = 1; j < N; j++) {
            if (obstacleGrid[0][j] == 0) {
                dp[0][j] = dp[0][j - 1];
            } else {
                dp[0][j] = 0;
            }
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return dp[M - 1][N - 1];
    }

    /**
     * LeetCode 63. 不同路径II
     * 使用单维数组
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstaclesI(int[][] obstacleGrid) {
        int M = obstacleGrid.length;
        int N = obstacleGrid[0].length;
        int[] dp = new int[N];
        dp[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int j = 1; j < N; j++) {
            dp[j] = obstacleGrid[0][j] == 0 ? dp[j - 1] : 0;
        }
        for (int i = 1; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    continue;
                }
                if (j >= 1 && obstacleGrid[i][j] == 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[N - 1];
    }

    /**
     * LeetCode 980. 不同路径 III
     * 在二维网格 grid 上，有 4 种类型的方格：
     * <p>
     * 1 表示起始方格。且只有一个起始方格。
     * 2 表示结束方格，且只有一个结束方格。
     * 0 表示我们可以走过的空方格。
     * -1 表示我们无法跨越的障碍。
     * 返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目。
     * <p>
     * 每一个无障碍方格都要通过一次，但是一条路径中不能重复通过同一个方格。
     * <p>
     * 示例 1：
     * <p>
     * 输入：[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
     * 输出：2
     * 解释：我们有以下两条路径：
     * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
     * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
     * 示例 2：
     * <p>
     * 输入：[[1,0,0,0],[0,0,0,0],[0,0,0,2]]
     * 输出：4
     * 解释：我们有以下四条路径：
     * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
     * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
     * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
     * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
     * 示例 3：
     * <p>
     * 输入：[[0,1],[2,0]]
     * 输出：0
     * 解释：
     * 没有一条路能完全穿过每一个空的方格一次。
     * 请注意，起始和结束方格可以位于网格中的任意位置。
     *
     * @param grid
     * @return
     */
    public int uniquePathsIII(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;

        Point start = null, end = null;
        Set<Point> candidates = new HashSet<>();
        Set<Point> obstacles = new HashSet<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                Point point = new Point(i, j);
                if (grid[i][j] == 1) {
                    start = point;
                } else if (grid[i][j] == 2) {
                    end = point;
                } else if (grid[i][j] == 0) {
                    candidates.add(point);
                } else {
                    obstacles.add(point);
                }
            }
        }

        int[][] visited = new int[M][N];
        assert start != null;
        visited[start.x][start.y] = 1;
        dfs(M, N, visited, start, end, candidates, obstacles);
        return path;
    }

    private void dfs(int M, int N, int[][] visited, Point current, Point end, Set<Point> candidates, Set<Point> obstacles) {
        if (current.equals(end)) {
            if (candidates.isEmpty()) {
                path++;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int newX = current.x + X[i];
            int newY = current.y + Y[i];
            Point next = new Point(newX, newY);
            if (isValid(M, N, newX, newY, visited) && !obstacles.contains(next)) {
                visited[newX][newY] = 1;
                candidates.remove(next);
                dfs(M, N, visited, next, end, candidates, obstacles);
                visited[newX][newY] = 0;
                candidates.add(next);
            }
        }
    }

    private boolean isValid(int M, int N, int x, int y, int[][] visited) {
        if (x >= M || y >= N || x < 0 || y < 0 || visited[x][y] == 1) {
            return false;
        }
        return true;
    }

    private static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            Point point = (Point) o;
            if (point == null) {
                return false;
            }
            return point.x == this.x && point.y == this.y;
        }

        @Override
        public int hashCode() {
            return this.x >> 2 + this.y >> 1;
        }
    }
}
