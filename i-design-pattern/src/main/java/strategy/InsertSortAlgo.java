package strategy;

/**
 * @Author: Jeremy
 * @Date: 2020/8/31 22:22
 */
public class InsertSortAlgo implements SortAlgorithm {
    @Override
    public int[] sort(int[] list) {
        if (list == null || list.length < 2) {
            return list;
        }

        for (int i = 0; i < list.length; i++) {
            int value = list[i];
            int j = i;
            for (; j > 0; j--) {
                // 移动数据
                if (value < list[j - 1]) {
                    list[j] = list[j - 1];
                } else {
                    break;
                }
            }
            // 插入数据
            list[j] = value;
        }
        return list;
    }
}
