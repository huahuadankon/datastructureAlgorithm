package com.huahuadan.algorithm.greedy;

import java.util.*;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/12/2 19:04
 * @description 零钱兑换 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 *请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 */
public class LeetCode518 {

    public int change(int[] coins, int amount) {
        Arrays.sort(coins);
        return rec(0, coins, amount, new LinkedList<>(), true);
    }

    /**
     * 求凑成剩余金额的解的个数，更倾向于回溯算法
     * @param index     当前硬币索引
     * @param coins     硬币面值数组
     * @param remainder 剩余金额
     * @param stack     -
     * @param first     -
     * @return 解的个数
     */
    public int rec(int index, int[] coins, int remainder, LinkedList<Integer> stack, boolean first) {
        if(!first) {
            stack.push(coins[index]);
        }
        // 情况1：剩余金额 < 0 - 无解
        int count = 0;
        if (remainder < 0) {
            print("无解：", stack);
        }
        // 情况2：剩余金额 == 0 - 有解
        else if (remainder == 0) {
            print("有解：", stack);
            count = 1;
        }
        // 情况3：剩余金额 > 0 - 继续递归
        else {
            for (int i = index; i < coins.length; i++) {
                count += rec(i, coins, remainder - coins[i], stack, false);
            }
        }
        if (!stack.isEmpty()) {
            stack.pop();
        }
        return count;
    }

    private static void print(String prompt, LinkedList<Integer> stack) {
        ArrayList<Integer> print = new ArrayList<>();
        ListIterator<Integer> iterator = stack.listIterator(stack.size());
        while (iterator.hasPrevious()) {
            print.add(iterator.previous());
        }
        System.out.println(prompt + print);
    }

    public static void main(String[] args) {
        LeetCode518 leetcode = new LeetCode518();
//        int count = leetcode.coinChange(new int[]{1, 5, 10, 25}, 41);
//        int count = leetcode.coinChange(new int[]{25, 10, 5, 1}, 41);
//        int count = leetcode.coinChange(new int[]{5, 2, 1}, 5);
//        int count = leetcode.coinChange(new int[]{1, 2, 5}, 5);
        int count = leetcode.change(new int[]{15, 10, 1}, 21); //利用数组从大到小的贪心思路
        System.out.println(count);
    }






}
