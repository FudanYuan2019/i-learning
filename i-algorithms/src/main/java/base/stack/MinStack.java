package base.stack;

import java.util.Stack;

/**
 * LeetCode 155 最小栈
 * <p>
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 * <p>
 * 示例:
 * <p>
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * <p>
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * <p>
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 * @Author: Jeremy
 * @Date: 2020/9/6 15:55
 */
public class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> minValueStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<>();
        minValueStack  = new Stack<>();
        minValueStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        if (!minValueStack.isEmpty() && x < minValueStack.peek()) {
            minValueStack.push(x);
        } else {
            minValueStack.push(minValueStack.peek());
        }
        stack.push(x);
    }

    public void pop() {
        if (!stack.isEmpty()) {
            minValueStack.pop();
            stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minValueStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        int min1 = minStack.getMin();
        System.out.println("min1: " + min1);
        minStack.pop();
        int top = minStack.top();
        System.out.println("top: " + top);
        int min2 = minStack.getMin();
        System.out.println("min2: " + min2);
    }
}
