package com.huahuadan.algorithm.dinaymicprogramming;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/12/5 18:58
 * @description 完全背包问题，相比01背包问题，在于每个物品可以重复拿
 */
public class KnapsackProblemComplete {
    static class Item {
        int index;
        String name;
        int weight;
        int value;

        public Item(int index, String name, int weight, int value) {
            this.index = index;
            this.name = name;
            this.weight = weight;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Item(" + name + ")";
        }
    }

    public static void main(String[] args) {
        Item[] items = new Item[]{
                new Item(1, "青铜", 2, 3),    // c
                new Item(2, "白银", 3, 4),    // s
                new Item(3, "黄金", 4, 7),    // a
        };
        System.out.println(select(items, 6));
    }

    /**
     * 二维dp 含义与01背包完全相同
     * @param items 物品数组
     * @param capacity 背包容量
     * @return
     */
    private static int select(Item[] items, int capacity) {
        int dp[][] = new int[items.length][capacity+1];
        Item item0 = items[0];
        for (int j = 0; j <= capacity; j++) {
            if (j >= item0.weight) {
                dp[0][j] = dp[0][j - item0.weight] + item0.value;
            }
        }
        print(dp);
        for (int i = 1; i < items.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                if(j>=items[i].weight){
                    //这里唯一与01背包的区别是 dp[i][j-items[i].weight] + items[i].value 是dp[i]而不是i-1
                    // 说明装当前物品时是从当前行找，也就是允许重复添加物品
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-items[i].weight] + items[i].value);
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        print(dp);

        return dp[items.length-1][capacity];

    }

    /**
     *  完全背包的降维代码，只与01背包有一个不同，那就是遍历背包时正序遍历
     * @param items
     * @param total
     * @return
     */
    private static int select2(Item[] items, int total) {
        int[] dp = new int[total + 1];
        for (Item item : items) {
            for (int j = 0; j < total + 1; j++) {
                //正序遍历，因为允许重复加入
                if (j >= item.weight) {
                    dp[j] = Integer.max(dp[j], dp[j - item.weight] + item.value);
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[total];
    }



    static void print(int[][] dp) {
        System.out.println("   " + "-".repeat(63));
        Object[] array = IntStream.range(0, dp[0].length + 1).boxed().toArray();
        System.out.printf(("%5d ".repeat(dp[0].length)) + "%n", array);
        for (int[] d : dp) {
            array = Arrays.stream(d).boxed().toArray();
            System.out.printf(("%5d ".repeat(d.length)) + "%n", array);
        }
    }



}
