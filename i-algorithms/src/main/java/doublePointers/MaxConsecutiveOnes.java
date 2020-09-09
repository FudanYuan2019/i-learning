package doublePointers;

import java.util.Scanner;

/**
 * 题目描述
 * 给你一个01字符串，定义答案=该串中最长的连续1的长度，现在你有至多K次机会，每次机会可以将串中的某个0改成1，现在问最大的可能答案
 * <p>
 * 输入描述:
 * 输入第一行两个整数N,K，表示字符串长度和机会次数
 * 第二行输入N个整数，表示该字符串的元素
 * <p>
 * ( 1 <= N <= 300000, 0 <= K <= N )
 * 输出描述:
 * 输出一行表示答案
 * <p>
 * 示例1
 * 输入
 * 10 2
 * 1 0 0 1 0 1 0 1 0 1
 * <p>
 * 输出
 * 5
 *
 * @Author: Jeremy
 * @Date: 2020/9/4 23:44
 */
public class MaxConsecutiveOnes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int[] nums = new int[N];
        for (int l = 0; l < N; l++) {
            nums[l] = scanner.nextInt();
        }

        int res = new MaxConsecutiveOnes().getMaxConsecutiveOnes(nums, N, K);
        System.out.println(res);
    }

    /**
     * 用 l 代表左指针, r 代表右指针。
     * 当 K 非 0 时，我们就让右指针一直右移，遇到 1 就直接右移，遇到 0 时从 K 中选一个来填充该位，K 就变成了 K - 1。
     * 当 K 为 0 时，右指针遇到 1 依旧右移，遇到 0 时，需要停下来，因为此时的 K 不够用了，左指针要动了。
     * 左指针一定要遇到首个 0 时再停下来，此时左指针和右指针之间的数字长度就是指针 l 的最长全1子串。
     * 记录此时的结果后，我们接着右移右指针，重复上述过程即可。
     * 其中最后的代码 res=max(res,r−l) 包含了 K 用不完的情况以及数字的最后一位是1的情况。
     *
     * @param nums
     * @param N
     * @param K
     * @return
     */
    public int getMaxConsecutiveOnes(int[] nums, int N, int K) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = 0;
        int l = 0, r = 0;
        while (r < N) {
            if (nums[r] == 1) {
                r++;
            } else if (K > 0) {
                K--;
                r++;
            } else {
                res = Math.max(res, r - l);
                while (l < r && nums[l] == 1) {
                    l++;
                }
                l++;
                r++;
            }
        }

        return Math.max(res, r - l);
    }
}
