package base.tree;

import util.PrintUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Jeremy
 * @Date: 2020/9/10 19:10
 */
public class TreeBuilder {
    public static void main(String[] args) {
        int[] preOrder = new int[]{3, 9, 20, 15, 7};
        int[] inOrder = new int[]{9, 3, 15, 20, 7};
        int[] postOrder = new int[]{9, 15, 7, 20, 3};

        TreeBuilder treeBuilder = new TreeBuilder();
        TreeNode root = treeBuilder.buildTreeFromPreAndInOrder(preOrder, inOrder);

        TreeTraverse treeTraverse = new TreeTraverse();
        List<List<Integer>> res = treeTraverse.levelOrder(root);
        for (List<Integer> subList : res) {
            PrintUtil.print(subList);
        }

        root = treeBuilder.buildTreeFromInAndPostOrder(inOrder, postOrder);
        res = treeTraverse.levelOrder(root);
        for (List<Integer> subList : res) {
            PrintUtil.print(subList);
        }

        root = treeBuilder.mirrorTree(root);
        PrintUtil.print(root);
    }

    /**
     * 根据一棵树的前序遍历与中序遍历构造二叉树。
     * <p>
     * 注意:
     * 你可以假设树中没有重复的元素。
     * <p>
     * 例如，给出
     * <p>
     * 前序遍历 preOrder = [3,9,20,15,7]
     * 中序遍历 inOrder = [9,3,15,20,7]
     * 返回如下的二叉树：
     * <p>
     *   3
     *  / \
     * 9  20
     *   /  \
     *  15   7
     *
     * @param preOrder
     * @param inOrder
     * @return
     */
    public TreeNode buildTreeFromPreAndInOrder(int[] preOrder, int[] inOrder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }
        return buildTreeHelperFromPreAndInOrder(preOrder, 0, preOrder.length, inOrder, 0, inOrder.length, map);
    }

    private TreeNode buildTreeHelperFromPreAndInOrder(int[] preOrder, int p_start, int p_end,
                                                      int[] inOrder, int i_start, int i_end, Map<Integer, Integer> map) {
        if (p_start == p_end) {
            return null;
        }
        int root_val = preOrder[p_start];
        TreeNode root = new TreeNode(root_val);
        int i_root_index = map.get(root_val);
        int leftNum = i_root_index - i_start;
        root.left = buildTreeHelperFromPreAndInOrder(preOrder, p_start + 1, p_start + leftNum + 1,
                inOrder, i_start, i_root_index, map);
        root.right = buildTreeHelperFromPreAndInOrder(preOrder, p_start + leftNum + 1, p_end,
                inOrder, i_root_index + 1, i_end, map);
        return root;
    }


    /**
     * LeetCode 106. 从中序与后序遍历序列构造二叉树
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     * <p>
     * 注意:
     * 你可以假设树中没有重复的元素。
     * <p>
     * 例如，给出
     * <p>
     * 中序遍历 inorder = [9,3,15,20,7]
     * 后序遍历 postorder = [9,15,7,20,3]
     * 返回如下的二叉树：
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * @param inOrder
     * @param postOrder
     * @return
     */
    public TreeNode buildTreeFromInAndPostOrder(int[] inOrder, int[] postOrder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }

        return buildTreeHelperFromInAndPostOrder(postOrder, 0, postOrder.length - 1,
                inOrder, 0, inOrder.length - 1, map);
    }

    private TreeNode buildTreeHelperFromInAndPostOrder(int[] postOrder, int p_start, int p_end,
                                                       int[] inOrder, int i_start, int i_end,
                                                       Map<Integer,Integer> map) {
        if (p_start > p_end) {
            return null;
        }

        int root_val = postOrder[p_end];
        TreeNode root = new TreeNode(root_val);
        int i_root_index = map.get(root_val);
        int leftNum = i_root_index - i_start;
        int rightNum = i_end - i_root_index;

        root.left = buildTreeHelperFromInAndPostOrder(postOrder, p_start, p_start + leftNum - 1,
                inOrder, i_start, i_root_index - 1, map);
        root.right = buildTreeHelperFromInAndPostOrder(postOrder, p_start + leftNum, p_start + leftNum + rightNum - 1,
                inOrder, i_root_index + 1, i_end, map);
        return root;
    }

    /**
     * LeetCode 226. 翻转二叉树
     * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
     * <p>
     * 例如输入：
     * <p>
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * 镜像输出：
     * <p>
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     * <p>
     * 示例 1：
     * <p>
     * 输入：root = [4,2,7,1,3,6,9]
     * 输出：[4,7,2,9,6,3,1]
     * <p>
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);
        return root;
    }

    /**
     * LeetCode 617. 合并二叉树
     * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
     * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，
     * 那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
     *
     * 示例 1:
     *
     * 输入:
     * 	Tree 1                     Tree 2
     *           1                         2
     *          / \                       / \
     *         3   2                     1   3
     *        /                           \   \
     *       5                             4   7
     * 输出:
     * 合并后的树:
     * 	     3
     * 	    / \
     * 	   4   5
     * 	  / \   \
     * 	 5   4   7
     * 注意: 合并必须从两个树的根节点开始。
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        if (t2 == null)
            return t1;
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }
}
