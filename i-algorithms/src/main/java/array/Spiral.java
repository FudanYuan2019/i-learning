package array;

import util.PrintUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵相关题目，LeetCode 54, 59, 885
 *
 * @Author: Jeremy
 * @Date: 2020/9/5 10:56
 */
public class Spiral {
    private static final int RIGHT = 0, DOWN = 1, LEFT = 2, UP = 3;

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        Spiral spiral = new Spiral();
        List<Integer> res = spiral.spiralOrder(matrix);
        PrintUtil.print(res);

        int[][] newMatrix = spiral.generateMatrix(3);
        PrintUtil.print(newMatrix);

        int[][] location = spiral.spiralMatrixIII(5, 6, 1, 4);
        PrintUtil.print(location);
    }

    /**
     * LeetCode 54 螺旋矩阵
     * <p>
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * [
     * [ 1, 2, 3 ],
     * [ 4, 5, 6 ],
     * [ 7, 8, 9 ]
     * ]
     * 输出: [1,2,3,6,9,8,7,4,5]
     * 示例 2:
     * <p>
     * 输入:
     * [
     * [1, 2, 3, 4],
     * [5, 6, 7, 8],
     * [9,10,11,12]
     * ]
     * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
     *
     * @param matrix 输入矩阵
     * @return 按照顺时针螺旋顺序输出的列表元素列表
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int width = n, height = m;
        int x = 0, y = 0;
        int direct = RIGHT;

        int nums = m * n;
        List<Integer> res = new ArrayList<>(nums);

        for (int count = 0; count < nums; count++) {
            switch (direct) {
                case RIGHT:
                    res.add(matrix[x][y]);
                    y++;
                    if (y == width) {
                        x++;
                        y--;
                        direct = DOWN;
                    }
                    break;
                case DOWN:
                    res.add(matrix[x][y]);
                    x++;
                    if (x == height) {
                        y--;
                        x--;
                        height--;
                        direct = LEFT;
                    }
                    break;
                case LEFT:
                    res.add(matrix[x][y]);
                    y--;
                    if (y < n - width) {
                        y++;
                        x--;
                        width--;
                        direct = UP;
                    }
                    break;
                case UP:
                    res.add(matrix[x][y]);
                    x--;
                    if (x < m - height) {
                        x++;
                        y++;
                        direct = RIGHT;
                    }
                    break;
            }
        }
        return res;
    }

    /**
     * LeetCode 59 螺旋矩阵 II
     * <p>
     * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
     * <p>
     * 示例:
     * <p>
     * 输入: 3
     * 输出:
     * [
     * [ 1, 2, 3 ],
     * [ 8, 9, 4 ],
     * [ 7, 6, 5 ]
     * ]
     *
     * @param n 正整数
     * @return 生成的矩阵
     */
    public int[][] generateMatrix(int n) {
        if (n < 1) {
            return null;
        }

        int[][] matrix = new int[n][n];
        int direct = RIGHT;
        int x = 0, y = 0;
        int count = 1;
        int width = n, height = n;
        for (int i = 0; i < n * n; i++) {
            switch (direct) {
                case RIGHT:
                    matrix[x][y] = count++;
                    y++;
                    if (y == width) {
                        x++;
                        y--;
                        direct = DOWN;
                    }
                    break;
                case DOWN:
                    matrix[x][y] = count++;
                    x++;
                    if (x == height) {
                        y--;
                        x--;
                        height--;
                        direct = LEFT;
                    }
                    break;
                case LEFT:
                    matrix[x][y] = count++;
                    y--;
                    if (y < n - width) {
                        y++;
                        x--;
                        width--;
                        direct = UP;
                    }
                    break;
                case UP:
                    matrix[x][y] = count++;
                    x--;
                    if (x < n - height) {
                        x++;
                        y++;
                        direct = RIGHT;
                    }
                    break;
            }
        }
        return matrix;
    }

    /**
     * LeetCode 885 螺旋矩阵 III
     * <p>
     * 在 R 行 C 列的矩阵上，我们从 (r0, c0) 面朝东面开始。
     * 这里，网格的西北角位于第一行第一列，网格的东南角位于最后一行最后一列。
     * 现在，我们以顺时针按螺旋状行走，访问此网格中的每个位置。
     * 每当我们移动到网格的边界之外时，我们会继续在网格之外行走（但稍后可能会返回到网格边界）。
     * 最终，我们到过网格的所有 R * C 个空间。
     * 按照访问顺序返回表示网格位置的坐标列表。
     * <p>
     * 示例 1：
     * <p>
     * 输入：R = 1, C = 4, r0 = 0, c0 = 0
     * 输出：[[0,0],[0,1],[0,2],[0,3]]
     * <p>
     * 示例 2：
     * <p>
     * 输入：R = 5, C = 6, r0 = 1, c0 = 4
     * 输出：[[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],
     *       [0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
     * <p>
     * 提示：
     * <p>
     * 1 <= R <= 100
     * 1 <= C <= 100
     * 0 <= r0 < R
     * 0 <= c0 < C
     *
     * @param R  矩阵行数
     * @param C  矩阵列数
     * @param r0 起始行坐标
     * @param c0 起始纵坐标
     * @return 坐标列表
     */
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        if (R < 0 || C < 0 || r0 < 0 || c0 < 0) {
            return null;
        }

        int size = R * C;
        int[][] location = new int[size][2];
        int direct = RIGHT;
        int x = r0, y = c0;
        int rightBound = c0 + 1, downBound = r0 + 1, leftBound = c0 - 1, upBound = r0 - 1;
        int count = 0;
        while (count < size) {
            switch (direct) {
                case RIGHT:
                    if (check(R, C, x, y)) {
                        location[count][0] = x;
                        location[count][1] = y;
                        count++;
                    }
                    y++;
                    if (y == rightBound) {
                        rightBound = y + 1;
                        direct = DOWN;
                    }
                    break;
                case DOWN:
                    if (check(R, C, x, y)) {
                        location[count][0] = x;
                        location[count][1] = y;
                        count++;
                    }
                    x++;
                    if (x == downBound) {
                        downBound = x + 1;
                        direct = LEFT;
                    }
                    break;
                case LEFT:
                    if (check(R, C, x, y)) {
                        location[count][0] = x;
                        location[count][1] = y;
                        count++;
                    }
                    y--;
                    if (y == leftBound) {
                        leftBound = y - 1;
                        direct = UP;
                    }
                    break;
                case UP:
                    if (check(R, C, x, y)) {
                        location[count][0] = x;
                        location[count][1] = y;
                        count++;
                    }
                    x--;
                    if (x == upBound) {
                        upBound = x - 1;
                        direct = RIGHT;
                    }
                    break;
            }
            if (rightBound > C) {
                rightBound = C;
            }
            if (downBound > R) {
                downBound = R;
            }
            if (leftBound < 0) {
                leftBound = -1;
            }
            if (upBound < 0) {
                upBound = -1;
            }
        }
        return location;
    }

    private boolean check(int R, int C, int x, int y) {
        return x >= 0 && y >= 0 && x < R && y < C;
    }
}
