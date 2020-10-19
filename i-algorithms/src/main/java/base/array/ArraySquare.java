package base.array;

import util.PrintUtil;

/**
 * @Author: Jeremy
 * @Date: 2020/10/16 10:25
 */
public class ArraySquare {
    public static void main(String[] args) {
        int[] array = new int[]{-4, -1, 0, 3, 10};
        ArraySquare arraySquare = new ArraySquare();
        int[] res = arraySquare.sortedSquares(array);
        PrintUtil.print(res);
    }

    /**
     * LeetCode 977. 有序数组的平方
     * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
     * <p>
     * 示例 1：
     * <p>
     * 输入：[-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     * 示例 2：
     * <p>
     * 输入：[-7,-3,2,3,11]
     * 输出：[4,9,9,49,121]
     *
     * @param A
     * @return
     */
    public int[] sortedSquares(int[] A) {
        int len = A.length;
        int[] res = new int[len];

        int pos = findFirstPositivePos(A);
        // 全是非负数
        if (pos == 0) {
            for (int i = 0; i < len; i++) {
                res[i] = A[i] * A[i];
            }
            return res;
        }

        // 全是非正数
        if (pos == -1) {
            for (int i = 0; i < len; i++) {
                res[i] = A[len - i - 1] * A[len - i - 1];
            }
            return res;
        }

        // 既有正数也有负数
        int i = pos - 1;
        int j = pos;
        int index = 0;
        while (i >= 0 && j < len) {
            if (-A[i] < A[j]) {
                res[index++] = A[i] * A[i];
                i--;
            } else if (-A[i] > A[j]) {
                res[index++] = A[j] * A[j];
                j++;
            } else if (-A[i] == A[j]) {
                res[index++] = A[i] * A[i];
                res[index++] = A[j] * A[j];
                i--;
                j++;
            }
        }
        while (i >= 0) {
            res[index++] = A[i] * A[i];
            i--;
        }
        while (j < len) {
            res[index++] = A[j] * A[j];
            j++;
        }
        return res;
    }

    private int findFirstPositivePos(int[] A) {
        int len = A.length;
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (A[mid] < 0) {
                left = mid + 1;
            } else {
                if (mid == 0 || A[mid - 1] < 0) return mid;
                right = mid - 1;
            }
        }
        return -1;
    }
}
