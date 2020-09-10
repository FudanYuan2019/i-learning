package base.tree;

import util.PrintUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树求路径和，LeetCode 129，
 *
 * @Author: Jeremy
 * @Date: 2020/9/10 14:38
 */
public class TreePathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode leftLeft = new TreeNode(4);
        root.left = left;
        root.right = right;
        left.left = leftLeft;

        TreePathSum treePathSum = new TreePathSum();
        int sum = treePathSum.sumNumbers(root);
        PrintUtil.print(sum);
    }

    /**
     * 129. 求根到叶子节点数字之和
     * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
     * <p>
     * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
     * <p>
     * 计算从根到叶子节点生成的所有数字之和。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,3]
     *   1
     *  / \
     * 2   3
     * 输出: 25
     * 解释:
     * 从根到叶子节点路径 1->2 代表数字 12.
     * 从根到叶子节点路径 1->3 代表数字 13.
     * 因此，数字总和 = 12 + 13 = 25.
     * 示例 2:
     * <p>
     * 输入: [4,9,0,5,1]
     *   4
     *  / \
     * 9   0
     *    / \
     *   5   1
     * 输出: 1026
     * 解释:
     * 从根到叶子节点路径 4->9->5 代表数字 495.
     * 从根到叶子节点路径 4->9->1 代表数字 491.
     * 从根到叶子节点路径 4->0 代表数字 40.
     * 因此，数字总和 = 495 + 491 + 40 = 1026.
     */
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        return dfs(root, path);
    }

    private int dfs(TreeNode root, List<Integer> path) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            int num = 0;
            for (Integer val : path) {
                num *= 10;
                num += val;
            }
            return num;
        }

        int res = 0;
        if (root.left != null) {
            path.add(root.left.val);
            res += dfs(root.left, path);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            path.add(root.right.val);
            res += dfs(root.right, path);
            path.remove(path.size() - 1);
        }
        return res;
    }
}
