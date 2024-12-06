package com.huahuadan.algorithm.greedy;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 存储递增子序列的最小末尾
        List<Integer> sub = new ArrayList<>();
        for (int num : nums) {
            int pos = binarySearch(sub, num); // 找到 num 在 sub 中的插入位置
            if (pos < sub.size()) {
                // 替换以保持末尾元素最小
                sub.set(pos, num);
            } else {
                // 添加到末尾
                sub.add(num);
            }
        }
        return sub.size();
    }

    // 二分查找，返回第一个大于等于 target 的位置
    private int binarySearch(List<Integer> sub, int target) {
        int left = 0, right = sub.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sub.get(mid) >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Length of LIS: " + solution.lengthOfLIS(nums)); // Output: 4
    }
}
