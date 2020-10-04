package base.tree;

import util.PrintUtil;

/**
 * @Author: Jeremy
 * @Date: 2020/9/22 10:42
 */
public class TreeMonitor {
    public static void main(String[] args) {
        TreeMonitor treeMonitor = new TreeMonitor();

        String treeStr1 = "0,0,null,0,0";
        TreeNode root1 = TreeNodeSerialize.deserialize(treeStr1);
        int min1 = treeMonitor.minCameraCover(root1);
        PrintUtil.print(min1);

        String treeStr2 = "0,0,null,0,null,0,null,null,0";
        TreeNode root2 = TreeNodeSerialize.deserialize(treeStr2);
        int min2 = treeMonitor.minCameraCover(root2);
        PrintUtil.print(min2);
    }

    /**
     * LeetCode 968. 监控二叉树
     * 给定一个二叉树，我们在树的节点上安装摄像头。
     * <p>
     * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
     * <p>
     * 计算监控树的所有节点所需的最小摄像头数量。
     * <p>
     * 示例 1：
     * <p>
     * 输入：[0,0,null,0,0]
     * 输出：1
     * 解释：如图所示，一台摄像头足以监控所有节点。
     * 示例 2：
     * <p>
     * 输入：[0,0,null,0,null,0,null,null,0]
     * 输出：2
     * 解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
     *
     * @param root
     * @return
     */
    public int minCameraCover(TreeNode root) {
        int[] array = dfs(root);
        return array[1];
    }

    public int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{Integer.MAX_VALUE / 2, 0, 0};
        }
        int[] leftArray = dfs(root.left);
        int[] rightArray = dfs(root.right);
        int[] array = new int[3];
        // root 必须放置摄像头的情况下，覆盖整棵树需要的摄像头数目。
        array[0] = leftArray[2] + rightArray[2] + 1;
        // 覆盖整棵树需要的摄像头数目，无论 root 是否放置摄像头。
        array[1] = Math.min(array[0], Math.min(leftArray[0] + rightArray[1], rightArray[0] + leftArray[1]));
        // 覆盖两棵子树需要的摄像头数目，无论节点 root 本身是否被监控到。
        array[2] = Math.min(array[0], leftArray[1] + rightArray[1]);
        return array;
    }
}
