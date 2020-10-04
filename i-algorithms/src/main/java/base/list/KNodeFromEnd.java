package base.list;

/**
 * @Author: Jeremy
 * @Date: 2020/9/23 11:24
 */
public class KNodeFromEnd {
    public static void main(String[] args) {

    }

    /**
     * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
     *
     * @param head
     * @param k
     * @return
     */
    public int kthToLast(ListNode head, int k) {
        if (head == null || k <= 0) {
            return -1;
        }
        ListNode first = head;
        while (k-- > 0) {
            first = first.next;
        }

        ListNode second = head;
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        return second.val;
    }

    /**
     * 剑指 Offer 22. 链表中倒数第k个节点
     * 输入一个链表，输出该链表中倒数第k个节点。
     * 为了符合大多数人的习惯，本题从1开始计数，
     * 即链表的尾节点是倒数第1个节点。
     * 例如，一个链表有6个节点，从头节点开始，
     * 它们的值依次是1、2、3、4、5、6。
     * 这个链表的倒数第3个节点是值为4的节点。
     * <p>
     * 示例：
     * <p>
     * 给定一个链表: 1->2->3->4->5, 和 k = 2.
     * <p>
     * 返回链表 4->5.
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode kNodeFromEnd(ListNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }

        ListNode first = head;
        while (k-- > 0) {
            first = first.next;
        }

        ListNode second = head;
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        return second;
    }

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * <p>
     * 示例：
     * <p>
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * <p>
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 说明：
     * <p>
     * 给定的 n 保证是有效的。
     * <p>
     * 进阶：
     * <p>
     * 你能尝试使用一趟扫描实现吗？
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }
        // 先走K步
        int n = 0;
        ListNode first = head;
        while (first != null) {
            if (n == k) {
                break;
            }
            first = first.next;
            n++;
        }

        // 当第一个指针走到链表尾部时，第二个指针正好走到倒数第K个节点
        ListNode second = head;
        ListNode last = null;
        while (first != null && second != null) {
            first = first.next;
            last = second;
            second = second.next;
        }

        // 为防止k大于链表长度，因此需要判断last是否为空
        if (last != null && second != null) {
            last.next = second.next;
        }

        // 如果要删除的是头节点，则返回头节点的下一节点即可
        if (second == head) {
            return head.next;
        }
        return head;
    }
}
