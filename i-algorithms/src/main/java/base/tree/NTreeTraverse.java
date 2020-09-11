package base.tree;

import util.PrintUtil;

import java.util.*;

/**
 * @Author: Jeremy
 * @Date: 2020/9/11 15:14
 */
public class NTreeTraverse {
    public static void main(String[] args) {
        NTreeNode node1 = new NTreeNode(1);
        NTreeNode node2 = new NTreeNode(2);
        NTreeNode node3 = new NTreeNode(3);
        NTreeNode node4 = new NTreeNode(4);
        List<NTreeNode> children = new ArrayList<>();
        children.add(node1);
        children.add(node2);
        children.add(node3);
        children.add(node4);

        NTreeNode root = new NTreeNode(0, children);
        NTreeTraverse nTreeTraverse = new NTreeTraverse();
        List<Integer> preOrder = nTreeTraverse.preOrderTraversal(root);
        PrintUtil.print(preOrder);

        List<Integer> postOrder = nTreeTraverse.postOrderTraversal(root);
        PrintUtil.print(postOrder);

        List<List<Integer>> inOrder = nTreeTraverse.levelOrder(root);
        for (List<Integer> subList : inOrder) {
            PrintUtil.print(subList);
        }
    }
    /**
     * LeetCode 589. N叉树的前序遍历
     * <p>
     * 给定一个 N 叉树，返回其节点值的前序遍历。
     * <p>
     * @param root
     * @return
     */
    public List<Integer> preOrderTraversal(NTreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) {
            return res;
        }

        Stack<NTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            NTreeNode top = stack.pop();
            res.add(top.val);
            if (top.children != null) {
                for (int i = top.children.size() - 1; i >= 0; i--) {
                    stack.push(top.children.get(i));
                }
            }
        }
        return res;
    }

    /**
     * LeetCode 590. N叉树的后序遍历
     *
     * 给定一个 N 叉树，返回其节点值的后序遍历。
     * @param root
     * @return
     */
    public List<Integer> postOrderTraversal(NTreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) {
            return res;
        }

        Stack<NTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            NTreeNode top = stack.pop();
            res.add(0, top.val);
            if (top.children != null) {
                for (int i = 0; i < top.children.size(); i++) {
                    stack.push(top.children.get(i));
                }
            }
        }
        return res;
    }

    /**
     * LeetCode 429. N叉树的层序遍历
     * <p>
     * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(NTreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        res.add(Collections.singletonList(root.val));

        Queue<NTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        NTreeNode last = root;
        NTreeNode newLast = null;

        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            NTreeNode top = queue.poll();
            if (top.children != null) {
                for (NTreeNode node : top.children) {
                    queue.offer(node);
                    list.add(node.val);
                    newLast = node;
                }
                if (last == top) {
                    last = newLast;
                    if (list.size() > 0) {
                        res.add(new ArrayList<>(list));
                        list.clear();
                    }
                }
            }
        }
        return res;
    }
}
