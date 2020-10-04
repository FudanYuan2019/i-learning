package strategy;


/**
 * @Author: Jeremy
 * @Date: 2020/8/31 22:22
 */
public class QuickSortAlgo implements SortAlgorithm {

    @Override
    public int[] sort(int[] list) {
        if (list == null || list.length < 2) {
            return list;
        }
        partition(list, 0, list.length - 1);
        return list;

    }

    private void partition(int[] list, int left, int right) {
        if (left > right) {
            return;
        }
        int i = left;
        int j = right;
        int base = list[left];
        while (i < j) {
            while (list[j] >= base && i < j) {
                j--;
            }

            if (i < j) {
                list[i] = list[j];
            }

            while (list[i] <= base && i < j) {
                i++;
            }
            if (i < j) {
                list[j] = list[i];
            }
        }
        list[i] = base;

        partition(list, left, i - 1);
        partition(list, i + 1, right);
    }
}
