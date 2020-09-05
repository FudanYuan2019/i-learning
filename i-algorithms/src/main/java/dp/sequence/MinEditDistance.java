package dp.sequence;

/**
 * LeetCode 72
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 * 示例 1：
 * <p>
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * <p>
 * 示例 2：
 * <p>
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * @Author: Jeremy
 * @Date: 2020/9/3 15:02
 */
public class MinEditDistance {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word1.length() == 0) {
            return word2.length();
        }

        word1 = " " + word1;
        word2 = " " + word2;
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1][len2];

        for (int i = 1; i < len1; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i < len2; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    int doNothing = dp[i - 1][j - 1];
                    int delete = dp[i - 1][j] + 1;
                    int insert = dp[i][j - 1] + 1;
                    dp[i][j] = min(doNothing, delete, insert);
                } else {
                    int edit = dp[i - 1][j - 1] + 1;
                    int delete = dp[i - 1][j] + 1;
                    int insert = dp[i][j - 1] + 1;
                    dp[i][j] = min(edit, delete, insert);
                }
            }
        }

        return dp[len1 - 1][len2 - 1];
    }

    private int min(int x, int y, int z) {
        return Math.min(Math.min(x, y), z);
    }

    public static void main(String[] args) {
        MinEditDistance minEditDistance = new MinEditDistance();
        int dis = minEditDistance.minDistance("horse", "ros");
        System.out.println(dis);
    }
}
