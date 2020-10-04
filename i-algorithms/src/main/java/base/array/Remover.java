package base.array;

import util.PrintUtil;

/**
 * @Author: Jeremy
 * @Date: 2020/10/2 21:18
 */
public class Remover {
    public static void main(String[] args) {
        int[] nums = new int[]{4, 5};
        int val = 5;

        Remover rm = new Remover();
        int count = rm.removeElement(nums, val);
        PrintUtil.print(count);
    }

    /**
     * LeetCode 27. 移除元素
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * <p>
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * <p>
     * 示例 1:
     * <p>
     * 给定 nums = [3,2,2,3], val = 3,
     * <p>
     * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
     * <p>
     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2:
     * <p>
     * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
     * <p>
     * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
     * <p>
     * 注意这五个元素可为任意顺序。
     * <p>
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int i = 0;
        int j = nums.length - 1;
        int count = 0;

        while (i <= j) {
            if (nums[i] == val) {
                count++;
                while (i < j && nums[j] == val) {
                    j--;
                    count++;
                }
                if (i < j) {
                    swap(nums, i, j);
                    j--;
                }
            }
            i++;
        }
        return nums.length - count;
    }

    public void swap(int[] nums, int i, int j) {
        int a = nums[i];
        nums[i] = nums[j];
        nums[j] = a;
    }
}
