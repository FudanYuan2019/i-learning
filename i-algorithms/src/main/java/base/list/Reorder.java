package base.list;

import util.PrintUtil;

/**
 * @Author: Jeremy
 * @Date: 2020/10/20 19:11
 */
public class Reorder {
    public static void main(String[] args) {
        ListNode head = ListNodeSerializer.deserialize("1,2,3");
        Reorder reorder = new Reorder();
        reorder.reorderList(head);

        PrintUtil.print(ListNodeSerializer.serialize(head));
    }

    /**
     * LeetCode 143. 重排链表
     * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     * <p>
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * <p>
     * 示例 1:
     * <p>
     * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
     * 示例 2:
     * <p>
     * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }

        // 走到链表中点
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 反转链表中点之后的部分
        ListNode pre = null;
        ListNode cur = slow.next;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        // 切断中点之后的部分
        slow.next = null;
        ListNode node1 = head;
        ListNode node2 = pre;
        while (node1 != null && node2 != null) {
            ListNode next1 = node1.next;
            ListNode next2 = node2.next;
            node1.next = node2;
            node2.next = next1;
            node1 = next1;
            node2 = next2;
        }
    }
}
