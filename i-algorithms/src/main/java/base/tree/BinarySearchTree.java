package base.tree;

import base.list.ListNode;
import util.PrintUtil;

import java.util.Stack;

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

        root = TreeNodeSerialize.deserialize("5,2,13");
        binarySearchTree.convertBST(root);
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

    /**
     * LeetCode 538, 1038. 把二叉搜索树转换为累加树
     * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，
     * 使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
     *
     * 例如：
     *
     * 输入: 原始二叉搜索树:
     *               5
     *             /   \
     *            2     13
     *
     * 输出: 转换为累加树:
     *              18
     *             /   \
     *           20     13
     *
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        int sum = 0;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.right;
            }

            TreeNode top = stack.pop();
            sum += top.val;
            top.val = sum;
            node = top.left;
        }

        return root;
    }


    private TreeNode parent;
    private boolean isLeftTree;
    public void delete(TreeNode root, int val) {
        if (root == null) {
            return;
        }

        TreeNode node = get(root, val);
        if (node == null) {
            return;
        }
        TreeNode left = node.left;
        TreeNode right = node.right;
        if (left == null && right == null) {
            if (isLeftTree) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (right == null) {
            if (isLeftTree) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }
        } else if (left == null) {
            if (isLeftTree) {
                parent.left = node.right;
            } else {
                parent.right = node.right;
            }
        } else {
            TreeNode p = node;
            TreeNode q = node.left;
            while (q.right != null) {
                p = q;
                q = q.right;
            }

            node.val = q.val;
            if (p != node) {
                p.right = q.left;
            } else {
                p.left = q.left;
            }
        }
    }

    private TreeNode get(TreeNode root, int val) {
        TreeNode node = root;
        while(node != null) {
            if (val > node.val) {
                parent = node;
                node = node.right;
                isLeftTree = false;
            } else if (val < node.val) {
                parent = node;
                node = node.left;
                isLeftTree = true;
            } else if (val == node.val) {
                return node;
            }
        }
        return null;
    }
}
