package com.huahuadan.algorithm.binarytree;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/18 20:47
 * @description 翻转二叉树
 */
public class LeetCode226 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 交换当前节点的左右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // 递归反转左右子树
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
