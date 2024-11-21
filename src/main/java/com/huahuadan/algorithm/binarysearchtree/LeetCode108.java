package com.huahuadan.algorithm.binarysearchtree;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/21 20:51
 * @description 将有序数组转换成平衡二叉搜索树
 */
public class LeetCode108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    // 递归方法，给定数组的起始和结束索引，构建平衡二叉搜索树
    private TreeNode buildTree(int[] nums, int left, int right) {
        if (left > right) {
            return null; // 基础条件，递归终止
        }

        // 选择中间元素
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]); // 创建根节点

        // 递归构建左子树和右子树
        root.left = buildTree(nums, left, mid - 1);  // 左子树
        root.right = buildTree(nums, mid + 1, right); // 右子树

        return root;
    }
}
