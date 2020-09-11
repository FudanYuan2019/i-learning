package base.tree;

import util.PrintUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Jeremy
 * @Date: 2020/9/10 19:10
 */
public class BuildTree {
    public static void main(String[] args) {
        int[] preOrder = new int[]{3, 9, 20, 15, 7};
        int[] inOrder = new int[]{9, 3, 15, 20, 7};
        int[] postOrder = new int[]{9, 15, 7, 20, 3};

        BuildTree buildTree = new BuildTree();
        TreeNode root = buildTree.buildTreeFromPreAndInOrder(preOrder, inOrder);

        TreeTraverse treeTraverse = new TreeTraverse();
        List<List<Integer>> res = treeTraverse.levelOrder(root);
        for (List<Integer> subList : res) {
            PrintUtil.print(subList);
        }

        root = buildTree.buildTreeFromInAndPostOrder(inOrder, postOrder);
        res = treeTraverse.levelOrder(root);
        for (List<Integer> subList : res) {
            PrintUtil.print(subList);
        }
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
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
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
}
