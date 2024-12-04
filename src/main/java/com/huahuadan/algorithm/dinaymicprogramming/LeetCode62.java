package com.huahuadan.algorithm.dinaymicprogramming;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/12/4 20:39
 * @description 不同路径问题 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 */
public class LeetCode62 {
    public static void main(String[] args) {
        int paths = uniquePaths2(3, 7);
        System.out.println(paths);

    }

    /**
     * 二维数组解决
     * @param m
     * @param n
     * @return
     */
    private static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n]; // dp[i][j] 的含义是到达位置i,j需要的所以路径
        for(int i = 0; i < m; i++){
            dp[i][0] = 1;
        }
        for(int j = 0; j < n; j++){
            dp[0][j] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];// 递推公式
            }
        }
        print(dp);
        return dp[m - 1][n - 1];
    }

    /**
     * 降维，采用一维数组解决
     * @param m
     * @param n
     * @return
     */
    private static int uniquePaths2(int m, int n) {
        int dp[] = new int[n];
        for(int i = 0; i < n; i++){
            dp[i] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[j] = dp[j]+dp[j - 1];
            }
        }

        return dp[n - 1];
    }





    static void print(int[][] dp) {
        System.out.println("-".repeat(20));
        Object[] array = IntStream.range(0, dp[0].length + 1).boxed().toArray();
        System.out.printf(("%2d ".repeat(dp[0].length)) + "%n", array);
        for (int[] d : dp) {
            array = Arrays.stream(d).boxed().toArray();
            System.out.printf(("%2d ".repeat(d.length)) + "%n", array);
        }
    }
}
