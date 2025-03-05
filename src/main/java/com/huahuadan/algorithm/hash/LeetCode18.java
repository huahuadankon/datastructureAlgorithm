package com.huahuadan.algorithm.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2025/3/4 17:38
 * @description 四数之和
 */
public class LeetCode18 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);  // 排序数组
        List<List<Integer>> result = new ArrayList<>();  // 结果集
        for (int k = 0; k < nums.length; k++) {
            // 剪枝处理
            if (nums[k] > target && nums[k] >= 0) {
                break;
            }
            // 对nums[k]去重
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            for (int i = k + 1; i < nums.length; i++) {
                // 第二级剪枝
                if (nums[k] + nums[i] > target && nums[k] + nums[i] >= 0) {
                    break;
                }
                // 对nums[i]去重
                if (i > k + 1 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int left = i + 1;
                int right = nums.length - 1;
                while (right > left) {
                    long sum = (long) nums[k] + nums[i] + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        result.add(Arrays.asList(nums[k], nums[i], nums[left], nums[right]));
                        // 对nums[left]和nums[right]去重
                        while (right > left && nums[right] == nums[right - 1]) right--;
                        while (right > left && nums[left] == nums[left + 1]) left++;
                        right--;
                        left++;
                    }
                }
            }
        }
        return result;
    }
}
