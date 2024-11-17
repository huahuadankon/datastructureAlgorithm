package com.huahuadan.datastructure.binarytree;

import com.huahuadan.datastructure.stack.LinkedListStack;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/17 20:24
 * @description 非递归实现后序遍历
 */
public class PostorderTraversal {
    public static void postorderTraversal(TreeNode root) {
        LinkedListStack<TreeNode> stack = new LinkedListStack<>(10); // 自定义栈
        TreeNode current = root; // 当前节点
        TreeNode lastVisited = null; // 记录上一次访问的节点

        while (current != null || !stack.isEmpty()) {
            // 不断深入左子树
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            // 查看栈顶节点
            TreeNode peekNode = stack.peek();
            // 如果右子树不存在或已被访问
            if (peekNode.right == null || peekNode.right == lastVisited) {
                // 访问当前节点
                colorPrintln(peekNode.val, 32);
                // 弹出栈顶节点，标记为已访问
                lastVisited = stack.pop();
            } else {
                // 转向右子树
                current = peekNode.right;
            }
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2, null),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6))
        );
        postorderTraversal(root);
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
