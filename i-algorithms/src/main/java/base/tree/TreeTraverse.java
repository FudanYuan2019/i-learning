package base.tree;

import util.PrintUtil;

import java.util.*;

/**
 * 树的遍历，LeetCode 102, 107, 257
 *
 * @Author: Jeremy
 * @Date: 2020/9/6 14:55
 */
public class TreeTraverse {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;

        TreeTraverse treeTraverse = new TreeTraverse();
        List<List<Integer>> levelOrder = treeTraverse.levelOrder(root);
        for (List<Integer> subList : levelOrder) {
            PrintUtil.print(subList);
        }

        levelOrder = treeTraverse.levelOrderBottom(root);
        for (List<Integer> subList : levelOrder) {
            PrintUtil.print(subList);
        }

        List<Double> average = treeTraverse.averageOfLevels(root);
        PrintUtil.print(average);

        List<Integer> preOrder = treeTraverse.preorderTraversal(root);
        PrintUtil.print(preOrder);

        List<Integer> inOrder = treeTraverse.inorderTraversal(root);
        PrintUtil.print(inOrder);

        List<Integer> postOrder = treeTraverse.postOrderTraversal(root);
        PrintUtil.print(postOrder);

        List<List<Integer>> zigZagOrder = treeTraverse.zigzagLevelOrder(root);
        for (List<Integer> list : zigZagOrder) {
            PrintUtil.print(list);
        }

        boolean symmetric = treeTraverse.isSymmetric(root);
        PrintUtil.print(symmetric);
    }

    /**
     * LeetCode 144. 二叉树的前序遍历
     * <p>
     * 给定一个二叉树，返回它的 前序 遍历。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     * <p>
     * 输出: [1,2,3]
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();
            res.add(top.val);
            if (top.right != null) {
                stack.push(top.right);
            }
            if (top.left != null) {
                stack.push(top.left);
            }
        }
        return res;
    }

    /**
     * leetCode 94 二叉树中序遍历
     * 给定一个二叉树，返回它的中序遍历。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * 输出: [1,3,2]
     * <p>
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

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

    /**
     * 给定一个二叉树，返回它的 后序 遍历。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     * <p>
     * 输出: [3,2,1]
     * @param root
     * @return
     */
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();
            res.add(0, top.val);
            if (top.left != null) {
                stack.push(top.left);
            }
            if (top.right != null) {
                stack.push(top.right);
            }
        }
        return res;
    }

    /**
     * LeetCode 102 二叉树的层序遍历
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     * <p>
     *  
     * <p>
     * 示例：
     * 二叉树：[3,9,20,null,null,15,7],
     * <p>
     *   3
     *  / \
     * 9  20
     *   /  \
     *  15   7
     * 返回其层次遍历结果：
     * <p>
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> levelList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode last = root;

        // 添加根结点
        levelList.add(root.val);
        res.add(new ArrayList<>(levelList));
        levelList.clear();

        TreeNode newLast = null;
        while (!queue.isEmpty()) {
            TreeNode top = queue.poll();
            if (top.left != null) {
                queue.offer(top.left);
                newLast = top.left;
                levelList.add(top.left.val);
            }
            if (top.right != null) {
                queue.offer(top.right);
                newLast = top.right;
                levelList.add(top.right.val);
            }
            if (top == last) {
                last = newLast;
                if (!levelList.isEmpty()) {
                    res.add(new ArrayList<>(levelList));
                    levelList.clear();
                }
            }
        }
        return res;
    }

    /**
     * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     * <p>
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     * <p>
     *   3
     *  / \
     * 9  20
     *   /  \
     *  15   7
     * 返回其自底向上的层次遍历为：
     * <p>
     * [
     * [15,7],
     * [9,20],
     * [3]
     * ]
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> levelList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode last = root;

        // 添加根结点
        levelList.add(root.val);
        res.add(new ArrayList<>(levelList));
        levelList.clear();

        TreeNode newLast = null;
        while (!queue.isEmpty()) {
            TreeNode top = queue.poll();
            if (top.left != null) {
                queue.offer(top.left);
                newLast = top.left;
                levelList.add(top.left.val);
            }
            if (top.right != null) {
                queue.offer(top.right);
                newLast = top.right;
                levelList.add(top.right.val);
            }
            if (top == last) {
                last = newLast;
                if (!levelList.isEmpty()) {
                    res.add(0, new ArrayList<>(levelList));
                    levelList.clear();
                }
            }
        }
        return res;
    }

    /**
     * LeetCode 637  二叉树的层平均值
     * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
     * 示例 1：
     * <p>
     * 输入：
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 输出：[3, 14.5, 11]
     * 解释：
     * 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        res.add(Double.valueOf(root.val));

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode last = root;
        TreeNode newLast = null;
        double sum = 0;
        int count = 0;
        while (!queue.isEmpty()) {
            TreeNode top = queue.poll();
            if (top.left != null) {
                queue.offer(top.left);
                newLast = top.left;
                sum += top.left.val;
                count++;
            }
            if (top.right != null) {
                queue.offer(top.right);
                newLast = top.right;
                sum += top.right.val;
                count++;
            }
            if (top == last) {
                last = newLast;
                if (count != 0) {
                    res.add(sum / count);
                    sum = 0;
                    count = 0;
                }
            }
        }
        return res;
    }

    /**
     * LeetCode 101. 对称二叉树
     * <p>
     * 给定一个二叉树，检查它是否是镜像对称的。
     * <p>
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     * <p>
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     * <p>
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     * <p>
     *     1
     *    / \
     *   2   2
     *    \   \
     *    3    3
     * <p>
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode n1 = queue.poll();
            TreeNode n2 = queue.poll();

            if (n1 == null && n2 == null) {
                continue;
            }
            if ((n1 == null || n2 == null) || (n1.val != n2.val)) {
                return false;
            }

            queue.offer(n1.left);
            queue.offer(n2.right);

            queue.offer(n1.right);
            queue.offer(n2.left);
        }
        return true;
    }

    /**
     * LeetCode 103. 二叉树的锯齿形层次遍历
     * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     * <p>
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     * <p>
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回锯齿形层次遍历如下：
     * <p>
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        res.add(new ArrayList<>(list));
        list.clear();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode last = root;
        TreeNode newLast = null;
        int i = 0;
        while (!queue.isEmpty()) {
            TreeNode top = queue.poll();
            if (top.left != null) {
                queue.offer(top.left);
                newLast = top.left;
                if (i % 2 == 0) {
                    list.add(0, top.left.val);
                } else {
                    list.add(top.left.val);
                }
            }
            if (top.right != null) {
                queue.offer(top.right);
                newLast = top.right;
                if (i % 2 == 0) {
                    list.add(0, top.right.val);
                } else {
                    list.add(top.right.val);
                }
            }
            if (last == top) {
                last = newLast;
                if (!list.isEmpty()) {
                    res.add(new ArrayList<>(list));
                    list.clear();
                }
                i++;
            }
        }
        return res;
    }
}
