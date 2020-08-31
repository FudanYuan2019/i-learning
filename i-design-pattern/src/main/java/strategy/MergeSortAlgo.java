package strategy;

/**
 * @Author: Jeremy
 * @Date: 2020/8/31 22:22
 */
public class MergeSortAlgo implements SortAlgorithm {
    @Override
    public int[] sort(int[] list) {
        if (list == null || list.length < 2) {
            return list;
        }

        if (list.length == 2) {
            if (list[0] > list[1]) {
                int tmp = list[0];
                list[0] = list[1];
                list[1] = tmp;
            }
            return list;
        }
        int left = 0;
        int right = list.length;
        int mid = (left + right) / 2;
        int[] leftA = new int[mid];
        int[] rightA = new int[right - mid];
        int i = 0;
        int j = mid;
        while (i < mid) {
            leftA[i] = list[i++];
        }
        while (j < right) {
            rightA[j - mid] = list[j++];
        }
        leftA = sort(leftA);
        rightA = sort(rightA);
        return merge(leftA, rightA);
    }

    private static int[] merge(int[] list, int[] B) {
        int lenA = list.length;
        int lenB = B.length;
        int[] C = new int[lenA + lenB];
        int i = 0;
        int j = 0;
        int m = 0;
        while (i < lenA && j < lenB) {
            if (list[i] <= B[j]) {
                C[m++] = list[i++];
            } else {
                C[m++] = B[j++];
            }
        }
        while (i < lenA) {
            C[m++] = list[i++];
        }

        while (j < lenB) {
            C[m++] = B[j++];
        }
        return C;
    }
}
