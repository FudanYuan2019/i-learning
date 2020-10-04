package base.array;

import util.PrintUtil;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 * <p>
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 说明:
 * <p>
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 *
 * @Author: Jeremy
 * @Date: 2020/9/16 10:50
 */
public class Rotate {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k = 8;

        Rotate rotate = new Rotate();
        rotate.rotateI(nums, k);
        PrintUtil.print(nums);

        nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        k = 8;
        rotate.rotateII(nums, k);
        PrintUtil.print(nums);
    }

    /**
     * 方法一：模拟每一步的操作
     * @param nums
     * @param k
     */
    public void rotateI(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }

        int n = nums.length;
        k %= n;
        for (int i = 0; i < k; i++) {
            int tmp = nums[n - 1];
            for (int j = n - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = tmp;
        }
    }

    /**
     * 方法二 最后k位数字逆转，前n-k位数字逆转，然后前后两位交换位置
     * @param nums
     * @param k
     */
    public void rotateII(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - k - 1);
        reverse(nums, n - k, n - 1);
        reverse(nums, 0, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
