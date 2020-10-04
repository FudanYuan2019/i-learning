package base.sort;

import util.PrintUtil;

/**
 * 时间复杂度：最好情况下O(nlogn); 最坏情况下：O(n^2)，正好倒序的情况下
 * 空间复杂度：O(1)
 * 不稳定的排序算法
 *
 * @Author: Jeremy
 * @Date: 2020/10/2 21:27
 */
public class QuickSort implements Sort {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 8, 2, 2, 1, 6, 7, 7, 5};
        QuickSort quickSort = new QuickSort();
        quickSort.sort(nums);
        PrintUtil.print(nums);
    }

    @Override
    public int[] sort(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        partition(nums, i, j);
        return nums;
    }

    private void partition(int[] nums, int left, int right) {
        if (left > right) {
            return;
        }
        int i = left;
        int j = right;
        int pivot = nums[i];
        while (i < j) {
            while (i < j && nums[j] >= pivot) {
                j--;
            }
            if (i < j) {
                nums[i] = nums[j];
            }
            while (i < j && nums[i] <= pivot) {
                i++;
            }
            if (i < j) {
                nums[j] = nums[i];
            }
        }
        nums[i] = pivot;
        partition(nums, left, i - 1);
        partition(nums, i + 1, right);
    }
}
