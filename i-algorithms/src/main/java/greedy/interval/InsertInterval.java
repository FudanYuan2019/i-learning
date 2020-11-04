package greedy.interval;

import util.PrintUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: Jeremy
 * @Date: 2020/11/4 09:35
 */
public class InsertInterval {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 3}, {6, 9}};
        int[] newIntervals = new int[]{2, 5};
        InsertInterval insertInterval = new InsertInterval();
        int[][] res = insertInterval.insert(intervals, newIntervals);
        PrintUtil.print(res);

        intervals = new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        newIntervals = new int[]{4, 8};
        res = insertInterval.insert(intervals, newIntervals);
        PrintUtil.print(res);

        intervals = new int[][]{{1, 5}};
        newIntervals = new int[]{2, 3};
        res = insertInterval.insert(intervals, newIntervals);
        PrintUtil.print(res);
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }
}
