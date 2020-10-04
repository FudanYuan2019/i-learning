package base.list;

import util.PrintUtil;

import java.util.Stack;

/**
 * @Author: Jeremy
 * @Date: 2020/9/22 11:10
 */
public class TwoNumbers {
    public static void main(String[] args) {
        String listStr1 = "7,2,4,3";
        String listStr2 = "5,6,4";
        ListNode list1 = ListNodeSerializer.deserialize(listStr1);
        ListNode list2 = ListNodeSerializer.deserialize(listStr2);

        TwoNumbers twoNumbers = new TwoNumbers();
        ListNode newList = twoNumbers.addTwoNumbers(list1, list2);
        String newListStr = ListNodeSerializer.serialize(newList);
        PrintUtil.print(newListStr);

        ListNode newList1 = twoNumbers.addTwoNumbersII(list1, list2);
        String newListStr1 = ListNodeSerializer.serialize(newList1);
        PrintUtil.print(newListStr1);
    }

    /**
     * LeetCode 2. 两数相加
     * 给出两个 非空 的链表用来表示两个非负的整数。
     * 其中，它们各自的位数是按照 逆序 的方式存储的，
     * 并且它们的每个节点只能存储 一位 数字。
     * <p>
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * <p>
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 示例：
     * <p>
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode dummyHead = new ListNode();
        ListNode last = dummyHead;
        ListNode newNode = null;

        ListNode node1 = l1;
        ListNode node2 = l2;
        int acc = 0;
        while (node1 != null || node2 != null || acc != 0) {
            int v1 = node1 == null ? 0 : node1.val;
            int v2 = node2 == null ? 0 : node2.val;
            int val = v1 + v2 + acc;
            acc = val / 10;
            val %= 10;
            newNode = new ListNode(val);
            last.next = newNode;
            last = newNode;

            node1 = node1 == null ? null : node1.next;
            node2 = node2 == null ? null : node2.next;
        }

        return dummyHead.next;
    }

    /**
     * LeetCode 445. 两数相加 II
     * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。
     * 它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     * <p>
     * 进阶：
     * <p>
     * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
     * <p>
     * 示例：
     * <p>
     * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 8 -> 0 -> 7
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        ListNode node1 = l1;
        ListNode node2 = l2;
        while(node1 != null) {
            stack1.push(node1.val);
            node1 = node1.next;
        }
        while(node2 != null) {
            stack2.push(node2.val);
            node2 = node2.next;
        }

        int acc = 0;
        ListNode dummyHead = new ListNode();
        ListNode node = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || acc != 0) {
            int v1 = stack1.isEmpty() ? 0 : stack1.pop();
            int v2 = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = v1 + v2 + acc;
            acc = sum / 10;
            sum %= 10;

            node = new ListNode(sum);
            node.next = dummyHead.next;
            dummyHead.next = node;
        }

        return dummyHead.next;
    }
}
