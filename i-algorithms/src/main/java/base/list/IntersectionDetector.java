package base.list;

import util.PrintUtil;

/**
 * @Author: Jeremy
 * @Date: 2020/9/23 11:24
 */
public class IntersectionDetector {
    public static void main(String[] args) {
        ListNode headA = new ListNode(0);
        ListNode nodeA1 = new ListNode(1);
        ListNode nodeA2 = new ListNode(2);

        ListNode headB = new ListNode(1);

        headA.next = nodeA1;
        nodeA1.next = nodeA2;

        headB.next = nodeA2;

        IntersectionDetector intersectionDetector = new IntersectionDetector();
        ListNode intersection = intersectionDetector.getIntersectionNode(headA, headB);
        PrintUtil.print(intersection);
    }

    /**
     * LeetCode 160. 相交链表
     * 编写一个程序，找到两个单链表相交的起始节点。
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        // 先获取两链表长度
        int lenA = 0;
        int lenB = 0;
        ListNode curA = headA;
        ListNode curB = headB;
        while (curA != null) {
            lenA++;
            curA = curA.next;
        }
        while (curB != null) {
            lenB++;
            curB = curB.next;
        }

        curA = headA;
        curB = headB;
        // 如果链表A较长，则A先走delta步
        if (lenA > lenB) {
            int delta = lenA - lenB;
            while (delta > 0) {
                delta--;
                curA = curA.next;
            }
        }
        // 如果链表B较长，则B先走delta步
        if (lenA < lenB) {
            int delta = lenB - lenA;
            while (delta > 0) {
                delta--;
                curB = curB.next;
            }
        }

        // 遍历剩下的节点，如果相遇则相交
        while (curA != null && curB != null) {
            if (curA == curB) {
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }
        return null;
    }
}
