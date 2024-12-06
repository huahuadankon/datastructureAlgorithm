package com.huahuadan.algorithm.dinaymicprogramming;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/12/6 19:40
 * @description 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？这个组合的总数也成为Catalan数
 */
public class LeetCode92 {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){ // i就是表示当前的n能够组成的二叉搜索树
            for(int j = 1; j <= i; j++){ //j从以1到i就是表示以j为头结点所能组成的二叉搜索树的数量
                //dp[i] += dp[j] * dp[i - j - 1];
                dp[i] += dp[i-j]*dp[j-1];//j-1 为j为头结点左子树节点数量，i-j 为以j为头结点右子树节点数量
                System.out.printf("(%d %d)", i-j,j-1);
            }
            System.out.println();
        }
        return dp[n];

    }
    public static void main(String[] args) {
        LeetCode92 leetcode = new LeetCode92();
        System.out.println(leetcode.numTrees(3));
    }
}
