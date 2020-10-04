package base.tree;

import util.PrintUtil;

/**
 * LCP 10. 二叉树任务调度(https://leetcode-cn.com/problems/er-cha-shu-ren-wu-diao-du/)
 * 任务调度优化是计算机性能优化的关键任务之一。
 * 在任务众多时，不同的调度策略可能会得到不同的总体执行时间，因此寻求一个最优的调度方案是非常有必要的。
 * 通常任务之间是存在依赖关系的，即对于某个任务，你需要先完成他的前导任务（如果非空），才能开始执行该任务。
 * 我们保证任务的依赖关系是一棵二叉树，其中 root 为根任务，root.left 和 root.right 为他的两个前导任务（可能为空），
 * root.val 为其自身的执行时间。
 * <p>
 * 在一个 CPU 核执行某个任务时，我们可以在任何时刻暂停当前任务的执行，并保留当前执行进度。
 * 在下次继续执行该任务时，会从之前停留的进度开始继续执行。暂停的时间可以不是整数。
 * 现在，系统有两个 CPU 核，即我们可以同时执行两个任务，但是同一个任务不能同时在两个核上执行。
 * 给定这颗任务树，请求出所有任务执行完毕的最小时间。
 *
 * @Author: Jeremy
 * @Date: 2020/9/12 10:59
 */
public class MinimalExecTime {
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

        MinimalExecTime minimalExecTime = new MinimalExecTime();
        double time = minimalExecTime.minimalExecTime(root);
        PrintUtil.print(time);
    }

    /**
     * @param root
     * @return
     */
    public double minimalExecTime(TreeNode root) {
        double[] res = execTime(root, 2);
        return res[0];
    }

    /**
     * 获取node最小执行时间
     *
     * @param node node
     * @param n    并行数
     * @return [0]执行完当前节点最小耗时，[1]当前node为根的时间串行之和
     */
    private double[] execTime(TreeNode node, int n) {
        if (node == null) {
            // [0]执行完当前节点最小耗时，[1]当前node为根的时间串行之和
            return new double[]{0.0D, 0.0D};
        }
        // 获取左右子树的值
        double[] leftTime = execTime(node.left, n);
        double[] rightTime = execTime(node.right, n);
        // 左右子树节点之和
        double sum = leftTime[1] + rightTime[1];
        // 当前节点执行完的最小消耗时间
        double minTime = Math.max(Math.max(leftTime[0], rightTime[0]), sum / n) + node.val;
        return new double[]{minTime, sum + node.val};
    }
}
