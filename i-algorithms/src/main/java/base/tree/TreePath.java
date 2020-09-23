package base.tree;

import util.PrintUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树求路径和，LeetCode 129
 *
 * @Author: Jeremy
 * @Date: 2020/9/10 14:38
 */
public class TreePath {
    public static void main(String[] args) {
        String treeStr = "5,4,8,11,null,13,4,7,2,null,null,5,1";
        TreeNode root = TreeNodeSerialize.deserialize(treeStr);

        TreePath treePath = new TreePath();
        int sum = treePath.sumNumbers(root);
        PrintUtil.print(sum);

        List<String> paths = treePath.binaryTreePaths(root);
        PrintUtil.print(paths);

        int target = 22;
        boolean exist = treePath.hasPathSum(root, target);
        PrintUtil.print(exist);

        List<List<Integer>> res = treePath.pathSum(root, target);
        for (List<Integer> subList : res) {
            PrintUtil.print(subList);
        }

        treeStr = "10,5,-3,3,2,null,11,3,-2,null,1";
        root = TreeNodeSerialize.deserialize(treeStr);
        int num = treePath.pathSumNum(root, 8);
        PrintUtil.print(num);

        int leftLeafSum = treePath.sumOfLeftLeaves(root);
        PrintUtil.print(leftLeafSum);
    }

    /**
     * LeetCode 129. 求根到叶子节点数字之和
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

    /**
     * LeetCode 257. 二叉树的所有路径
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

    private void traverse(TreeNode root, String path, List<String> paths) {
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

    /**
     * LeetCode 112. 路径总和
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，
     * 这条路径上所有节点值相加等于目标和。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例: 
     * 给定如下二叉树，以及目标和 sum = 22，
     * <p>
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \      \
     *         7    2      1
     * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /**
     * LeetCode 113. 路径总和 II
     * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
     * 从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
     * <p>
     * 示例:
     * 给定如下二叉树，以及目标和 sum = 22，
     *
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \    / \
     *         7    2  5   1
     * 返回:
     * <p>
     * [
     *    [5,4,11,2],
     *    [5,8,4,5]
     * ]
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        List<Integer> path = new ArrayList<>();
        dfs(root, sum, path, res);
        return res;
    }


    private void dfs(TreeNode root, int sum, List<Integer> path, List<List<Integer>> res) {
        if (root == null) {
            return;
        }

        path.add(root.val);
        if (root.left == null && root.right == null && sum == root.val) {
            res.add(new ArrayList<>(path));
        }

        dfs(root.left, sum - root.val, path, res);
        dfs(root.right, sum - root.val, path, res);
        path.remove(path.size() - 1);
    }

    /**
     * LeetCode 437. 路径总和 III
     * 给定一个二叉树，它的每个结点都存放着一个整数值。
     * 找出路径和等于给定数值的路径总数。
     * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
     * <p>
     * 示例：
     * <p>
     * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
     *
     *       10
     *      /  \
     *     5   -3
     *    / \    \
     *   3   2   11
     *  / \   \
     * 3  -2   1
     * <p>
     * 返回 3。和等于 8 的路径有:
     * <p>
     * 1.  5 -> 3
     * 2.  5 -> 2 -> 1
     * 3.  -3 -> 11
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSumNum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        int res = dfs(root, sum);
        res += pathSumNum(root.left, sum);
        res += pathSumNum(root.right, sum);
        return res;
    }

    private int dfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        sum -= root.val;
        int res = (sum == 0) ? 1 : 0;
        res += dfs(root.left, sum);
        res += dfs(root.right, sum);
        return res;
    }

    /**
     * LeetCode 404. 左叶子之和
     * 计算给定二叉树的所有左叶子之和。
     *
     * 示例：
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
     *
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        int res = 0;
        if(root == null){
            return res;
        }
        if(root.left != null && isLeafNode(root.left)){
            res += root.left.val;
        }
        res += sumOfLeftLeaves(root.left);
        res += sumOfLeftLeaves(root.right);
        return res;
    }

    private boolean isLeafNode(TreeNode root) {
        if (root == null) {
            return true;
        }
        return root.left == null && root.right == null;
    }
}
