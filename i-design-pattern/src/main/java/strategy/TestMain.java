package strategy;


import java.util.Arrays;

/**
 * @Author: Jeremy
 * @Date: 2020/8/31 22:23
 */
public class TestMain {
    public static void main(String[] args) {
        int[] A = new int[]{3, 1, 2, 4, 6};
        SortAlgorithm sortAlgorithm1 = SortAlgoFactory.newSortAlgorithm(A.length);
        A = sortAlgorithm1.sort(A);
        System.out.println(Arrays.toString(A));

        int[] B = new int[]{3, 1, 2, 4, 6, 2, 4, 10, 9, 20, 39};
        SortAlgorithm sortAlgorithm2 = SortAlgoFactory.newSortAlgorithm(B.length);
        B = sortAlgorithm2.sort(B);
        System.out.println(Arrays.toString(B));

        int[] C = new int[]{3, 1, 2, 4, 6, 2, 4, 10, 9, 20, 39, 3, 1, 2, 4, 6, 2, 4, 10,
                9, 20, 39, 3, 1, 2, 4, 6, 2, 4, 10, 9, 20, 39};
        SortAlgorithm sortAlgorithm3 = SortAlgoFactory.newSortAlgorithm(C.length);
        C = sortAlgorithm3.sort(C);
        System.out.println(Arrays.toString(C));
    }
}
