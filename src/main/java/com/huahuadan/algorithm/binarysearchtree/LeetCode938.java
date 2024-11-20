package com.huahuadan.algorithm.binarysearchtree;

import java.util.LinkedList;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/20 21:56
 * @description 求解范围值
 */
public class LeetCode938 {
    // 解法2. 上下限递归 0ms
    public int rangeSumBST(TreeNode node, int low, int high) {
        if (node == null) {
            return 0;
        }
        if (node.val < low) {
            return rangeSumBST(node.right, low, high);
        }
        if (node.val > high) {
            return rangeSumBST(node.left, low, high);
        }
        // 在范围内
        return node.val + rangeSumBST(node.left, low, high) + rangeSumBST(node.right, low, high);
    }

    // 解法1. 中序遍历非递归实现 4ms
    public int rangeSumBST1(TreeNode node, int low, int high) {
        TreeNode p = node;
        LinkedList<TreeNode> stack = new LinkedList<>();
        int sum = 0;
        while(p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode pop = stack.pop();
                // 处理值
                if (pop.val > high) {
                    break;
                }
                if (pop.val >= low) {
                    sum += pop.val;
                }
                p = pop.right;
            }
        }
        return sum;
    }

}
