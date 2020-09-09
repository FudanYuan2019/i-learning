package dp.backpack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 背包问题
 * <p>
 * 0-1背包问题：LeetCode 416, LeetCode 494
 * 完全背包问题，考虑顺序：LeetCode 377, LeetCode 139
 * 完全背包问题，不考虑顺序：LeetCode 518, LeetCode 322
 *
 * @Author: Jeremy
 * @Date: 2020/9/8 15:24
 */
public class Backpack {
    public static void main(String[] args) {
        Backpack backpack = new Backpack();

        int[] candidates = new int[]{1, 2, 3};
        int target = 4;
        int res = backpack.combinationSum4(candidates, target);
        System.out.println(res);

        int[] coins = new int[]{1, 2, 5};
        int amount = 5;
        res = backpack.change(amount, coins);
        System.out.println(res);

        res = backpack.coinChange(coins, amount);
        System.out.println(res);

        int[] nums = new int[]{1, 1, 1, 1, 1};
        int S = 3;
        res = backpack.findTargetSumWays(nums, S);
        System.out.println(res);

        nums = new int[]{1, 5, 11, 5};
        boolean can = backpack.canPartition(nums);
        System.out.println(can);

        String string = "catsandog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cats");
        wordDict.add("dog");
        wordDict.add("sand");
        wordDict.add("and");
        wordDict.add("cat");
        can = backpack.wordBreak(string, wordDict);
        System.out.println(can);
    }

    /**
     * LeetCode 377 组合总和 Ⅳ——完全背包，考虑顺序
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
        if (candidates == null || candidates.length == 0 || target <= 0) {
            return 0;
        }
        Arrays.sort(candidates);
        int[] dp = new int[target + 1];
        dp[0] = 1;
        // target 在外循环
        for (int i = 1; i <= target; i++) {
            // num 在内循环
            for (int num : candidates) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    /**
     * LeetCode 518 零钱兑换II——完全背包，不考虑顺序
     * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 
     * <p>
     * 示例 1:
     * <p>
     * 输入: amount = 5, coins = [1, 2, 5]
     * 输出: 4
     * 解释: 有四种方式可以凑成总金额:
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     * 示例 2:
     * <p>
     * 输入: amount = 3, coins = [2]
     * 输出: 0
     * 解释: 只用面额2的硬币不能凑成总金额3。
     * 示例 3:
     * <p>
     * 输入: amount = 10, coins = [10]
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        if (coins == null || coins.length == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        // nums 在外循环
        for (int coin : coins) {
            // target 在内循环
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    /**
     * LeetCode 494 目标和 -> 0-1背包问题
     * <p>
     * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。
     * 现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
     * <p>
     * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
     * <p>
     * 示例：
     * <p>
     * 输入：nums: [1, 1, 1, 1, 1], S: 3
     * 输出：5
     * 解释：
     * <p>
     * -1+1+1+1+1 = 3
     * +1-1+1+1+1 = 3
     * +1+1-1+1+1 = 3
     * +1+1+1-1+1 = 3
     * +1+1+1+1-1 = 3
     * <p>
     * 一共有5种方法让最终目标和为3。
     * <p>
     * 提示：
     * <p>
     * 数组非空，且长度不会超过 20 。
     * 初始的数组的和不会超过 1000 。
     * 保证返回的最终结果能被 32 位整数存下。
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = calSum(nums);
        if (sum < S || (sum + S) % 2 != 0) {
            return 0;
        }

        int capacity = (sum + S) / 2;
        int[] dp = new int[capacity + 1];
        dp[0] = 1;
        // nums 在外循环
        for (int num : nums) {
            // target 在内循环，逆序循环
            for (int i = capacity; i >= num; i--) {
                dp[i] += dp[i - num];
            }
        }
        return dp[capacity];
    }

    private int calSum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    /**
     * LeetCode 416 分割等和子集——0-1背包
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * <p>
     * 注意:
     * <p>
     * 每个数组中的元素不会超过 100
     * 数组的大小不会超过 200
     * 示例 1:
     * <p>
     * 输入: [1, 5, 11, 5]
     * <p>
     * 输出: true
     * <p>
     * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
     *  
     * <p>
     * 示例 2:
     * <p>
     * 输入: [1, 2, 3, 5]
     * <p>
     * 输出: false
     * <p>
     * 解释: 数组不能分割成两个元素和相等的子集.
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = calSum(nums);
        if (sum % 2 == 1) {
            return false;
        }

        int capacity = sum / 2;
        boolean[] dp = new boolean[capacity + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = capacity; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[capacity];
    }

    /**
     * LeetCode 139 单词拆分——完全背包，考虑顺序
     * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     * <p>
     * 说明：
     * <p>
     * 拆分时可以重复使用字典中的单词。
     * 你可以假设字典中没有重复的单词。
     * 示例 1：
     * <p>
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
     * 示例 2：
     * <p>
     * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
     * 输出: true
     * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     *      注意你可以重复使用字典中的单词。
     * 示例 3：
     * <p>
     * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * 输出: false
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }
        if (wordDict == null || wordDict.size() == 0) {
            return false;
        }

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        // target 外循环，正序遍历
        for (int i = 1; i <= n; i++) {
            // wordDict，内循环
            for (String word : wordDict) {
                int len = word.length();
                if (i >= len && word.equals(s.substring(i - len, i))) {
                    dp[i] = dp[i] || dp[i - len];
                }
            }
        }
        return dp[n];
    }

    /**
     * LeetCode 322 零钱兑换——完全背包，不考虑顺序
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     *
     * 示例 1:
     * <p>
     * 输入: coins = [1, 2, 5], amount = 11
     * 输出: 3
     * 解释: 11 = 5 + 5 + 1
     * 示例 2:
     * <p>
     * 输入: coins = [2], amount = 3
     * 输出: -1
     * <p>
     * <p>
     * 说明:
     * 你可以认为每种硬币的数量是无限的。
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount == 0) {
            return 0;
        }

        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;
        }
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
