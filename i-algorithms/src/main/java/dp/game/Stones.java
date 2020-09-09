package dp.game;

/**
 * @Author: Jeremy
 * @Date: 2020/9/3 21:55
 */
public class Stones {
    public int getMaxScore(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int[][][] dp = new int[N][N][2];
        for (int i = 0; i < N; i++) {
            dp[i][i][0] = nums[i];
        }

        for (int l = 1; l < N; l++) {
            for (int i = 0; i < N - l; i++) {
                int j = i + l;
                int left = nums[i] + dp[i + 1][j][1];
                int right = nums[j] + dp[i][j - 1][1];
                // 选最左边的
                if (left > right) {
                    dp[i][j][0] = left;
                    dp[i][j][1] = dp[i + 1][j][0];
                } else {
                    dp[i][j][0] = right;
                    dp[i][j][1] = dp[i][j - 1][0];
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print("(" + dp[i][j][0] + ", " + dp[i][j][1] + ")  ");
            }
            System.out.println();
        }

        return dp[0][N - 1][0] - dp[0][N - 1][1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 9, 1, 2, 4};
        int diff = new Stones().getMaxScore(nums);
        System.out.println(diff);
    }
}
