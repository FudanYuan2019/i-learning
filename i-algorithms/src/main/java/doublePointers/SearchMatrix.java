package doublePointers;

import util.PrintUtil;

/**
 * 搜索二维矩阵。 LeetCode 74, 240
 *
 * @Author: Jeremy
 * @Date: 2020/9/14 13:03
 */
public class SearchMatrix {
    public static void main(String[] args) {
        SearchMatrix searchMatrix = new SearchMatrix();
        int[][] matrix = new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50},
        };
        boolean res = searchMatrix.searchMatrixI(matrix, 3);
        PrintUtil.print(res);

        matrix = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        res = searchMatrix.searchMatrixII(matrix, 5);
        PrintUtil.print(res);
    }

    /**
     * LeetCode 74 搜索二维矩阵
     * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。
     * 该矩阵具有如下特性：
     * <p>
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     * 示例 1:
     * <p>
     * 输入:
     * matrix = [
     * [1,   3,  5,  7],
     * [10, 11, 16, 20],
     * [23, 30, 34, 50]
     * ]
     * target = 3
     * 输出: true
     * 示例 2:
     * <p>
     * 输入:
     * matrix = [
     * [1,   3,  5,  7],
     * [10, 11, 16, 20],
     * [23, 30, 34, 50]
     * ]
     * target = 13
     * 输出: false
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrixI(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        int M = matrix.length;
        int N = matrix[0].length;
        // 先确定在哪一行
        int row = -1;
        int up = 0;
        int down = M - 1;
        while (up <= down) {
            int mid = up + ((down - up) >> 1);
            if (matrix[mid][0] > target) {
                down = mid - 1;
            } else if (matrix[mid][0] <= target) {
                if (mid == M - 1 || matrix[mid + 1][0] > target) {
                    row = mid;
                    break;
                }
                up = mid + 1;
            }
        }

        if (row == -1) {
            return false;
        }

        // 在确定在哪一列
        int left = 0;
        int right = N - 1;
        int col = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (matrix[row][mid] > target) {
                right = mid - 1;
            } else if (matrix[row][mid] < target) {
                left = mid + 1;
            } else {
                col = mid;
                break;
            }
        }

        if (col == -1) {
            return false;
        }
        return true;
    }

    /**
     * LeetCode 240 搜索二维矩阵II
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。
     * 该矩阵具有以下特性：
     * <p>
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * 示例:
     * <p>
     * 现有矩阵 matrix 如下：
     * <p>
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     * <p>
     * 给定 target = 20，返回 false。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrixII(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int M = matrix.length;
        int N = matrix[0].length;
        int i = M - 1;
        int j = 0;
        while (i >= 0 && j < N) {
            if (matrix[i][j] > target) {
                i--;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }
}
