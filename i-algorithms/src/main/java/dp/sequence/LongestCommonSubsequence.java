package dp.sequence;

/**
 * LeetCode 1143
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 * <p>
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 * <p>
 * 若这两个字符串没有公共子序列，则返回 0。
 * <p>
 * 示例 1:
 * <p>
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 * 示例 2:
 * <p>
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc"，它的长度为 3。
 * 示例 3:
 * <p>
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0。
 *
 * @Author: Jeremy
 * @Date: 2020/9/3 15:03
 */
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
            return 0;
        }
        int M = text1.length();
        int N = text2.length();
        int[][] dp = new int[M][N];
        for (int i = 0; i < N; i++) {
            if (text2.charAt(i) == text1.charAt(0)) {
                for (int j = i; j < N; j++) {
                    dp[0][j] = 1;
                }
                break;
            }
        }

        for (int i = 0; i < M; i++) {
            if (text1.charAt(i) == text2.charAt(0)) {
                for (int j = i; j < M; j++) {
                    dp[j][0] = 1;
                }
                break;
            }
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[M - 1][N - 1];
    }

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();
        int res = longestCommonSubsequence.longestCommonSubsequence(text1, text2);
        System.out.println(res);
    }
}
