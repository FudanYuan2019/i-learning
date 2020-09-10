package base.tree;

import util.PrintUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

        List<String> paths = treeTraverse.binaryTreePaths(root);
        PrintUtil.print(paths);

        List<Double> average = treeTraverse.averageOfLevels(root);
        PrintUtil.print(average);
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
     * 二叉树的所有路径 LeetCode 257
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例:
     * <p>
     * 输入:
     * <p>
     *    1
     *  /   \
     * 2     3
     * \
     * 5
     * <p>
     * 输出: ["1->2->5", "1->3"]
     * <p>
     * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        String path = "";
        List<String> paths = new ArrayList<>();
        traverse(root, path, paths);
        return paths;
    }

    public void traverse(TreeNode root, String path, List<String> paths) {
        if (root == null) {
            return;
        }

        path += root.val;
        if (root.left == null && root.right == null) {
            paths.add(path);
        } else {
            path += "->";
            traverse(root.left, path, paths);
            traverse(root.right, path, paths);
        }
    }
}
