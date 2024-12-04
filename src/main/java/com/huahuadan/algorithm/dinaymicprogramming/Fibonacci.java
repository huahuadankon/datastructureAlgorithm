package com.huahuadan.algorithm.dinaymicprogramming;

/**
 * 求斐波那契数列的第n项（动态规划）
 */
public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fibonacci(13));
    }

    /**
     * 一维数组降成0维
     * @param n
     * @return
     */
    public static int fibonacci2(int n) {
        if (n == 1 || n == 0){
            return n;
        }
        int a = 0;
        int b = 1;
        for (int i = 2; i <= n ; i++) {
            int c = b + a;
            a = b;
            b = c;
        }
        return b;
    }

    public static int fibonacci(int n) {
        int[] dp = new int[n + 1];// 用来缓存结果
        if(n == 0 || n == 1){
            return n;
        }
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n ; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

}
