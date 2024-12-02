package com.huahuadan.algorithm.binarytree;

import java.util.Arrays;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/18 21:23
 * @description 根据前中序结果构建二叉树
 */
public class LeetCode105 {
    public static void main(String[] args) {
        int[] preOrder = {1, 2, 4, 3, 6, 7};
        int[] inOrder = {4, 2, 1, 6, 3, 7};
        TreeNode root = new LeetCode105().buildTree(preOrder, inOrder);
        System.out.println(root);
    }
    /*
        preOrder = {1,2,4,3,6,7}
        inOrder = {4,2,1,6,3,7}

        根 1
            pre         in
        左  2,4         4,2
        右  3,6,7       6,3,7


        根 2
        左 4

        根 3
        左 6
        右 7
     */

    public TreeNode buildTree(int[] preOrder, int[] inOrder) {
        if (preOrder == null || preOrder.length == 0 || inOrder == null || inOrder.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(preOrder[0]);
        for (int i = 0; i < inOrder.length; i++) {
            if(inOrder[i] == root.val){ // 找到根节点
                //拆分子树
                int[] inOrderLeft = Arrays.copyOfRange(inOrder, 0, i);
                int[] inOrderRight = Arrays.copyOfRange(inOrder, i+1, inOrder.length);
                int[] preOrderLeft = Arrays.copyOfRange(preOrder, 1, i+1);
                int[] preOrderRight = Arrays.copyOfRange(preOrder, i+1, preOrder.length);

                //递归求解
                root.left = buildTree(preOrderLeft, inOrderLeft);
                root.right = buildTree(preOrderRight, inOrderRight);
                break;
            }
        }
        return root;


    }

}
