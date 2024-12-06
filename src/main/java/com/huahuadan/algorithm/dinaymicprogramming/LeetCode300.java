package com.huahuadan.algorithm.dinaymicprogramming;

import java.util.Arrays;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/12/6 18:21
 * @description 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 */
public class LeetCode300 {

    /*
        输入：nums = [10,9,2,5,3,7,101,18]
        输出：4
        解释：最长递增子序列是 [2,3,7,101]，因此长度为 4


     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];// dp[i]表示数组下标为i包括i之前的元素的最长严格递增子序列的长度。
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++){
            for (int j = 0; j < i; j++){
                if (nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = Arrays.stream(dp).max().getAsInt();
        return max;
    }
    public static void main(String[] args) {
        LeetCode300 leetCode300 = new LeetCode300();
        System.out.println(leetCode300.lengthOfLIS(new int[]{4,10,4,3,8,9}));
        System.out.println(leetCode300.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }
}
