package base.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 单调队列
 *
 * @Author: Jeremy
 * @Date: 2020/9/6 15:27
 */
public class MonotonicQueue {

    private Deque<Integer> deque = new LinkedList<>();

    public void offer(int value) {
        while (!deque.isEmpty() && value > deque.peekLast()) {
            deque.pollLast();
        }
        deque.offerLast(value);
    }

    public void poll(int n) {
        if (!deque.isEmpty() && n == deque.peekFirst()) {
            deque.pollFirst();
        }
    }

    public int max() {
        if (deque.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        return deque.peekFirst();
    }
}
