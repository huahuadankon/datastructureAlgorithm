package com.huahuadan.algorithm.binarytree;

import java.util.Arrays;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/18 21:23
 * @description 根据中后序结果构建二叉树
 */
public class LeetCode106 {
    /*
         inOrder = {4,2,1,6,3,7}
         postOrder = {4,2,6,7,3,1}

         根 1
            in        post
         左 4,2       4,2
         右 6,3,7     6,7,3
      */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 边界条件：如果数组为空，直接返回 null
        if (inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0) {
            return null;
        }

        // 后序遍历的最后一个元素是根节点
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);

        // 在中序遍历中找到根节点的位置
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) {
                // 划分中序遍历的左右子树
                int[] inorderLeft = Arrays.copyOfRange(inorder, 0, i);
                int[] inorderRight = Arrays.copyOfRange(inorder, i + 1, inorder.length);

                // 划分后序遍历的左右子树
                int[] postorderLeft = Arrays.copyOfRange(postorder, 0, i);
                int[] postorderRight = Arrays.copyOfRange(postorder, i, postorder.length - 1);

                // 递归构建左右子树
                root.left = buildTree(inorderLeft, postorderLeft);
                root.right = buildTree(inorderRight, postorderRight);
                break;
            }
        }

        return root;
    }


}
