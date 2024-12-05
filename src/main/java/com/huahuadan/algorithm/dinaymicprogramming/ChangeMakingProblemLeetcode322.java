package com.huahuadan.algorithm.dinaymicprogramming;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * <h3>零钱兑换 - 动态规划</h3>
 * <p>凑成总金额的凑法中，需要硬币最少个数是几？</p>
 */
public class ChangeMakingProblemLeetcode322 {
    /*
     面值    0        1        2        3        4        5
       1    0        1        11       111      1111     11111
       2    0        1        2        21       22       221
       5    0        1        2        21       22       5

     面值    0        1        2        3        4        5
      10    0        max      max      max      max      max

     总金额❤  - 类比为背包容量
     硬币面值  - 类比为物品重量
     硬币个数  - 类比为物品价值，固定为1 （求价值(个数)最小的）

     if(装得下) {
        min(上次价值(个数), 剩余容量能装下的最小价值(个数)+1)
        dp[i][j] = min(dp[i-1][j], dp[i][j-item.weight] + 1)
     } else {
        保留上次价值(个数)不变
        dp[i][j] = dp[i-1][j]
     }
     */

    /**
     * 这就是一个完全背包问题，只需要改变一点点条件。
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 0 max max max max max
        Arrays.fill(dp, amount + 1); //初始最大值为amount+1，因为即便全是1组合起来最大值也才amount
        dp[0] = 0;
        System.out.println(Arrays.toString(dp));
        for (int coin : coins) {
            for (int j = coin; j < amount + 1; j++) { //直接从coin面值开始，反正前面amount小于了面值肯定放不下。
                dp[j] = Math.min(dp[j], 1 + dp[j - coin]);//不断对比上一层和当前层的数量的大小
            }
            System.out.println(Arrays.toString(dp));
        }
        int r = dp[amount];
        return r == amount+1 ? -1 : r;//如果最后的最大值没有被更新，说明没有组合能凑齐总金额
    }

    public static void main(String[] args) {
        ChangeMakingProblemLeetcode322 leetcode = new ChangeMakingProblemLeetcode322();
        int count = leetcode.coinChange(new int[]{1, 2, 5}, 5);
//        int count = leetcode.coinChange(new int[]{25, 10, 5, 1}, 41);
//        int count = leetcode.coinChange(new int[]{2}, 3);
//        int count = leetcode.coinChange(new int[]{15, 10, 1}, 21);
        System.out.println(count);
    }

    static void print(int[][] dp) {
        System.out.println("-".repeat(18));
        Object[] array = IntStream.range(0, dp[0].length + 1).boxed().toArray();
        System.out.printf(("%2d ".repeat(dp[0].length)) + "%n", array);
        for (int[] d : dp) {
            array = Arrays.stream(d).boxed().toArray();
            System.out.printf(("%2d ".repeat(d.length)) + "%n", array);
        }
    }
}
