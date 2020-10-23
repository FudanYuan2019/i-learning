package base.list;

import util.PrintUtil;

/**
 * @Author: Jeremy
 * @Date: 2020/10/23 10:25
 */
public class Palindrome {
    public static void main(String[] args) {
        ListNode head = ListNodeSerializer.deserialize("1,2,3,2,1");
        Palindrome palindrome = new Palindrome();
        boolean res = palindrome.isPalindrome(head);
        PrintUtil.print(res);

        head = ListNodeSerializer.deserialize("1,2,2,1");
        res = palindrome.isPalindrome(head);
        PrintUtil.print(res);

        head = ListNodeSerializer.deserialize("1,2,1,1");
        res = palindrome.isPalindrome(head);
        PrintUtil.print(res);
    }

    /**
     * LeetCode 234. 回文链表
     * 请判断一个链表是否为回文链表。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->2
     * 输出: false
     * 示例 2:
     * <p>
     * 输入: 1->2->2->1
     * 输出: true
     * 进阶：
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        // 双指针，快指针一次前进2个节点，慢指针一次前进1个节点，并且做链表反转
        // 当快指针走到链表尾部时，慢指针正好走到中点，此时从链表中点分别向两边遍历。
        // 判断两两节点是否相等。
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }
        // 如果fast不为空，代表有奇数个节点
        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null && pre != null) {
            if (slow.val != pre.val) {
                return false;
            }
            slow = slow.next;
            pre = pre.next;
        }

        return true;
    }
}
