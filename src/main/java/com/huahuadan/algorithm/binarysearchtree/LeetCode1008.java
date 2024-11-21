package com.huahuadan.algorithm.binarysearchtree;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/21 19:27
 * @description 根据前序遍历创建二叉搜索树
 */
public class LeetCode1008 {
    //方法一，遍历数组，依次插入节点构建整棵树
    public TreeNode bstFromPreorder1(int[] preorder) {
        TreeNode root = insert(null, preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            insert(root, preorder[i]);
        }
        return root;
    }

    private TreeNode insert(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        }
        if(val < node.val) {
            node.left = insert(node.left, val);
        } else if(node.val < val){
            node.right = insert(node.right, val);
        }
        return node;
    }
    //方法二，上下限
    public TreeNode bstFromPreorder2(int[] preorder) {
        return insert2(preorder,Integer.MAX_VALUE);
    }
    int i =0; //数组下标
    public TreeNode insert2(int[] preorder, int max) {
        if(i == preorder.length){
            return null;
        }
        if(preorder[i] > max){
            return null;
        }
        TreeNode root = new TreeNode(preorder[i]);
        i++;
        root.left = insert2(preorder,root.val);
        root.right = insert2(preorder,max);
        return root;
    }
    //方法三，分治法
    public TreeNode bstFromPreorder3(int[] preorder) {
        return partition(preorder,0,preorder.length-1);
    }
    public TreeNode partition(int[] preorder,int start,int end) {
        if(start > end){
            return null;
        }
        TreeNode root = new TreeNode(preorder[start]);
        int index = start+1;
        while(index < end){
            if(preorder[index] > preorder[start]){
                break;
            }
            index++;
        }
        root.left = partition(preorder,start+1,index-1);
        root.right = partition(preorder,index,end);
        return root;
    }

}
