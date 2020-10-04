package backtrack;

import util.PrintUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 排列问题：LeetCode 31, 46, 47, 60
 *
 * @Author: Jeremy
 * @Date: 2020/9/5 12:37
 */
public class Permutation {
    public static void main(String[] args) {
        Permutation permutation = new Permutation();

        // 下一排列
        int[] nums = new int[]{1, 2, 3};
        permutation.nextPermutation(nums);
        nums = new int[]{1, 3, 2};
        permutation.nextPermutation(nums);
        PrintUtil.newLine();

        // 全排列
        nums = new int[]{1, 2, 3};
        List<List<Integer>> res = permutation.permute(nums);
        for (List<Integer> subList : res) {
            PrintUtil.print(subList);
        }
        PrintUtil.newLine();

        // 全排列II
        nums = new int[]{1, 1, 2};
        res = permutation.permuteUnique(nums);
        for (List<Integer> subList : res) {
            PrintUtil.print(subList);
        }
        PrintUtil.newLine();

        // 求子集
        nums = new int[]{1, 2, 3};
        res = permutation.subsets(nums);
        for (List<Integer> subList : res) {
            PrintUtil.print(subList);
        }
        PrintUtil.newLine();

        nums = new int[]{1, 2, 3};
        res = permutation.subsetsI(nums);
        for (List<Integer> subList : res) {
            PrintUtil.print(subList);
        }
        PrintUtil.newLine();

        // 第k个排列
        int n = 3;
        int k = 3;
        String str = permutation.getPermutation(n, k);
        System.out.println(str);
        PrintUtil.newLine();

        // 组合问题
        int[] array = new int[]{1, 2, 3, 4, 5};
        n = array.length;
        k = 3;
        res = permutation.combination(array, n, k);
        for (List<Integer> list : res) {
            PrintUtil.print(list);
        }
    }

