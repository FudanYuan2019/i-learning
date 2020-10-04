package base.stack;

import util.PrintUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 下一更大元素，解决思路——单调栈
 * LeetCode 496, 503, 556, 739
 *
 * @Author: Jeremy
 * @Date: 2020/9/6 00:44
 */
public class MonotonicStack {
    public static void main(String[] args) {
        MonotonicStack monotonicStack = new MonotonicStack();
        int[] nums1 = new int[]{4, 1, 2};
        int[] nums2 = new int[]{1, 3, 4, 2};
        int[] res = monotonicStack.nextGreaterElement(nums1, nums2);
        PrintUtil.print(res);

        int[] nums3 = new int[]{1, 2, 1};
        res = monotonicStack.nextGreaterElement(nums3);
        PrintUtil.print(res);

        int[] T = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        res = monotonicStack.dailyTemperatures(T);
        PrintUtil.print(res);

        int n = 1999999999;
        int next = monotonicStack.nextGreaterElement(n);
        System.out.println(next);
    }

    /**
     * LeetCode 496 下一个更大元素 I
     * 给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
     * 找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
     * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
     * 输出: [-1,3,-1]
     * 解释:
     * 对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
     * 对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
     * 对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
     * 示例 2:
     * <p>
     * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
     * 输出: [3,-1]
     * 解释:
     * 对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
     * 对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
     *  
     * 提示：
     * <p>
     * nums1和nums2中所有元素是唯一的。
     * nums1和nums2 的数组大小都不超过1000。
     *
     * @param nums1 子元素
     * @param nums2 元素集合
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return new int[0];
        }

        int len1 = nums1.length;
        int len2 = nums2.length;

        Map<Integer, Integer> map = new HashMap<>();

        Stack<Integer> stack = new Stack<>();
        for (int i = len2 - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums2[i] >= stack.peek()) {
                stack.pop();
            }
            int nextGreaterNum = stack.isEmpty() ? -1 : stack.peek();
            map.put(nums2[i], nextGreaterNum);
            stack.push(nums2[i]);
        }

        int[] res = new int[len1];
        for (int i = 0; i < len1; i++) {
            res[i] = map.get(nums1[i]);
        }

        return res;
    }


    /**
     * LeetCode 503  下一个更大元素 II
     * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
     * 数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。
     * 如果不存在，则输出 -1。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,1]
     * 输出: [2,-1,2]
     * 解释: 第一个 1 的下一个更大的数是 2；
     * 数字 2 找不到下一个更大的数；
     * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
     * 注意: 输入数组的长度不会超过 10000。
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int len = nums.length;
        int[] res = new int[len];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i % len] >= nums[stack.peek()]) {
                stack.pop();
            }
            res[i % len] = stack.isEmpty() ? -1 : nums[stack.peek()];
            stack.push(i % len);
        }
        return res;
    }

    /**
     * LeetCode 739 每日温度
     * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。
     * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
     * <p>
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     * <p>
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     *
     * @param T 温度列表
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0) {
            return new int[0];
        }

        int len = T.length;
        int[] res = new int[len];
        Stack<Integer> stack = new Stack<>();
        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return res;
    }

    /**
     * LeetCode 556 下一更大元素 III
     * 给定一个32位正整数 n，你需要找到最小的32位整数，其与 n 中存在的位数完全相同，并且其值大于n。如果不存在这样的32位整数，则返回-1。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 12
     * 输出: 21
     * 示例 2:
     * <p>
     * 输入: 21
     * 输出: -1
     * <p>
     * 相似题目：LeetCode 31 <code>Permutation.nextPermutation</code>
     *
     * @param n 32为正整数
     * @return 下一更大元素
     */
    public int nextGreaterElement(int n) {
        if (n < 10) {
            return -1;
        }
        String str = String.valueOf(n);
        char[] chars = str.toCharArray();
        int len = chars.length;
        // 超过了32位整数了
        if (len >= 10) {
            return -1;
        }
        int j = 0;
        int i;
        for (i = len - 1; i > 0; i--) {
            if (chars[i] > chars[i - 1]) {
                j = i - 1;
                break;
            }
        }
        if (i == 0) {
            return -1;
        }
        for (i = len - 1; i >= 0; i--) {
            if (chars[i] > chars[j]) {
                swap(chars, i, j);
                break;
            }
        }
        traverse(chars, j + 1, len - 1);

        int res = 0;
        for (char ch : chars) {
            res *= 10;
            res += ch - '0';
        }
        return res;
    }

    private void traverse(char[] chars, int start, int end) {
        while (start < end) {
            swap(chars, start, end);
            start++;
            end--;
        }
    }

    private void swap(char[] chars, int start, int end) {
        char tmp = chars[start];
        chars[start] = chars[end];
        chars[end] = tmp;
    }
}
