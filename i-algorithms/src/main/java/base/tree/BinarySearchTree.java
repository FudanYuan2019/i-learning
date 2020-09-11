package base.tree;

import base.list.ListNode;
import util.PrintUtil;

/**
 * 二叉搜索树
 *
 * @Author: Jeremy
 * @Date: 2020/9/11 16:53
 */
public class BinarySearchTree {
    private ListNode head;
    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();

        ListNode head = new ListNode(-10);
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(9);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        TreeNode root = binarySearchTree.sortedListToBST(head);
        PrintUtil.print(root);
    }
    /**
     * LeetCode 108. 将有序数组转换为二叉搜索树
     * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     * <p>
     * 示例:
     * <p>
     * 给定有序数组: [-10,-3,0,5,9],
     * <p>
     * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     * <p>
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBSTHelper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBSTHelper(nums, left, mid - 1);
        root.right = sortedArrayToBSTHelper(nums, mid + 1, right);
        return root;
    }

    /**
     * LeetCode 109. 有序链表转换二叉搜索树
     * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     * <p>
     * 示例:
     * <p>
     * 给定的有序链表： [-10, -3, 0, 5, 9],
     * <p>
     * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
     * <p>
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     * <p>
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        this.head = head;
        return sortedListToBSTHelper(0, getListLength(head) - 1);
    }

    private TreeNode sortedListToBSTHelper(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode();
        root.left = sortedListToBSTHelper(left, mid - 1);
        root.val = head.val;
        this.head = head.next;
        root.right = sortedListToBSTHelper(mid + 1, right);
        return root;
    }

    private int getListLength(ListNode head) {
        if (head == null) {
            return 0;
        }
        ListNode cur = head;
        int length = 0;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }
}
