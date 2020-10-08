package base.sort;

import util.PrintUtil;

/**
 * @Author: Jeremy
 * @Date: 2020/10/7 10:58
 */
public class ColorSort {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 1, 0, 0, 1, 1, 0, 2, 2, 1, 2};
        ColorSort colorSort = new ColorSort();
        colorSort.sortColors(nums);

        PrintUtil.print(nums);
    }

    /**
     * 75. 颜色分类
     * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，
     * 使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     * <p>
     * 注意:
     * 不能使用代码库中的排序函数来解决这道题。
     * <p>
     * 示例:
     * <p>
     * 输入: [2,0,2,1,1,0]
     * 输出: [0,0,1,1,2,2]
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        // 记录0的位置
        int left = -1;
        // 记录2的位置
        int right = nums.length;
        int i = 0;
        while (i < right) {
            int cur = nums[i];
            if (cur == 0) {
                int tmp = nums[++left];
                nums[left] = cur;
                nums[i++] = tmp;
            } else if (cur == 2) {
                int tmp = nums[--right];
                nums[right] = cur;
                nums[i] = tmp;
            } else if (cur == 1) {
                i++;
            }
        }
    }
}
