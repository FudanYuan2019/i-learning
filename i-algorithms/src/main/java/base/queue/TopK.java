package base.queue;

import util.PrintUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * TopK问题
 *
 * @Author: Jeremy
 * @Date: 2020/9/7 16:25
 */
public class TopK {
    /**
     * LeetCode 347
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     * 示例 2:
     * <p>
     * 输入: nums = [1], k = 1
     * 输出: [1]
     * <p>
     * 提示：
     * <p>
     * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
     * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
     * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
     * 你可以按任意顺序返回答案。
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 0);
            }
            map.put(num, map.get(num) - 1);
        }

        CustomPriorityQueue.Node[] nodes = new CustomPriorityQueue.Node[map.size()];
        int count = 0;
        for (Integer key : map.keySet()) {
            nodes[count++] = new CustomPriorityQueue.Node(key, map.get(key));
        }

        int[] res = new int[k];
        count = 0;
        CustomPriorityQueue customPriorityQueue = new CustomPriorityQueue(nodes.length, nodes);
        while (!customPriorityQueue.isEmpty()) {
            if (k == 0) {
                break;
            }
            CustomPriorityQueue.Node node = customPriorityQueue.poll();
            res[count++] = node.getKey();
            k--;
        }

        return res;
    }

    /**
     * LeetCode 215 数组中的第K个最大元素
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     * 示例 2:
     * <p>
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     * 说明:
     * <p>
     * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        CustomPriorityQueue customPriorityQueue = new CustomPriorityQueue(nums.length, nums);
        customPriorityQueue.print();
        while (!customPriorityQueue.isEmpty()) {
            CustomPriorityQueue.Node node = customPriorityQueue.poll();
            if (--k == 0) {
                return node.getKey();
            }
        }
        return customPriorityQueue.top().getKey();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int k = 2;

        TopK topK = new TopK();
        int[] res = topK.topKFrequent(nums, k);
        PrintUtil.print(res);

        nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        k = 4;
        int kth = topK.findKthLargest(nums, k);
        PrintUtil.print(kth);
    }

}
