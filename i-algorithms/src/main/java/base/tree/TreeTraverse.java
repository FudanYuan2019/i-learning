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
        treeTraverse.preorderTraversalRecursive(root);

        treeTraverse.inorderTraversalRecursive(root);

        treeTraverse.postorderTraversalRecursive(root);

        int[] levelOrderArray = treeTraverse.levelOrderI(root);
        PrintUtil.print(levelOrderArray);

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

        TreeNode root1 = TreeNodeSerialize.deserialize("1,2,3,4,null,null,null");
        List<Integer> rightView = treeTraverse.rightSideView(root1);
        PrintUtil.print(rightView);

        boolean symmetric = treeTraverse.isSymmetric(root);
        PrintUtil.print(symmetric);

        String bstStr = "3,1,4,null,2";
        root = TreeNodeSerialize.deserialize(bstStr);
        int k = 1;
        int kthVal = treeTraverse.kthLargest(root, k);
        PrintUtil.print(kthVal);

        int[] bstPostOrder = new int[]{1, 6, 3, 2, 5};
        boolean isBst = treeTraverse.verifyPostorder(bstPostOrder);
        PrintUtil.print(isBst);

        bstStr = "4,2,5,1,3";
        root = TreeNodeSerialize.deserialize(bstStr);
        root = treeTraverse.treeToDoublyList(root);

        String treeStr = "1,2,3,4,5,null,7";
        root = TreeNodeSerialize.deserialize(treeStr);
        root = treeTraverse.connect(root);
        PrintUtil.print(TreeNodeSerialize.serialize(root));


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
     * 前序遍历的递归实现
     * 若二叉树为空，则空操作，否则先访问根节点，再先序遍历左子树，最后先序遍历右子树。
     * @param root
     * @return
     */
    public void preorderTraversalRecursive(TreeNode root) {
        if (root == null) {
            return;
        }

        // 先访问根结点
        System.out.println(root.val);
        // 先序访问左子树
        preorderTraversalRecursive(root.left);
        // 再先序访问右子树
        preorderTraversalRecursive(root.right);
    }

    /**
     * leetCode 94 二叉树中序遍历
     * 给定一个二叉树，返回它的 中序 遍历。
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
     * 中序遍历的递归实现
     * 若二叉树为空，则空操作，否则先中序遍历左子树，再访问根节点，最后中序遍历右子树。
     * @param root
     * @return
     */
    public void inorderTraversalRecursive(TreeNode root) {
        if (root == null) {
            return;
        }

        // 中序遍历左子树
        inorderTraversalRecursive(root.left);
        // 访问根节点
        System.out.println(root.val);
        // 中序遍历右子树
        inorderTraversalRecursive(root.right);
    }

    /**
     * LeetCode 145. 二叉树的后序遍历
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
     * 后序遍历的递归实现
     * 若二叉树为空，则空操作；否则先后序遍历左子树，再后序遍历右子树，最后访问根结点。
     * @param root
     * @return
     */
    public void postorderTraversalRecursive(TreeNode root) {
        if (root == null) {
            return;
        }

        // 后序遍历左子树
        postorderTraversalRecursive(root.left);
        // 后序遍历右子树
        postorderTraversalRecursive(root.right);
        // 访问根节点
        System.out.println(root.val);
    }

    /**
     * 剑指 Offer 32 - I. 从上到下打印二叉树
     * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
     *
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回：
     *
     * [3,9,20,15,7]
     *
     * @param root
     * @return
     */
    public int[] levelOrderI(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }

        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode top = queue.poll();
            list.add(top.val);
            if (top.left != null) {
                queue.offer(top.left);
            }
            if (top.right != null) {
                queue.offer(top.right);
            }
        }
        int[] res = new int[list.size()];
        int i = 0;
        for (Integer val : list) {
            res[i++] = val;
        }
        return res;
    }

    /**
     * LeetCode 102 二叉树的层序遍历
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。
     * （即逐层地，从左到右访问所有节点）。
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
     * LeetCode 107. 二叉树的层次遍历 II
     * 给定一个二叉树，返回其节点值自底向上的层次遍历。
     * （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
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

        res.add((double) root.val);

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
     * LeetCode 103. 二叉树的锯齿形层次遍历
     * 给定一个二叉树，返回其节点值的锯齿形层次遍历。
     * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
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

    /**
     * 199. 二叉树的右视图
     * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     *
     * 示例:
     *
     * 输入: [1,2,3,null,5,null,4]
     * 输出: [1, 3, 4]
     * 解释:
     *
     *    1            <---
     *  /   \
     * 2     3         <---
     *  \     \
     *   5     4       <---
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) {
            return res;
        }

        TreeNode pLast = root;
        TreeNode last = null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode top = queue.poll();
            if(top.left != null) {
                last = top.left;
                queue.offer(top.left);
            }
            if (top.right != null) {
                last = top.right;
                queue.offer(top.right);
            }

            if(top == pLast) {
                res.add(pLast.val);
                pLast = last;
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
     * 剑指 Offer 54. 二叉搜索树的第k大节点
     * <p>
     * 给定一棵二叉搜索树，请找出其中第k大的节点。
     * <p>
     * 示例 1:
     * <p>
     * 输入: root = [3,1,4,null,2], k = 1
     *    3
     *   / \
     *  1   4
     *   \
     *    2
     * 输出: 4
     * 示例 2:
     * <p>
     * 输入: root = [5,3,6,2,4,null,null,1], k = 3
     *        5
     *       / \
     *      3   6
     *     / \
     *    2   4
     *   /
     *  1
     * 输出: 4
     *
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {
        if(root == null) {
            return -1;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        int i = 0;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.right;
            }
            TreeNode top = stack.pop();
            i++;
            if (i == k) {
                return top.val;
            }
            node = top.left;
        }
        return -1;
    }

    /**
     * 剑指 Offer 33. 二叉搜索树的后序遍历序列
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。
     * 如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
     * <p>
     * 参考以下这颗二叉搜索树：
     * <p>
     *      5
     *     / \
     *    2   6
     *   / \
     *  1   3
     * 示例 1：
     * <p>
     * 输入: [1,6,3,2,5]
     * 输出: false
     * 示例 2：
     * <p>
     * 输入: [1,3,2,6,5]
     * 输出: true
     *
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > root) {
                return false;
            }
            while (!stack.isEmpty() && postorder[i] < stack.peek()) {
                root = stack.pop();
            }

            stack.push(postorder[i]);
        }
        return true;
    }

    /**
     * 剑指 Offer 36. 二叉搜索树与双向链表
     * 
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。
     * 要求不能创建任何新的节点，只能调整树中节点指针的指向。
     * 
     * @param root
     * @return
     */
    public TreeNode treeToDoublyList(TreeNode root) {
        if(root == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode last = null;
        TreeNode node = root;
        TreeNode head = null;
        while(!stack.isEmpty() || node != null){
            while(node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            if (last == null) {
                head = node;
            } else {
                last.right = node;
            }
            node.left = last;
            last = node;

            node = node.right;
        }

        last.right = head;
        head.left = last;
        return head;
    }


    /**
     * LeetCode 116. 填充每个节点的下一个右侧节点指针
     * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。
     *
     * 二叉树定义如下：
     *
     * struct Node {
     *   int val;
     *   Node *left;
     *   Node *right;
     *   Node *next;
     * }
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
     * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     *
     * 初始状态下，所有 next 指针都被设置为 NULL。
     * @param root
     * @return
     */
    public TreeNode connect(TreeNode root) {
        if (root == null) {
            return root;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode last = root;
        TreeNode newLast = null;
        List<TreeNode> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode top = queue.poll();
            if (top.left != null) {
                queue.offer(top.left);
                newLast = top.left;
                list.add(newLast);
            }
            if (top.right != null) {
                queue.offer(top.right);
                newLast = top.right;
                list.add(newLast);
            }
            if (last == top) {
                last = newLast;
                for (int i = 0; i < list.size() - 1; i++) {
                    list.get(i).next = list.get(i + 1);
                }
                list = new ArrayList<>();
            }
        }

        return root;
    }


    /**
     * LeetCode 117. 填充每个节点的下一个右侧节点指针 II
     *
     * 给定一个二叉树
     *
     * struct Node {
     *   int val;
     *   Node *left;
     *   Node *right;
     *   Node *next;
     * }
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
     * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     *
     * 初始状态下，所有 next 指针都被设置为 NULL。
     *
     * @param root
     * @return
     */
    public TreeNode connectII(TreeNode root) {
        if (root == null) {
            return root;
        }

        TreeNode cur = root;
        while (cur != null) {
            TreeNode dummyHead = new TreeNode();
            TreeNode prev = dummyHead;
            while (cur != null) {
                if (cur.left != null) {
                    prev.next = cur.left;
                    prev = cur.left;
                }
                if (cur.right != null) {
                    prev.next = cur.right;
                    prev = cur.right;
                }
                cur = cur.next;
            }
            cur = dummyHead.next;
        }

        return root;
    }
}
