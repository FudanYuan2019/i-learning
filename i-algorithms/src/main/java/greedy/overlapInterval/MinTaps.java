package greedy.overlapInterval;

/**
 * @Author: Jeremy
 * @Date: 2020/9/5 19:58
 */
public class MinTaps {
    public static void main(String[] args) {
        MinTaps minTaps = new MinTaps();
        int n = 5;
        int[] ranges = new int[]{3,4,1,1,0,0};
        int res = minTaps.minTaps(n, ranges);
        System.out.println(res);
    }

    public int minTaps(int n, int[] ranges) {
        int[][] intervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            intervals[i][0] = Math.max(i - ranges[i], 0);
            intervals[i][1] = Math.min(i + ranges[i], n);
        }

        return 0;
    }
}
