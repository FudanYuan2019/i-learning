package base.tree;

/**
 * @Author: Jeremy
 * @Date: 2020/9/6 14:56
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode next;

    public TreeNode() {

    }

    public TreeNode(Integer val) {
        this.val = val;
        this.left = null;
        this.right = null;
        this.next = null;
    }

}
