package util;

import java.util.List;

/**
 * @Author: Jeremy
 * @Date: 2020/9/5 11:13
 */
public class PrintUtil {
    public static void nextLine() {
        System.out.println();
    }

    public static void print(List list) {
        for (Object object : list) {
            System.out.print(object + " ");
        }
        System.out.println();
    }

    public static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void print(Object obj) {
        System.out.println(obj);
    }
}
