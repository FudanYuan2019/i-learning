package strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jeremy
 * @Date: 2020/8/31 22:23
 */
public class SortAlgoFactory {
    private static List<RangeAlgo> algorithms = new ArrayList<>();

    static {
        algorithms.add(new RangeAlgo(0, 10, new InsertSortAlgo()));
        algorithms.add(new RangeAlgo(10, 20, new MergeSortAlgo()));
        algorithms.add(new RangeAlgo(20, Integer.MAX_VALUE, new QuickSortAlgo()));
    }

    public static void addAlgorithms(RangeAlgo rangeAlgo) {
        algorithms.add(rangeAlgo);
    }

    public static SortAlgorithm newSortAlgorithm(Integer num) {
        for (RangeAlgo algo : algorithms) {
            if (algo.inRange(num)) {
                System.out.println(String.format("use sort algorithm %s", algo.getAlgorithm().getClass()));
                return algo.getAlgorithm();
            }
        }
        throw new IllegalStateException("invalid num");
    }

    private static class RangeAlgo {
        private Integer min;
        private Integer max;

        private SortAlgorithm algorithm;

        public SortAlgorithm getAlgorithm() {
            return algorithm;
        }

        public RangeAlgo(Integer min, Integer max, SortAlgorithm algorithm) {
            this.min = min;
            this.max = max;
            this.algorithm = algorithm;
        }

        public boolean inRange(Integer num) {
            return num >= this.min && num < this.max;
        }
    }
}
