package com.huahuadan.algorithm.dinaymicprogramming;

import java.util.Arrays;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/12/5 20:57
 * @description 求两个字符串的最长公共子串
 */
public class LCSubstring {
    static void print(int[][] dp, String a, String b) {
        System.out.println("-".repeat(23));
        Object[] array = a.chars().mapToObj(i -> String.valueOf((char) i)).toArray();
        System.out.printf("  " + "%2s ".repeat(a.length()) + "%n", array);
        for (int i = 0; i < b.length(); i++) {
            int[] d = dp[i];
            array = Arrays.stream(d).boxed().toArray();
            System.out.printf(b.charAt(i) + " " + "%2d ".repeat(d.length) + "%n", array);
        }
    }

    /*
            i   t   h   e   i   m   a
        t   0   1   0   0   0   0   0
        h   0   0   2   0   0   0   0
        e   0   0   0   3   0   0   0
        m   0   0   0   0   0   1   0
        a   0   0   0   0   0   0   2

        if(相同字符) {
            dp[i][j] = dp[i-1][j-1] + 1
        } else {
            dp[i][j] = 0
        }
     */

    public static void main(String[] args) {
        System.out.println(lcs("itheima", "thema"));
    }

    private static int lcs(String s1, String s2) {
        int[][] dp = new int[s2.length()][s1.length()];
        int max = 0;
        for (int i = 0; i < s2.length(); i++) {
            for (int j = 0; j < s1.length(); j++) {
               if(s2.charAt(i) == s1.charAt(j)){
                   if(i == 0 || j == 0){
                       dp[i][j] = 1;
                   }else {
                       dp[i][j] = dp[i-1][j-1] + 1;
                   }
                   max = Math.max(max, dp[i][j]);
               }else {
                   dp[i][j] = 0;
               }
            }
        }
        print(dp, s1, s2);


        return max;
    }

}