    /**
     * LeetCode 31 下一个排列
     * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * 必须原地修改，只允许使用额外常数空间。
     * <p>
     * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     * <p>
     * <p>
     * 如何得到这样的排列顺序？我们可以这样来分析：
     * <p>
     * 1. 我们希望下一个数比当前数大，这样才满足“下一个排列”的定义。
     * 因此只需要将后面的「大数」与前面的「小数」交换，就能得到一个更大的数。比如 123456，将 5 和 6 交换就能得到一个更大的数 123465。
     * 2. 我们还希望下一个数增加的幅度尽可能的小，这样才满足“下一个排列与当前排列紧邻“的要求。
     * 为了满足这个要求，我们需要：
     * 2.1 在尽可能靠右的低位进行交换，需要从后向前查找
     * 2.2 将一个 尽可能小的「大数」 与前面的「小数」交换。比如 123465，下一个排列应该把 5 和 4 交换而不是把 6 和 4 交换
     * 2.3 将「大数」换到前面后，需要将「大数」后面的所有数重置为升序，升序排列就是最小的排列。
     * 以 123465 为例：首先按照上一步，交换 5 和 4，得到 123564；然后需要将 5 之后的数重置为升序，得到 123546。
     * 显然 123546 比 123564 更小，123546 就是 123465 的下一个排列
     *
     * @param nums 输入数组
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int n = nums.length;
        int i, j = 0;
        for (i = n - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                j = i - 1;
                break;
            }
        }

        if (i == 0) {
            traverse(nums, 0, n - 1);
            PrintUtil.print(nums);
            return;
        }

        for (i = n - 1; i >= 0; i--) {
            if (nums[i] > nums[j]) {
                swap(nums, i, j);
                break;
            }
        }

        traverse(nums, j + 1, n - 1);
        PrintUtil.print(nums);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void traverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    /**
     * LeetCode 46 全排列
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,2,3]
     * 输出:
     * [
     * [1,2,3],
     * [1,3,2],
     * [2,1,3],
     * [2,3,1],
     * [3,1,2],
     * [3,2,1]
     * ]
     * <p>
     *
     * @param nums 集合数组
     * @return 全排列
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrack(nums, path, res);
        return res;
    }

    private void backtrack(int[] nums, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            if (!res.contains(path)) {
                res.add(new ArrayList<>(path));
            }
            return;
        }

        for (int num : nums) {
            if (path.contains(num)) {
                continue;
            }
            path.add(num);
            backtrack(nums, path, res);
            path.remove(path.size() - 1);
        }
    }

    /**
     * LeetCode 47 全排列II
     * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,1,2]
     * 输出:
     * [
     * [1,1,2],
     * [1,2,1],
     * [2,1,1]
     * ]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        int n = nums.length;
        List<Integer> candidates = new ArrayList<>(n);
        for (int num : nums) {
            candidates.add(num);
        }
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        backtrack(n, candidates, path, res);
        return res;
    }

    private void backtrack(int n, List<Integer> candidates, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == n) {
            if (!res.contains(path)) {
                res.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = 0; i < candidates.size(); i++) {
            List<Integer> candidatesCopy = new ArrayList<>(candidates);
            path.add(candidates.get(i));
            candidatesCopy.remove(i);
            backtrack(n, candidatesCopy, path, res);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 全排列II 解法二
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUniqueI(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        int n = nums.length;
        permutations(nums, n, n, res);
        return res;
    }

    public void permutations(int[] nums, int n, int k, List<List<Integer>> res) {
        if (k == 1) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            if (!res.contains(list)) {
                res.add(list);
            }
        }
        for (int i = 0; i < k; i++) {
            swap(nums, i, k - 1);
            permutations(nums, n, k - 1, res);
            swap(nums, i, k - 1);
        }
    }

    /**
     * LeetCode 78. 子集
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * <p>
     * 说明：解集不能包含重复的子集。
     * <p>
     * 示例:
     * <p>
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     * [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     * <p>
     * 方法1：位图
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return res;
        }

        // 位图
        int count = (int) Math.pow(2, nums.length);
        for (int i = 0; i < count; i++) {
            int[] list = transform(i, nums.length);
            List<Integer> tmp = new ArrayList<>();
            for (int j = 0; j < list.length; j++) {
                if (list[j] != 0) {
                    tmp.add(nums[j]);
                }
            }
            res.add(tmp);
        }
        return res;
    }

    private int[] transform(int num, int size) {
        int[] res = new int[size];
        int count = 0;
        while (num > 0) {
            res[size - 1 - count++] = num % 2;
            num /= 2;
        }
        return res;
    }

    /**
     * 方法2：递归
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsI(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return res;
        }

        // 递归
        int n = nums.length;
        List<Integer> path = new ArrayList<>();
        backtrack(nums, n, 0, path, res);
        return res;
    }

    private void backtrack(int[] nums, int n, int k, List<Integer> path, List<List<Integer>> res) {
        if (k == n) {
            res.add(new ArrayList<>(path));
            return;
        }
        path.add(nums[k]);
        backtrack(nums, n, k + 1, path, res);

        path.remove(path.size() - 1);
        backtrack(nums, n, k + 1, path, res);
    }

    /**
     * LeetCode 60 第k个排列
     * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
     * <p>
     * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
     * <p>
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * 给定 n 和 k，返回第 k 个排列。
     * <p>
     * 说明：
     * <p>
     * 给定 n 的范围是 [1, 9]。
     * 给定 k 的范围是[1, n!]。
     * 示例 1:
     * <p>
     * 输入: n = 3, k = 3
     * 输出: "213"
     * 示例 2:
     * <p>
     * 输入: n = 4, k = 9
     * 输出: "2314"
     * <p>
     *
     * @param n 集合最大值，即[1, 2, 3, ..., n]
     * @param k 第k个
     * @return 第k个排列组成的字符串
     */
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>(n);
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            nums.add(i);
            factorial *= i;
        }

        k -= 1;
        StringBuilder res = new StringBuilder();
        while (nums.size() > 0) {
            factorial = factorial / nums.size();
            int index = k / factorial;
            res.append(nums.get(index));
            nums.remove(index);
            k %= factorial;
        }
        return res.toString();
    }

    public List<List<Integer>> combination(int[] array, int m, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (array == null || array.length == 0) {
            return res;
        }
        if (n > m) {
            throw new IllegalArgumentException("n should be less than m");
        }

        List<Integer> path = new ArrayList<>();
        dfs(array, m, n, 0, path, res);
        return res;
    }

    private void dfs(int[] array, int m, int n, int start, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == n) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < m; i++) {
            if (path.contains(array[i])) {
                continue;
            }
            path.add(array[i]);
            dfs(array, m, n, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
}
