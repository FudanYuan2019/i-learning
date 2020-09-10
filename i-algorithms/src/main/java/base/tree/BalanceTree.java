package base.tree;

/**
 * LeetCode 110. 平衡二叉树
 * <p>
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * <p>
 * 一个二叉树每个节点的左右两个子树的高度差的绝对值不超过1。
 * <p>
 * 示例 1:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]
 * <p>
 *   3
 *  / \
 * 9  20
 *   /  \
 *  15   7
 * 返回 true 。
 * <p>
 * 示例 2:
 * <p>
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * <p>
 *     1
 *    / \
 *   2   2
 *  / \
 * 3   3
 *    / \
 *   4   4
 * 返回 false 。
 * <p>
 *
 * @Author: Jeremy
 * @Date: 2020/9/10 16:57
 */
public class BalanceTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        root.right = node1;
        node1.right = node2;
        node2.right = node3;

        BalanceTree balanceTree = new BalanceTree();
        boolean balanced = balanceTree.isBalanced(root);
        System.out.println(balanced);
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(getHeight(root.left) - getHeight(root.right)) <= 1
                && isBalanced(root.left)
                && isBalanced(root.right);
    }

    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
}
