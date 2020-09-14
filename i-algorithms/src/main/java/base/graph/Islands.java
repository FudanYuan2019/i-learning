package base.graph;

import util.Point;
import util.PrintUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: Jeremy
 * @Date: 2020/9/14 15:57
 */
public class Islands {
    private static final int[] X = new int[]{0, 0, 1, -1};
    private static final int[] Y = new int[]{1, -1, 0, 0};
    private int[][] visited;
    private int maxArea = 0;

    /**
     * LeetCode 200. 岛屿数量
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * <p>
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
     * <p>
     * 此外，你可以假设该网格的四条边均被水包围。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * [
     * ['1','1','1','1','0'],
     * ['1','1','0','1','0'],
     * ['1','1','0','0','0'],
     * ['0','0','0','0','0']
     * ]
     * 输出: 1
     * 示例 2:
     * <p>
     * 输入:
     * [
     * ['1','1','0','0','0'],
     * ['1','1','0','0','0'],
     * ['0','0','1','0','0'],
     * ['0','0','0','1','1']
     * ]
     * 输出: 3
     * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int M = grid.length;
        int N = grid[0].length;
        visited = new int[M][N];

        int res = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (isValid(grid, M, N, i, j, visited)) {
                    res++;
                    bfs(grid, M, N, i, j);
                }
            }
        }
        return res;
    }

    private void bfs(char[][] grid, int M, int N, int x, int y) {
        if (visited[x][y] == 1) {
            return;
        }

        visited[x][y] = 1;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));
        while (!queue.isEmpty()) {
            Point top = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newX = top.getX() + X[i];
                int newY = top.getY() + Y[i];
                if (isValid(grid, M, N, newX, newY, visited)) {
                    Point newPoint = new Point(newX, newY);
                    queue.offer(newPoint);
                    visited[newX][newY] = 1;
                }
            }
        }
    }

    private boolean isValid(char[][] board, int M, int N, int x, int y, int[][] visited) {
        if (x < 0 || y < 0 || x >= M || y >= N || visited[x][y] == 1 || board[x][y] == '0') {
            return false;
        }
        return true;
    }

    /**
     * LeetCode 695. 岛屿的最大面积
     * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
     * <p>
     * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
     * 你可以假设 grid 的四个边缘都被 0（代表水）包围着。
     * <p>
     * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
     * <p>
     * 示例 1:
     * <p>
     * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
     * [0,0,0,0,0,0,0,1,1,1,0,0,0],
     * [0,1,1,0,1,0,0,0,0,0,0,0,0],
     * [0,1,0,0,1,1,0,0,1,0,1,0,0],
     * [0,1,0,0,1,1,0,0,1,1,1,0,0],
     * [0,0,0,0,0,0,0,0,0,0,1,0,0],
     * [0,0,0,0,0,0,0,1,1,1,0,0,0],
     * [0,0,0,0,0,0,0,1,1,0,0,0,0}}
     * 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
     * <p>
     * 示例 2:
     * <p>
     * [[0,0,0,0,0,0,0,0}}
     * 对于上面这个给定的矩阵, 返回 0。
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int M = grid.length;
        int N = grid[0].length;
        visited = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (isValid(grid, M, N, i, j, visited)) {
                    bfs(grid, M, N, i, j);
                }
            }
        }
        return maxArea;
    }

    private void bfs(int[][] grid, int M, int N, int x, int y) {
        if (visited[x][y] == 1) {
            return;
        }

        int area = 0;
        visited[x][y] = 1;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));
        while (!queue.isEmpty()) {
            Point top = queue.poll();
            area++;
            for (int i = 0; i < 4; i++) {
                int newX = top.getX() + X[i];
                int newY = top.getY() + Y[i];
                if (isValid(grid, M, N, newX, newY, visited)) {
                    Point newPoint = new Point(newX, newY);
                    queue.offer(newPoint);
                    visited[newX][newY] = 1;
                }
            }
        }
        if (area > maxArea) {
            maxArea = area;
        }
    }

    private boolean isValid(int[][] board, int M, int N, int x, int y, int[][] visited) {
        if (x < 0 || y < 0 || x >= M || y >= N || visited[x][y] == 1 || board[x][y] == 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        Islands islands = new Islands();
        int num = islands.numIslands(grid);
        PrintUtil.print(num);

        int[][] matrix = new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        int maxArea = islands.maxAreaOfIsland(matrix);
        PrintUtil.print(maxArea);
    }
}
