package com.huahuadan.algorithm.binarysearchtree;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/17 19:38
 * @description 二叉树节点
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val) {
        this.val = val;
    }
    public TreeNode(TreeNode left, int val, TreeNode right) {
        this.left = left;
        this.val = val;
        this.right = right;
    }

    @Override
    public String toString() {
        return String.valueOf(this.val);
    }

}
