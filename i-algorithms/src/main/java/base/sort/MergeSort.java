package base.sort;

import util.PrintUtil;

/**
 * 归并排序，计算逆序对数
 *
 * @Author: Jeremy
 * @Date: 2020/9/6 16:41
 */
public class MergeSort implements Sort {
    private Integer reverseCount = 0;
    private int[] order; // 逆序对数量
    private int[] rOrder; // 顺序对数量

    public int reversePairs(int[] nums) {
        this.reverseCount = 0;
        sort(nums);
        return reverseCount;
    }

    @Override
    public int[] sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        return mergeSort(nums);
    }

    private int[] mergeSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        if (nums.length == 1) {
            return nums;
        }

        int len = nums.length;
        int mid = len / 2;
        int[] left = new int[mid];
        int[] right = new int[len - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = nums[i];
        }
        for (int j = mid; j < len; j++) {
            right[j - mid] = nums[j];
        }
        return merge(mergeSort(left), mergeSort(right));
    }

    private int[] merge(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return nums2;
        }
        if (nums2 == null || nums2.length == 0) {
            return nums1;
        }
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] res = new int[len1 + len2];
        int i = 0, j = 0;
        int count = 0;
        while (i < len1 && j < len2) {
            if (nums1[i] <= nums2[j]) {
                res[count++] = nums1[i++];
                reverseCount += j;
            } else {
                res[count++] = nums2[j++];
            }
        }
        while (i < len1) {
            res[count++] = nums1[i++];
            reverseCount += j;
        }

        while (j < len2) {
            res[count++] = nums2[j++];
        }
        return res;
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] nums1 = new int[]{1, 2, 4, 5};
        int[] nums2 = new int[]{0, 3, 5, 8};
        int[] mergeRes = mergeSort.merge(nums1, nums2);
        PrintUtil.print(mergeRes);

        int[] nums = new int[]{7, 5, 6, 4};
        int[] res = mergeSort.sort(nums);
        PrintUtil.print(res);

        nums = new int[]{1, 3, 2, 3, 1};
        int count = mergeSort.reversePairs(nums);
        System.out.println(count);
    }
}
