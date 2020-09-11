package base.tree;


import util.PrintUtil;

import java.util.*;

/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，
 * 进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。
 * 这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串
 * 并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 示例: 
 * <p>
 * 你可以将以下二叉树：
 * <p>
 *   1
 *  / \
 * 2   3
 *    / \
 *   4   5
 * <p>
 * 序列化为 "[1,2,3,null,null,4,5]"
 *
 * @Author: Jeremy
 * @Date: 2020/9/11 19:54
 */
public class TreeNodeSerialize {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(1);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;
        node3.left = node5;
        node3.right = node6;

        TreeNodeSerialize treeNodeSerialize = new TreeNodeSerialize();
        String serialize = treeNodeSerialize.serialize(root);
        PrintUtil.print(serialize);

        TreeNode deserialize = treeNodeSerialize.deserialize(serialize);
        PrintUtil.print(deserialize);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "null";
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        StringBuilder stringBuilder = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode top = queue.poll();
            if (top == null) {
                stringBuilder.append("null,");
                continue;
            }
            stringBuilder.append(top.val).append(",");
            queue.offer(top.left);
            queue.offer(top.right);
        }
        return stringBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("null".equals(data)) {
            return null;
        }
        String[] nodes = data.split(",");
        TreeNode root = getTreeNodeFromString(nodes[0]);
        int cursor = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(cursor < nodes.length) {
            TreeNode node = queue.poll();
            String leftStr = nodes[cursor];
            String rightStr = nodes[cursor + 1];
            if (!"null".equals(leftStr)) {
                TreeNode left = getTreeNodeFromString(leftStr);
                node.left = left;
                queue.offer(left);
            }
            if (!"null".equals(rightStr)) {
                TreeNode right = getTreeNodeFromString(rightStr);
                node.right = right;
                queue.offer(right);
            }
            cursor += 2;
        }
        return root;
    }

    private TreeNode getTreeNodeFromString(String nodeStr) {
        if ("null".equals(nodeStr)) {
            return null;
        }
        return new TreeNode(Integer.valueOf(nodeStr));
    }
}
