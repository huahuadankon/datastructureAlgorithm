package com.huahuadan.algorithm.binarysearchtree;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/20 20:23
 * @description 删除二叉搜索树的一个节点
 */
public class LeetCode450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null; // 树为空，或者没找到节点
        }
        if (key < root.val) {
            // 目标值小于当前节点，递归到左子树
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            // 目标值大于当前节点，递归到右子树
            root.right = deleteNode(root.right, key);
        } else {
            // 找到目标节点
            if (root.left == null) {
                // 左子树为空，用右子树替代
                return root.right;
            } else if (root.right == null) {
                // 右子树为空，用左子树替代
                return root.left;
            }
            // 左右子树都存在
            TreeNode successor = findMin(root.right); // 找到后继节点
            root.val = successor.val; // 替换当前节点值为后继节点值
            root.right = deleteNode(root.right, successor.val); // 删除后继节点
        }
        return root;
    }

    public TreeNode findMin(TreeNode root) {
        while (root != null && root.left != null) {
            root = root.left; // 迭代寻找最左节点
        }
        return root;
    }

}
