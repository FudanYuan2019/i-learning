package backtrack;

import util.PrintUtil;

import java.util.*;

/**
 * 组合问题，LeetCode
 *
 * @Author: Jeremy
 * @Date: 2020/9/8 11:12
 */
public class Combination {
    private Map<Integer, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
        Combination combination = new Combination();
        List<List<Integer>> res = combination.combine(4, 2);
        for (List<Integer> subList : res) {
            PrintUtil.print(subList);
        }
        PrintUtil.nextLine();

        int[] candidates = new int[]{2, 3, 5};
        int target = 8;
        res = combination.combinationSum(candidates, target);
        for (List<Integer> subList : res) {
            PrintUtil.print(subList);
        }
        PrintUtil.nextLine();

        candidates = new int[]{2, 5, 2, 1, 2};
        target = 5;
        res = combination.combinationSum2(candidates, target);
        for (List<Integer> subList : res) {
            PrintUtil.print(subList);
        }
        PrintUtil.nextLine();

        int k = 3, n = 7;
        res = combination.combinationSum3(k, n);
        for (List<Integer> subList : res) {
            PrintUtil.print(subList);
        }
        PrintUtil.nextLine();

        candidates = new int[]{1, 2, 3};
        target = 4;
        int count = combination.combinationSum4(candidates, target);
        PrintUtil.print(count);
    }

    /**
     * LeetCode 77
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     * <p>
     * 示例:
     * <p>
     * 输入: n = 4, k = 2
     * 输出:
     * [
     * [2,4],
     * [3,4],
     * [2,3],
     * [1,2],
     * [1,3],
     * [1,4],
     * ]
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        backtrack(n, k, 1, path, res);
        return res;
    }

    public void backtrack(int n, int k, int begin, List<Integer> path, List<List<Integer>> res) {
        // 递归终止条件是：path 的长度等于 k
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 遍历可能的搜索起点
        for (int i = begin; i <= n - (k - path.size()) + 1; i++) {
            // 向路径变量里添加一个数
            path.add(i);
            // 下一轮搜索，设置的搜索起点要加 1，因为组合数理不允许出现重复的元素
            backtrack(n, k, i + 1, path, res);
            // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            path.remove(path.size() - 1);
        }
    }

    /**
     * LeetCode 39 组合总和
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，
     * 找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的数字可以无限制重复被选取。
     * <p>
     * 说明：
     * <p>
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1：
     * <p>
     * 输入：candidates = [2,3,6,7], target = 7,
     * 所求解集为：
     * [
     * [7],
     * [2,2,3]
     * ]
     * 示例 2：
     * <p>
     * 输入：candidates = [2,3,5], target = 8,
     * 所求解集为：
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (target <= 0) {
            return new ArrayList<>();
        }
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        backtrack(candidates, target, path, 0, res);
        return res;
    }

    public void backtrack(int[] candidates, int target, List<Integer> path, int sum, List<List<Integer>> res) {
        if (sum == target) {
            List<Integer> copyPath = new ArrayList<>(path);
            Collections.sort(copyPath);
            if (!res.contains(copyPath)) {
                res.add(copyPath);
            }
            return;
        }

        for (int i = 0; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                break;
            }
            path.add(candidates[i]);
            backtrack(candidates, target, path, sum + candidates[i], res);
            path.remove(path.size() - 1);
        }
    }

    /**
     * LeetCode 40 组合总数II
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用一次。
     * <p>
     * 说明：
     * <p>
     * 所有数字（包括目标数）都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1:
     * <p>
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 所求解集为:
     * [
     * [1, 7],
     * [1, 2, 5],
     * [2, 6],
     * [1, 1, 6]
     * ]
     * 示例 2:
     * <p>
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 所求解集为:
     * [
     *   [1,2,2],
     *   [5]
     * ]
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (target <= 0) {
            return res;
        }

        Arrays.sort(candidates);

        List<Integer> path = new ArrayList<>();
        backtrack(candidates, target, 0, path, res);
        return res;
    }

    public void backtrack(int[] candidates, int target, int index, List<Integer> path, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            List<Integer> copyPath = new ArrayList<>(path);
            Collections.sort(copyPath);
            if (!res.contains(copyPath)) {
                res.add(copyPath);
            }
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                break;
            }
            path.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }

    /**
     * LeetCode 216 组合总和 III
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     * <p>
     * 说明：
     * 所有数字都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1:
     * <p>
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 示例 2:
     * <p>
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrack(k, n, 1, 9, path, res);
        return res;
    }

    public void backtrack(int k, int n, int begin, int maxLimit, List<Integer> path, List<List<Integer>> res) {
        if (n < 0) {
            return;
        }
        if (path.size() == k && n == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i <= maxLimit; i++) {
            path.add(i);
            backtrack(k, n - i, i + 1, maxLimit, path, res);
            path.remove(path.size() - 1);
        }
    }

    /**
     * LeetCode 377 组合总和 Ⅳ
     * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
     * <p>
     * 示例:
     * <p>
     * nums = [1, 2, 3]
     * target = 4
     * <p>
     * 所有可能的组合为：
     * (1, 1, 1, 1)
     * (1, 1, 2)
     * (1, 2, 1)
     * (1, 3)
     * (2, 1, 1)
     * (2, 2)
     * (3, 1)
     * <p>
     * 请注意，顺序不同的序列被视作不同的组合。
     * <p>
     * 因此输出为 7。
     * 进阶：
     * 如果给定的数组中含有负数会怎么样？
     * 问题会产生什么变化？
     * 我们需要在题目中添加什么限制来允许负数的出现？
     *
     * @param candidates
     * @param target
     * @return
     */
    public int combinationSum4(int[] candidates, int target) {
        if (target <= 0) {
            return 0;
        }
        memo = new HashMap<>();
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        return backtrack(candidates, target, path);
    }

    public int backtrack(int[] candidates, int target, List<Integer> path) {
        if (target < 0) {
            return 0;
        }
        if (memo.containsKey(target)) {
            return memo.get(target);
        }

        if (target == 0) {
            return 1;
        }

        int res = 0;
        for (int i = 0; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                break;
            }
            path.add(candidates[i]);
            res += backtrack(candidates, target - candidates[i], path);
            path.remove(path.size() - 1);
        }
        memo.put(target, res);
        return res;
    }

}
