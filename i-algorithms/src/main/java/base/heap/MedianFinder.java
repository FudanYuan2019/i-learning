package base.heap;

import util.PrintUtil;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Author: Jeremy
 * @Date: 2020/9/10 11:48
 */
public class MedianFinder {
    private Queue<Integer> rightHeap, leftHeap;

    public MedianFinder() {
        leftHeap = new PriorityQueue<>((x, y) -> (y - x)); // 大顶堆，保存较小的一半
        rightHeap = new PriorityQueue<>(); // 小顶堆，保存较大的一半
    }

    public void addNum(int num) {
        if (rightHeap.size() == leftHeap.size()) {
            rightHeap.add(num);
            leftHeap.add(rightHeap.poll());
        } else {
            leftHeap.add(num);
            rightHeap.add(leftHeap.poll());
        }
    }

    public double findMedian() {
        return rightHeap.size() != leftHeap.size() ? leftHeap.peek() : (rightHeap.peek() + leftHeap.peek()) / 2.0;
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        double median = medianFinder.findMedian();
        PrintUtil.print(median);
        medianFinder.addNum(2);
        median = medianFinder.findMedian();
        PrintUtil.print(median);
        medianFinder.addNum(3);
        median = medianFinder.findMedian();
        PrintUtil.print(median);
        medianFinder.addNum(4);
        median = medianFinder.findMedian();
        PrintUtil.print(median);
    }
}
