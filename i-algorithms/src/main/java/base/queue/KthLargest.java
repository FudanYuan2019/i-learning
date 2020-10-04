package base.queue;

import util.PrintUtil;

import java.util.PriorityQueue;

/**
 * LeetCode 703  数据流中的第K大元素
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * <p>
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。
 * 每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 * <p>
 * 示例:
 * <p>
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * 说明:
 * 你可以假设 nums 的长度≥ k-1 且k ≥ 1。
 *
 * @Author: Jeremy
 * @Date: 2020/9/7 16:55
 */
public class KthLargest {
    private PriorityQueue<Integer> priorityQueue;
    private int limit;
    public KthLargest(int k, int[] nums) {
        priorityQueue = new PriorityQueue<>(k);
        this.limit = k;
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (priorityQueue.size() < limit) {
            priorityQueue.add(val);
        } else if (val > priorityQueue.peek()) {
            priorityQueue.poll();
            priorityQueue.add(val);
        }
        return priorityQueue.peek();
    }

    public static void main(String[] args) {
        int k = 3;
        int[] nums = new int[]{4,5,8,2};
        KthLargest kthLargest = new KthLargest(k, nums);
        int top = kthLargest.add(3);
        PrintUtil.print(top);
        top = kthLargest.add(5);
        PrintUtil.print(top);
        top = kthLargest.add(10);
        PrintUtil.print(top);
        top = kthLargest.add(9);
        PrintUtil.print(top);
        top = kthLargest.add(4);
        PrintUtil.print(top);
    }
}
