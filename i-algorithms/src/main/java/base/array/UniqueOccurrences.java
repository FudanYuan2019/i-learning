package base.array;

import util.PrintUtil;

import java.util.Arrays;
import java.util.BitSet;

/**
 * @Author: Jeremy
 * @Date: 2020/10/28 10:51
 */
public class UniqueOccurrences {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 2, 1, 1, 3};
        UniqueOccurrences uniqueOccurrences = new UniqueOccurrences();
        boolean unique = uniqueOccurrences.uniqueOccurrences(array);
        PrintUtil.print(unique);

        array = new int[]{-3, 0, 1, -3, 1, 1, 1, -3, 10, 0};
        unique = uniqueOccurrences.uniqueOccurrences(array);
        PrintUtil.print(unique);

        array = new int[]{1, 2};
        unique = uniqueOccurrences.uniqueOccurrences(array);
        PrintUtil.print(unique);
    }

    /**
     * 1207. 独一无二的出现次数
     * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
     * <p>
     * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
     * <p>
     * 示例 1：
     * <p>
     * 输入：arr = [1,2,2,1,1,3]
     * 输出：true
     * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
     * 示例 2：
     * <p>
     * 输入：arr = [1,2]
     * 输出：false
     * 示例 3：
     * <p>
     * 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
     * 输出：true
     * <p>
     * 提示：
     * <p>
     * 1 <= arr.length <= 1000
     * -1000 <= arr[i] <= 1000
     *
     * @param arr
     * @return
     */
    public boolean uniqueOccurrences(int[] arr) {
        Arrays.sort(arr);
        BitSet bitMap = new BitSet(1000);
        int i = 0;
        int len = arr.length;
        while (i < len) {
            int tmp = arr[i];
            int j = i + 1;
            while (j < len && arr[j] == tmp) {
                j++;
            }
            int occurs = j - i;
            if (bitMap.get(occurs)) {
                return false;
            }
            bitMap.set(occurs, true);
            i = j;
        }

        return true;
    }
}
