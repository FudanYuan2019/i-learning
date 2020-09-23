package base.list;

import util.PrintUtil;

/**
 * @Author: Jeremy
 * @Date: 2020/9/16 11:39
 */
public class Rotate {
    public static void main(String[] args) {
        ListNode listNode1 = ListNodeSerializer.deserialize("1,2,3,4,5,6");
        Rotate rotate = new Rotate();
        ListNode node = rotate.rotateRight(listNode1, 4);
        PrintUtil.print(node);
    }

    /**
     * LeetCode 61. 旋转链表
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->2->3->4->5->NULL, k = 2
     * 输出: 4->5->1->2->3->NULL
     * 解释:
     * 向右旋转 1 步: 5->1->2->3->4->NULL
     * 向右旋转 2 步: 4->5->1->2->3->NULL
     * 示例 2:
     * <p>
     * 输入: 0->1->2->NULL, k = 4
     * 输出: 2->0->1->NULL
     * 解释:
     * 向右旋转 1 步: 2->0->1->NULL
     * 向右旋转 2 步: 1->2->0->NULL
     * 向右旋转 3 步: 0->1->2->NULL
     * 向右旋转 4 步: 2->0->1->NULL
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode rear = null;
        ListNode node = head;
        int len = 0;
        while(node != null) {
            len++;
            rear = node;
            node = node.next;
        }

        k %= len;
        if (k == 0) {
            return dummyHead.next;
        }

        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        ListNode last = slow;
        while (slow != null && fast != null) {
            last = slow;
            slow = slow.next;
            fast = fast.next;
        }

        last.next = null;
        rear.next = dummyHead.next;
        dummyHead.next = slow;

        return dummyHead.next;
    }
}
