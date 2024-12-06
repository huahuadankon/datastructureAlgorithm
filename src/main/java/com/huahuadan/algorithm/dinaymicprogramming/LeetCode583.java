package com.huahuadan.algorithm.dinaymicprogramming;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/12/6 18:03
 * @description 两个字符串的删除操作 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
 *每步 可以删除任意一个字符串中的一个字符。
 */
public class LeetCode583 {
    /*
     sea eat
     ea ea  2
     e  a  t
   s 0  0  0
   e 1  1  1
   a 1  2  2
     */

    /**
     * 其实就是最长公共子序列问题
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = Integer.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int maxlen = dp[m][n];
        int res = m-maxlen+n-maxlen;

        return res;
    }
    public static void main(String[] args) {
        LeetCode583 leetcode583 = new LeetCode583();
        System.out.println(leetcode583.minDistance("horse", "ros"));
    }
}
