package com.huahuadan.algorithm.dinaymicprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/12/6 20:07
 * @description 卡特兰数
 */
public class Catalan {
    /**
     * 二叉搜索树的数量，栈的合法出栈序列数
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){ // i就是表示当前的n能够组成的二叉搜索树
            for(int j = 1; j <= i; j++){ //j从以1到i就是表示以j为头结点所能组成的二叉搜索树的数量
                //dp[i] += dp[j] * dp[i - j - 1];
                dp[i] += dp[i-j]*dp[j-1];//j-1 为j为头结点左子树节点数量，i-j 为以j为头结点右子树节点数量

            }
        }
        return dp[n];

    }

    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * 对应leetcode 上的 22题
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        ArrayList<String>[] dp = new ArrayList[n+1];
        dp[0] = new ArrayList<>(List.of(""));
        dp[1] = new ArrayList<>(List.of("()"));
        for(int i = 2; i <= n; i++){
            dp[i] = new ArrayList<>();
            for(int j = 1; j <= i; j++){
                for (String s1 : dp[i - j]) {
                    for (String s2 : dp[j-1]) { //原先是求组合的数量，这里转换成两层循环，一样的道理
                        //这里需要注意的是dp[i-j]表示的是左子树的所有组合，[j-1]表示的是右子树所有的组合,目前还有一对括号，需要加入到这个组合里
                        dp[i].add("("+s1+")"+s2);

                    }
                }
            }
        }
        System.out.println(dp[n]);
        return dp[n];
    }

    public static void main(String[] args) {
        generateParenthesis(3);
    }


}

/*
Catalan 数在以下场景中频繁出现：
二叉搜索树（BST）：n 个节点，能形成的不同二叉搜索树的数量为
括号匹配问题：n 对括号的所有合法匹配方式总数为
凸多边形的三角剖分：一个凸 n+2 边形的不同三角形划分方案数
完全二叉树：n 个节点的完全二叉树的总数为
栈的合法出栈序列：n 个元素的栈，可能的出栈顺序总数为
其他组合结构：例如分割问题、路径计数问题等。
 */
