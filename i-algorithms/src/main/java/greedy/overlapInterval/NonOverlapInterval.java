package greedy.overlapInterval;

import java.util.Arrays;
import java.util.Comparator;

/**
 * LeetCode 435
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * <p>
 * 注意:
 * <p>
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 示例 1:
 * <p>
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 * <p>
 * 输出: 1
 * <p>
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 * <p>
 * 输入: [ [1,2], [1,2], [1,2] ]
 * <p>
 * 输出: 2
 * <p>
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 * <p>
 * 输入: [ [1,2], [2,3] ]
 * <p>
 * 输出: 0
 * <p>
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 * @Author: Jeremy
 * @Date: 2020/9/3 14:52
 */
public class NonOverlapInterval {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 2}, {2, 3}};
        NonOverlapInterval nonOverlapInterval = new NonOverlapInterval();
        int max = nonOverlapInterval.overlapIntervals(intervals);
        int min = nonOverlapInterval.eraseOverlapIntervals(intervals);
        System.out.println(max);
        System.out.println(min);
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        return intervals.length - overlapIntervals(intervals);
    }

    public int overlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));

        int count = 1;
        int end = intervals[0][1];
        for (int[] interval : intervals) {
            int start = interval[0];
            if (start >= end) {
                count++;
                end = interval[1];
            }
        }
        return count;
    }
}
