package com.huahuadan.algorithm.binarytree;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/21 20:51
 * @description 二叉树转换成链表
 */
public class LeetCode114 {
        TreeNode pre = null; // 记录当前节点的前驱节点

    /**
     * 利用后序遍历，和一个指针不断操作整个树的结构
     * @param root
     */
    public void flatten(TreeNode root) {
            if (root == null) {
                return; // 如果当前节点为null，则直接返回
            }

            flatten(root.right); // 先处理右子树
            flatten(root.left);  // 然后处理左子树

            root.right = pre;    // 将当前节点的右指针指向前一个已经展平的节点
            root.left = null;    // 将当前节点的左指针设为null

            pre = root;          // 更新pre为当前节点
        }
}
