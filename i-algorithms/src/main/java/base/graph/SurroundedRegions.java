package base.graph;

import base.unionfind.UnionFind;
import util.Point;
import util.PrintUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 连通区域
 *
 * @Author: Jeremy
 * @Date: 2020/9/14 14:45
 */
public class SurroundedRegions {

    private static final int[] X = new int[]{0, 0, 1, -1};
    private static final int[] Y = new int[]{1, -1, 0, 0};
    private int[][] visited;

    /**
     * LeetCode 130. 连通区域
     * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
     * <p>
     * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     * <p>
     * 示例:
     * <p>
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * 运行你的函数后，矩阵变为：
     * <p>
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     * 解释:
     * <p>
     * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
     * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
     * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
     * <p>
     * 解决方案一：使用BFS或者DFS，先将与边界上'O'相连的'O'替换成'#'，然后替换剩下的'O'为'X',最后再将'#'替换为'O'
     * 解决方案二：使用并查集，只替换与虚拟节点不相连的'O'。
     *
     * @param board
     */
    public void solveI(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }

        int M = board.length;
        int N = board[0].length;
        visited = new int[M][N];
        // 替换左右两列的'O'
        for (int i = 0; i < M; i++) {
            if (board[i][0] == 'O') {
                // bfs(board, M, N, i, 0);
                dfs(board, M, N, i, 0);
            }
            if (board[i][N - 1] == 'O') {
                // bfs(board, M, N, i, N - 1);
                dfs(board, M, N, i, N - 1);
            }
        }
        // 替换上下两行的'O'
        for (int j = 0; j < N; j++) {
            if (board[0][j] == 'O') {
                // bfs(board, M, N, 0, j);
                dfs(board, M, N, 0, j);
            }
            if (board[M - 1][j] == 'O') {
                // bfs(board, M, N, M - 1, j);
                dfs(board, M, N, M - 1, j);
            }
        }

        // 替换'O'->'X'
        replaceAll(board, M, N, 'O', 'X');
        // 替换'#'->'O'
        replaceAll(board, M, N, '#', 'O');
    }

    private void bfs(char[][] board, int M, int N, int x, int y) {
        if (visited[x][y] == 1) {
            return;
        }

        visited[x][y] = 1;
        board[x][y] = '#';
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));
        while (!queue.isEmpty()) {
            Point top = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newX = top.getX() + X[i];
                int newY = top.getY() + Y[i];
                if (isValid(board, M, N, newX, newY, visited)) {
                    Point newPoint = new Point(newX, newY);
                    queue.offer(newPoint);
                    board[newX][newY] = '#';
                    visited[newX][newY] = 1;
                }
            }
        }
    }

    private void dfs(char[][] board, int M, int N, int x, int y) {
        visited[x][y] = 1;
        board[x][y] = '#';
        for (int i = 0; i < 4; i++) {
            int newX = x + X[i];
            int newY = y + Y[i];
            if (isValid(board, M, N, newX, newY, visited)) {
                board[newX][newY] = '#';
                visited[newX][newY] = 1;
                dfs(board, M, N, newX, newY);
            }
        }
    }

    private boolean isValid(char[][] board, int M, int N, int x, int y, int[][] visited) {
        if (x < 0 || y < 0 || x >= M || y >= N || visited[x][y] == 1) {
            return false;
        }
        if ((board[x][y] != 'O' && board[x][y] != '#')) {
            return false;
        }
        return true;
    }

    private void replaceAll(char[][] board, int M, int N, char from, char to) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == from) {
                    board[i][j] = to;
                }
            }
        }
    }

    /**
     * 使用并查集
     *
     * @param board
     */
    public void solveII(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }

        int M = board.length;
        int N = board[0].length;
        int dummy = M * N;
        UnionFind unionFind = new UnionFind(M * N + 1);
        // 替换左右两列的'O'
        for (int i = 0; i < M; i++) {
            if (board[i][0] == 'O') {
                unionFind.union(i * N, dummy);
            }
            if (board[i][N - 1] == 'O') {
                unionFind.union(i * N + N - 1, dummy);
            }
        }
        // 替换上下两行的'O'
        for (int j = 0; j < N; j++) {
            if (board[0][j] == 'O') {
                unionFind.union(j, dummy);
            }
            if (board[M - 1][j] == 'O') {
                unionFind.union((M - 1) * N + j, dummy);
            }
        }

        for (int i = 1; i < M - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                if (board[i][j] == 'O') {
                    for (int m = 0; m < 4; m++) {
                        int newI = i + X[m];
                        int newJ = j + Y[m];
                        if (board[newI][newJ] == 'O') {
                            unionFind.union(i * N + j, newI * N + newJ);
                        }
                    }
                }
            }
        }

        // 所有不和 dummy 连通的 O，都要被替换
        for (int i = 1; i < M - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                if (!unionFind.connected(dummy, i * N + j)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'O', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };

        SurroundedRegions surroundedRegions = new SurroundedRegions();
        surroundedRegions.solveI(board);
        PrintUtil.print(board);

        surroundedRegions.solveII(board);
        PrintUtil.print(board);
    }
}
