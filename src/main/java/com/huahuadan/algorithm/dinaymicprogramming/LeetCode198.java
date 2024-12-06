package com.huahuadan.algorithm.dinaymicprogramming;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/12/6 21:11
 * @description 打家劫舍问题
 */
public class LeetCode198 {

    /*
    示例 1：
    输入：[1,2,3,1]
    输出：4
    解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。
    示例 2：
    输入：[2,7,9,3,1]
    输出：12
    解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     */
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1]; //dp[i]表示偷窃前i个房间的最大价值
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1]);
        }
        return dp[n];
    }
    public static void main(String[] args) {
        LeetCode198 leetcode198 = new LeetCode198();
        System.out.println(leetcode198.rob(new int[] { 1, 2, 3, 1 }));
        System.out.println(leetcode198.rob(new int[] { 2,7,9,3,1 }));

    }
}
