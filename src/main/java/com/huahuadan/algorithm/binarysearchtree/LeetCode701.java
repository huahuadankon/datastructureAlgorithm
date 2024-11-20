package com.huahuadan.algorithm.binarysearchtree;

/**
 * 新增节点 (题目前提：val 一定与树中节点不同) 新增一个节点
 */
public class LeetCode701 {

    /*
            val=1

            5
           / \
          2   6
           \
            3
     */
    public TreeNode insertIntoBST(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        }
        if (val < node.val) {
            node.left = insertIntoBST(node.left, val);
        } else if (node.val < val) {
            node.right = insertIntoBST(node.right, val);
        }
        return node;
    }

}
