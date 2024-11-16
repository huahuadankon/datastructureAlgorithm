package com.huahuadan.algorithm.deque;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/11 19:40
 * @description 二叉树S字形遍历
 */
public class LeetCode103 {
    static class TreeNode {
        int val;
        LeetCode103.TreeNode left;
        LeetCode103.TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, LeetCode103.TreeNode left, LeetCode103.TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.valueOf(this.val);
            /*return "TreeNode [val=" + val + ", left=" + left + ", right=" + right + "]";*/
        }
    }
    public static void main(String[] args) {
    LeetCode103.TreeNode root = new LeetCode103.TreeNode(1,
            new LeetCode103.TreeNode(2, new LeetCode103.TreeNode(4), new LeetCode103.TreeNode(5)),
            new LeetCode103.TreeNode(3, new LeetCode103.TreeNode(6), new LeetCode103.TreeNode(7)));

        System.out.println(levelOrder(root));
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int c1 = 1; //当前层节点数
        boolean odd = true; //是否为奇数层
        while (!queue.isEmpty()){
            int c2 = 0;//下一层节点个数
            LinkedList<Integer> list = new LinkedList<>(); //保存每一层结果,这是一个双端队列，可以
            for (int i = 0; i < c1; i++){
                TreeNode node = queue.poll();
                if(odd){
                    list.offerLast(node.val);
                }else {
                    list.offerFirst(node.val);
                }
                if(node.left != null){
                    queue.offer(node.left);
                    c2++;
                }
                if(node.right != null){
                    queue.offer(node.right);
                    c2++;
                }

            }
            c1 = c2;
            odd = !odd;
            result.add(new ArrayList<>(list));
        }
        return result;
    }

}
