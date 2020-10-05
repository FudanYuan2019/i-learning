package base.array;

import util.PrintUtil;

import java.util.*;

/**
 * @Author: Jeremy
 * @Date: 2020/10/5 10:16
 */
public class NSums {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;

        NSums nSums = new NSums();
        int[] res = nSums.twoSum(nums, target);
        PrintUtil.print(res);
        PrintUtil.newLine();

        int[] nums1 = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res1 = nSums.threeSum(nums1);
        for (List<Integer> list : res1) {
            PrintUtil.print(list);
        }
        PrintUtil.newLine();

        int[] nums2 = new int[]{1, 0, -1, 0, -2, 2};
        List<List<Integer>> res2 = nSums.fourSum(nums2, 0);
        for (List<Integer> list : res2) {
            PrintUtil.print(list);
        }
        PrintUtil.newLine();
    }

    /**
     * LeetCode 1. 两数之和
     * 给定一个整数数组 nums 和一个目标值 target，
     * 请你在该数组中找出和为目标值的那 两个 整数，
     * 并返回他们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。
     * 但是，数组中同一个元素不能使用两遍。
     * <p>
     * 示例:
     * <p>
     * 给定 nums = [2, 7, 11, 15], target = 9
     * <p>
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null) {
            return res;
        }
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            if (!map.containsKey(num)) {
                map.put(target - num, i);
            } else {
                res[0] = map.get(num);
                res[1] = i;
                return res;
            }
        }
        return res;
    }

    /**
     * LeetCode 15. 三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
     * 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     * <p>
     * 示例：
     * <p>
     * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * <p>
     * 满足要求的三元组集合为：
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    /**
     * LeetCode 18. 四数之和
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
     * 判断 nums 中是否存在四个元素 a，b，c 和 d ，
     * 使得 a + b + c + d 的值与 target 相等？
     * 找出所有满足条件且不重复的四元组。
     * <p>
     * 注意：
     * <p>
     * 答案中不可以包含重复的四元组。
     * <p>
     * 示例：
     * <p>
     * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
     * <p>
     * 满足要求的四元组集合为：
     * [
     * [-1,  0, 0, 1],
     * [-2, -1, 1, 2],
     * [-2,  0, 0, 2]
     * ]
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        // 枚举 a
        for (int first = 0; first < n; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int threeSums = target - nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; second++) {
                int four = n - 1;
                int twoSums = threeSums - nums[second];
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }

                // 枚举c
                for (int third = second + 1; third < n; third++) {
                    if (third > second + 1 && nums[third] == nums[third - 1]) {
                        continue;
                    }

                    while (third < four && nums[third] + nums[four] > twoSums) {
                        four--;
                    }

                    if (third == four) {
                        break;
                    }

                    if (nums[third] + nums[four] == twoSums) {
                        List<Integer> index = new ArrayList<>();
                        index.add(nums[first]);
                        index.add(nums[second]);
                        index.add(nums[third]);
                        index.add(nums[four]);
                        ans.add(index);
                    }
                }

            }
        }
        return ans;
    }
}
