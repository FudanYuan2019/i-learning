package base.tree;

import base.list.ListNode;
import util.PrintUtil;

import java.util.*;

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

        root = TreeNodeSerialize.deserialize("2,1,2");
        int[] mode = binarySearchTree.findMode(root);
        PrintUtil.print(mode);

        root = binarySearchTree.insertIntoBST(root, 4);
        PrintUtil.print(TreeNodeSerialize.serialize(root));

        root = TreeNodeSerialize.deserialize("5,4,7");
        int min = binarySearchTree.getMinimumDifference(root);
        PrintUtil.print(min);
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

    /**
     * 501. 二叉搜索树中的众数
     * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
     * <p>
     * 假定 BST 有如下定义：
     * <p>
     * 结点左子树中所含结点的值小于等于当前结点的值
     * 结点右子树中所含结点的值大于等于当前结点的值
     * 左子树和右子树都是二叉搜索树
     * 例如：
     * 给定 BST [1,null,2,2],
     * <p>
     *    1
     *     \
     *      2
     *     /
     *    2
     * 返回[2].
     *
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }

        Set<Integer> set = new HashSet<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        int last = root.val;
        int max = 1;
        int count = 0;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            TreeNode top = stack.pop();
            if (top.val == last) {
                count++;
            } else {
                last = top.val;
                count = 1;
            }

            if (count == max) {
                set.add(top.val);
            } else if (count > max) {
                set.clear();
                set.add(top.val);
                max = count;
            }

            node = top.right;
        }

        int len = set.size();
        int[] res = new int[len];
        int index = 0;
        for (Integer ele : set) {
            res[index++] = ele;
        }

        return res;
    }

    /**
     * 701. 二叉搜索树中的插入操作
     * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。
     * 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。
     *
     * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。
     * 你可以返回任意有效的结果。
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        TreeNode node = root;
        TreeNode parent = null;
        boolean isLeft = false;
        while (node != null) {
            if(val > node.val) {
                parent = node;
                node = node.right;
                isLeft = false;
            } else if (val < node.val) {
                parent = node;
                node = node.left;
                isLeft = true;
            } else if (val == node.val) {
                break;
            }
        }
        if (isLeft) {
            parent.left = new TreeNode(val);
        } else {
            parent.right = new TreeNode(val);
        }
        return root;
    }

    /**
     * LeetCode 530. 二叉搜索树的最小绝对差
     * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
     *
     * 示例：
     *
     * 输入：
     *
     *    1
     *     \
     *      3
     *     /
     *    2
     *
     * 输出：
     * 1
     *
     * 解释：
     * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        List<Integer> list = inorderTraverse(root);
        int min = Integer.MAX_VALUE;
        for(int i = 1; i < list.size(); i++) {
            int diff = Math.abs(list.get(i - 1) - list.get(i));
            if (diff < min) {
                min = diff;
            }
        }
        return min;
    }

    private List<Integer> inorderTraverse(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            TreeNode top = stack.pop();
            res.add(top.val);
            node = top.right;
        }
        return res;
    }
}
