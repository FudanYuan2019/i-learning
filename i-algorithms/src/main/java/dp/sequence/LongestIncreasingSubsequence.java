package dp.sequence;

import java.util.*;

/**
 * LeetCode 300
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * <p>
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(nlogn) 吗?
 * <p>
 * LeetCode 673
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 * <p>
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 * 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
 *
 * @Author: Jeremy
 * @Date: 2020/9/3 15:07
 */
public class LongestIncreasingSubsequence {
    private int count = 0;
    private Set<String> set = new HashSet<>();

    /**
     * 动态规划，时间复杂度O(n2)，空间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int res = Integer.MIN_VALUE;
        for (int num : dp) {
            res = Math.max(res, num);
        }
        return res;
    }

    /**
     * 扑克牌分堆，时间复杂度O(nlogn)，空间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int piles = 1;
        int[] top = new int[len];
        top[0] = nums[0];

        for (int i = 1; i < len; i++) {
            int num = nums[i];
            int left = 0;
            int right = piles - 1;
            int mid = 0;
            boolean found = false;
            while (left <= right) {
                mid = left + ((right - left) >> 1);
                if (top[mid] < num) {
                    left = mid + 1;
                } else if (top[mid] >= num) {
                    if (mid == 0 || top[mid - 1] < num) {
                        found = true;
                        break;
                    }
                    right = mid - 1;
                }
            }
            if (!found) {
                top[piles] = num;
                piles++;
            } else {
                top[mid] = num;
            }
        }

        return piles;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 2, 2, 2};
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
        int res = longestIncreasingSubsequence.findLengthOfLCIS(nums);
        System.out.println(res);

        res = longestIncreasingSubsequence.findLengthOfLCIS2(nums);
        System.out.println(res);
    }
}
