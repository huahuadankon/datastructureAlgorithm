package com.huahuadan.algorithm.binarytree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/17 22:00
 * @description 判断一个二叉树是否对称
 */
public class LeetCode101 {
    //方法一 递归
    public static boolean isSymmetric(TreeNode root) {
        return check(root.left,  root.right);
    }
    public static boolean check(TreeNode left, TreeNode right){
        if(left==null && right==null){
            return true;
        }
        if(left == null || right == null || left.val!=right.val){
            return false;
        }

        return check(left.left,right.right)&&check(left.right,right.left);
    }

    //方法二 ，镜像队列判断
    public static boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 使用队列来模拟镜像检查
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();

            // 如果两个节点均为空，继续检查下一对节点
            if (node1 == null && node2 == null) {
                continue;
            }

            // 如果一个为空，另一个不为空，或者值不同，返回false
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }

            // 按镜像顺序加入左右子树
            queue.offer(node1.left);  // 左子树的左节点
            queue.offer(node2.right); // 右子树的右节点
            queue.offer(node1.right); // 左子树的右节点
            queue.offer(node2.left);  // 右子树的左节点
        }

        return true;
    }

    // TreeNode定义
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 示例主函数
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        System.out.println(isSymmetric(root)); // 输出: true
    }

}
