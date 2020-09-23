package base.list;

/**
 * @Author: Jeremy
 * @Date: 2020/9/23 11:30
 */
public class ListCloner {
    public static class Node {
        int val;
        Node next = null;
        Node random = null;

        Node(int val) {
            this.val = val;
        }
    }

    /**
     * LeetCode 138. 复制带随机指针的链表
     * 请实现 copyRandomList 函数，复制一个复杂链表。
     * 在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
     * 还有一个 random 指针指向链表中的任意节点或者 null。
     *
     * @param head
     * @return
     */
    public Node clone(Node head) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        Node next;
        // 复制节点
        while (cur != null) {
            next = cur.next;
            Node copy = new Node(cur.val);
            copy.next = cur.next;
            cur.next = copy;
            cur = next;
        }

        // 确定random节点
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = next;
        }

        // 分开两链表，保证原始链表的正确性
        Node newHead = head.next;
        cur = head;
        Node copy = cur.next;
        while (cur != null) {
            next = cur.next.next;
            cur.next = copy.next;
            copy.next = next == null ? null : next.next;
            copy = copy.next;
            cur = next;
        }
        return newHead;
    }
}
