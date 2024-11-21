package com.huahuadan.algorithm.binarytree;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/21 20:45
 * @description 二叉树最近公共祖先
 */
public class LeetCode236 {
    /**
     *      3
     *      / \
     *     5   1
     *    / \  / \
     *   6  2 0   8
     *     / \
     *    7   4
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 基本情况
        if (root == null || root == p || root == q) {
            return root;
        }

        // 在左子树中查找
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 在右子树中查找
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 如果左右子树都找到了，说明当前节点是最近公共祖先
        if (left != null && right != null) {
            return root;
        }

        // 否则返回非空的那一侧
        return left != null ? left : right;
    }

}
