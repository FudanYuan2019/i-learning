package base.array;

import util.PrintUtil;

/**
 * @Author: Jeremy
 * @Date: 2020/10/25 12:21
 */
public class LongestMountain {
    public static void main(String[] args) {
        LongestMountain mountain = new LongestMountain();
        int[] A = new int[]{2, 1, 4, 7, 3, 2, 5};
        int res;
        res = mountain.longestMountainV1(A);
        PrintUtil.print(res);

        res = mountain.longestMountainV2(A);
        PrintUtil.print(res);
    }

    /**
     * LeetCode 845. 数组中的最长山脉
     * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
     * <p>
     * B.length >= 3
     * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
     * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
     * <p>
     * 给出一个整数数组 A，返回最长 “山脉” 的长度。
     * <p>
     * 如果不含有 “山脉” 则返回 0。
     * <p>
     * 示例 1：
     * <p>
     * 输入：[2,1,4,7,3,2,5]
     * 输出：5
     * 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
     * 示例 2：
     * <p>
     * 输入：[2,2,2]
     * 输出：0
     * 解释：不含 “山脉”。
     *
     * @param A
     * @return
     */
    public int longestMountainV1(int[] A) {
        int n = A.length;
        if (n == 0) {
            return 0;
        }

        int[] left = new int[n];
        for (int i = 1; i < n; ++i) {
            left[i] = A[i - 1] < A[i] ? left[i - 1] + 1 : 0;
        }
        int[] right = new int[n];
        for (int i = n - 2; i >= 0; --i) {
            right[i] = A[i + 1] < A[i] ? right[i + 1] + 1 : 0;
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (left[i] > 0 && right[i] > 0) {
                ans = Math.max(ans, left[i] + right[i] + 1);
            }
        }
        return ans;
    }

    /**
     * 枚举山脚
     *
     * @param A
     * @return
     */
    public int longestMountainV2(int[] A) {
        int n = A.length;
        int ans = 0;
        int left = 0;
        while (left + 2 < n) {
            int right = left + 1;
            if (A[left] < A[left + 1]) {
                // 寻找山顶
                while (right + 1 < n && A[right] < A[right + 1]) {
                    ++right;
                }
                // 寻找右侧山脚
                if (right < n - 1 && A[right] > A[right + 1]) {
                    while (right + 1 < n && A[right] > A[right + 1]) {
                        ++right;
                    }
                    ans = Math.max(ans, right - left + 1);
                } else {
                    ++right;
                }
            }
            // 更新下一山脉的左侧山脚=上一山脉的右侧山脚
            left = right;
        }
        return ans;
    }
}
