package base.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 104 二叉树的最大深度
 * <p>
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 * @Author: Jeremy
 * @Date: 2020/9/10 17:18
 */
public class TreeDepth {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<QueueNode> queue = new LinkedList<>();
        queue.offer(new QueueNode(root, 1));
        while (!queue.isEmpty()) {
            QueueNode top = queue.poll();
            TreeNode topNode = top.node;
            if (topNode.left == null && topNode.right == null){
                return top.depth;
            }
            if (topNode.left != null) {
                queue.offer(new QueueNode(topNode.left, top.depth + 1));
            }
            if (topNode.right != null) {
                queue.offer(new QueueNode(topNode.right, top.depth + 1));
            }
        }
        return 0;
    }

    private static class QueueNode {
        private TreeNode node;
        private int depth;
        public QueueNode(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        root.right = node1;
        node1.right = node2;
        node2.right = node3;

        TreeDepth treeDepth = new TreeDepth();
        int maxDepth = treeDepth.maxDepth(root);
        System.out.println(maxDepth);

        int minDepth = treeDepth.minDepth(root);
        System.out.println(minDepth);
    }
}
