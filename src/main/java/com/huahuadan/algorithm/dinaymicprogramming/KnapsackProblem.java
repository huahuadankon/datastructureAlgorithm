package com.huahuadan.algorithm.dinaymicprogramming;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/12/4 20:48
 * @description 01背包问题，动态规划的解法
 */
public class KnapsackProblem {
    static class Item {
        int index;
        String name;
        int weight;//重量
        int value;//价值

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
                new Item(1, "黄金", 4, 1600),
                new Item(2, "宝石", 8, 2400),
                new Item(3, "白银", 5, 30),
                new Item(4, "钻石", 1, 10_000),
        };
        System.out.println(select(items, 10));
    }

    /**
     *
     * @param items 物品数组
     * @param capacity 背包容量
     * @return 背包能装的最大价值
     */
    private static int select(Item[] items, int capacity) {
        //dp数组的含义： 背包容量为j时，放前i个物品，背包所能装的最大价值。
        int[][] dp = new int[items.length][capacity + 1];
        for (int i = 1; i < items.length; i++) {
            dp[i][0] = 0; //背包容量为0时，价值肯定是0
        }
        //第一行需要提前初始化，防止后面数组i-1下标越界
        for(int j = 1; j <= capacity; j++ ) {
            if(j >= items[0].weight){
                dp[0][j] = items[0].value;
            }else {
                dp[0][j] = 0;
            }
        }
        for(int i = 1; i < items.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                if(j >= items[i].weight) { // 这时候的选择有两个:装当前这个物品还是不装当前这个物品
                    //前一个参数是不装当前物品，后一个参数是装当前物品.判断的是上一层的物品i-1所以不会重复添加
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i-1][j - items[i].weight] + items[i].value);
                }else {
                    dp[i][j] = dp[i - 1][j];//装不下，
                }
            }
        }
        print(dp);
        return dp[items.length - 1][capacity];
    }

    /**
     * 优化成一维数组
     * @param items
     * @param capacity
     * @return
     */
    private static int select2(Item[] items, int capacity) {
        int[] dp = new int[capacity+1];//这个dp数组的含义就是 dp[i] 容量为i的背包最多能装下的价值
        dp[0] = 0;
        for (Item item : items) {
            for (int j = capacity; j >=1; j--) {
                //这里选择倒序遍历，最重要的是防止物品重复添加，因为一维数组添加的时候会去找前面索引的数组值dp[j-item.weight] + item.value
                //dp[j-item.weight]有可能已经添加了当前物品且更新了，这时候再此判断，有可能再加一遍当前物品。如果是倒序遍历。数组前面的索引就还没有添加当前物品
                if(j >= item.weight){
                    dp[j] = Math.max(dp[j], dp[j-item.weight] + item.value);
                }
            }
        }
        return dp[capacity];
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
