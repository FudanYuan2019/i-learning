package base.list;

import util.PrintUtil;

import java.util.Stack;

/**
 * @Author: Jeremy
 * @Date: 2020/9/22 23:55
 */
public class Reverse {
    public static void main(String[] args) {
        ListNode head = ListNodeSerializer.deserialize("1,2,3,4,5");
        int m = 2;
        int n = 4;

        Reverse reverse = new Reverse();

        ListNode res = reverse.reverseList(head);
        String resStr = ListNodeSerializer.serialize(res);
        PrintUtil.print(resStr);

        head = ListNodeSerializer.deserialize("1,2,3,4,5");
        ListNode newHead = reverse.reverseBetween(head, m, n);
        String newHeadStr = ListNodeSerializer.serialize(newHead);
        PrintUtil.print(newHeadStr);

        int[] array = reverse.reversePrint(head);
        PrintUtil.print(array);

        head = ListNodeSerializer.deserialize("1,2,3,4,5");
        ListNode kGroups = reverse.reverseKGroup(head, 2);
        String kGroupsStr = ListNodeSerializer.serialize(kGroups);
        PrintUtil.print(kGroupsStr);

    }

    /**
     * LeetCode 206. 反转链表
     * 反转一个单链表。
     * <p>
     * 示例:
     * <p>
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * LeetCode 92. 反转链表II
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     * <p>
     * 说明:
     * 1 ≤ m ≤ n ≤ 链表长度。
     * <p>
     * 示例:
     * <p>
     * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * 输出: 1->4->3->2->5->NULL
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 找到需要翻转节点的上一节点
        ListNode last = dummyHead;
        for (int i = 1; i < m; i++) {
            last = last.next;
        }

        // 依次翻转需要翻转的节点
        ListNode node = last.next;
        for (int i = m; i < n; i++) {
            ListNode next = node.next;
            node.next = next.next;
            next.next = last.next;
            last.next = next;
        }
        return dummyHead.next;
    }


    /**
     * LeetCode 25. K 个一组翻转链表
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     * <p>
     * k 是一个正整数，它的值小于或等于链表的长度。
     * <p>
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * <p>
     * 示例：
     * <p>
     * 给你这个链表：1->2->3->4->5
     * <p>
     * 当 k = 2 时，应当返回: 2->1->4->3->5
     * <p>
     * 当 k = 3 时，应当返回: 3->2->1->4->5
     * <p>
     * 说明：
     * <p>
     * 你的算法只能使用常数的额外空间。
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode dummyHead = new ListNode();
        dummyHead.next = head;

        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        ListNode last = dummyHead;
        int i = 0;
        while (i < len) {
            if (i + k > len) {
                break;
            }
            // 依次翻转需要翻转的节点
            ListNode node = last.next;
            for (int j = 0; j < k - 1; j++) {
                ListNode next = node.next;
                node.next = next.next;
                next.next = last.next;
                last.next = next;
            }
            last = node;
            i += k;
        }

        return dummyHead.next;
    }


    /**
     * LeetCode 24. 两两交换链表中的节点
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * <p>
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * <p>
     * 示例:
     * <p>
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        return reverseKGroup(head, 2);
    }

    /**
     * 剑指 Offer 06. 从尾到头打印链表
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     * <p>
     * 示例 1：
     * <p>
     * 输入：head = [1,3,2]
     * 输出：[2,3,1]
     *
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[]{};
        }

        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }

        int[] res = new int[stack.size()];
        int count = 0;
        while (!stack.isEmpty()) {
            res[count++] = stack.pop();
        }
        return res;
    }
}
