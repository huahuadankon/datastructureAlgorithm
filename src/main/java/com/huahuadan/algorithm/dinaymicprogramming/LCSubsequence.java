package com.huahuadan.algorithm.dinaymicprogramming;

import java.util.Arrays;

/**
 * <h3>最长公共子序列</h3>
 */
public class LCSubsequence {
    /*
              a  b  c  x  y  z
           0  0  0  0  0  0  0
        a  0  1  1  1  1  1  1
        b  0  1  2  2  2  2  2
        x  0  1  2  2  3  3  3
        y  0  1  2  2  3  4  4
        z  0  1  2  2  3  4  5

        相同字符
            找到上一行上一列数值+1
        不同字符
            max(上一行, 上一列)
     */


    /**
     *dp[i][j] 定义：存储 text1 前 i 个字符与 text2 前 j 个字符的 LCS 长度。
     * 初始化：第一行和第一列初始化为 0。
     * 递推公式：
     * 字符匹配：dp[i][j] = dp[i-1][j-1] + 1
     * 字符不匹配：dp[i][j] = max(dp[i-1][j], dp[i][j-1])
     * 最终结果：dp[m][n]。
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1]; //text1 的前 i 个字符与 text2 的前 j 个字符的最长公共子序列（LCS）的长度。
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Integer.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        print(dp, text2, text1);
        return dp[m][n];
    }

    /**
     * 优化成一维数组 一维数组 dp[j] 表示 text1 的前 i 个字符 与 text2 的前 j 个字符 的最长公共子序列的长度。
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence2(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // 用于存储当前行的 dp 值
        int[] dp = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            // 用于保存 dp[j-1] 的值，表示 dp[i-1][j-1]
            int prev = 0;
            for (int j = 1; j <= n; j++) {
                int temp = dp[j]; // 临时保存当前 dp[j]，用于下一次循环中的 prev
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[j] = prev + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                prev = temp; // 更新 prev 为当前 dp[j]
            }
        }

        return dp[n];
    }


    static void print(int[][] dp, String a, String b) {
        System.out.println("-".repeat(23));
        Object[] array = a.chars().mapToObj(i -> String.valueOf((char) i)).toArray();
        System.out.printf("     " + "%2s ".repeat(a.length()) + "%n", array);
        System.out.printf("     " + "%2s ".repeat(a.length()) + "%n", a.chars().mapToObj(i -> "0").toArray());
        for (int i = 0; i < b.length(); i++) {
            int[] d = dp[i + 1];
            array = Arrays.stream(d).boxed().toArray();
            System.out.printf(b.charAt(i) + " " + "%2d ".repeat(d.length) + "%n", array);
        }
    }

    public static void main(String[] args) {
        LCSubsequence code = new LCSubsequence();
        System.out.println(code.longestCommonSubsequence2("abxyz", "abcxyz"));
//        System.out.println(code.longestCommonSubsequence("ba", "yby"));
    }
}
