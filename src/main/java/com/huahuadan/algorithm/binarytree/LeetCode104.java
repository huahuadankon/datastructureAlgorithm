package com.huahuadan.algorithm.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/18 20:07
 * @description 求解二叉树最大深度
 */
public class LeetCode104 {
    //方法一 递归
    /*
        思路：
        1. 得到左子树深度, 得到右子树深度, 二者最大者加一, 就是本节点深度
        2. 因为需要先得到左右子树深度, 很显然是后序遍历典型应用
        3. 关于深度的定义：从根(也可以是某节点)出发, 离根最远的节点总边数,
            注意: 力扣里的深度定义要多一

            深度2         深度3        深度1
            1            1            1
           / \          / \
          2   3        2   3
                            \
                             4
     */
    public int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        /*if (node.left == null && node.right == null) {
            return 1;
        }*/
        int d1 = maxDepth(node.left);
        int d2 = maxDepth(node.right);
        return Integer.max(d1, d2) + 1;
    }

    //方法二，后序遍历算栈深度
    public int maxDepth2(TreeNode root) {
        TreeNode curr = root;
        TreeNode pop = null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        int max = 0; // 栈的最大高度
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                int size = stack.size();
                if (size > max) {
                    max = size;
                }
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {
                    pop = stack.pop();
                } else {
                    curr = peek.right;
                }
            }
        }
        return max;
    }
    //方法三 层序遍历，每遍历完一层，深度加一
    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
//                System.out.print(poll.val + "\t");
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
//            System.out.println();
            depth ++;
        }
        return depth;
    }



    }


