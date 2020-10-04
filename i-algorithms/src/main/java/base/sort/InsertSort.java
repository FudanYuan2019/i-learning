package base.sort;

import util.PrintUtil;

/**
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 * 稳定的排序算法
 *
 * @Author: Jeremy
 * @Date: 2020/10/2 23:08
 */
public class InsertSort implements Sort {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 5, 2, 0, 9};
        InsertSort insertSort = new InsertSort();
        insertSort.sort(nums);
        PrintUtil.print(nums);
    }

    @Override
    public int[] sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int val = nums[i];
            int j = i;
            for (; j > 0; j--) {
                if (nums[j - 1] > val) {
                    nums[j] = nums[j - 1];
                } else {
                    break;
                }
            }
            nums[j] = val;
        }
        return nums;
    }
}