package base.sort;

import util.PrintUtil;

/**
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 * 稳定的排序算法
 *
 * @Author: Jeremy
 * @Date: 2020/10/2 22:11
 */
public class BubbleSort implements Sort {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 5, 2};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(nums);
        PrintUtil.print(nums);
    }

    @Override
    public int[] sort(int[] nums) {
        boolean swapped = false;
        for (int j = nums.length - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                if (nums[i] > nums[i + 1]) {
                    swapped = true;
                    Swapper.swap(nums, i, i + 1);
                }
            }
            if (!swapped) {
                break;
            }
        }
        return nums;
    }
}
