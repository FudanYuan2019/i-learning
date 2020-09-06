package base.stack;

import java.util.Stack;

/**
 * @Author: Jeremy
 * @Date: 2020/9/6 16:21
 */
public class MaxStack {
    private Stack<Integer> stack;
    private Stack<Integer> maxValueStack;

    /**
     * initialize your data structure here.
     */
    public MaxStack() {
        stack = new Stack<>();
        maxValueStack = new Stack<>();
        maxValueStack.push(Integer.MIN_VALUE);
    }

    public void push(int x) {
        if (!maxValueStack.isEmpty() && x > maxValueStack.peek()) {
            maxValueStack.push(x);
        } else {
            maxValueStack.push(maxValueStack.peek());
        }
        stack.push(x);
    }

    public void pop() {
        if (!stack.isEmpty()) {
            maxValueStack.pop();
            stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMax() {
        return maxValueStack.peek();
    }

    public static void main(String[] args) {
        MaxStack maxStack = new MaxStack();
        maxStack.push(2);
        maxStack.push(0);
        maxStack.push(3);
        int max1 = maxStack.getMax();
        System.out.println("max1: " + max1);
        maxStack.pop();
        int top = maxStack.top();
        System.out.println("top: " + top);
        int max2 = maxStack.getMax();
        System.out.println("max2: " + max2);
    }
}
