package com.huahuadan.datastructure.binarytree;

import com.huahuadan.datastructure.stack.LinkedListStack;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/17 20:23
 * @description 非递归实现中序遍历
 */
public class InorderTraversal {
    public static void preorderTraversal(TreeNode root) {
        LinkedListStack<TreeNode> stack = new LinkedListStack<>(10);
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            if(current != null) { //当前存在节点
                stack.push(current);
                current = current.left;
            }
            else {
                current = stack.pop(); //当前节点不存在左子树，开始弹栈，打印节点，找右子树
                colorPrintln(current.val,31);
                current = current.right;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2, null),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6))
        );
        preorderTraversal(root);


    }

    /*
       31 红
       32 黄
       33 橙
       34 蓝
       35 紫
       36 绿
    */
    public static void colorPrintln(int origin, int color) {
        System.out.printf("\033[%dm%s\033[0m%n", color, origin);
    }

}
