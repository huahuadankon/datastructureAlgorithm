package com.huahuadan.algorithm.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2025/3/4 17:11
 * @description 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * 并不是特别适合hash法，而是双指针的典型例题。
 */
public class LeetCode15 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        int left;
        int right;

        for(int i = 0; i < nums.length; i++){
            if (nums[i] > 0) {
                return result;
            }
            //对第一个数去重
            if(i>0 && nums[i] == nums [i-1]){
                continue;
            }
            left = i+1;
            right = nums.length-1;
            while(right > left){
                if(nums[i] + nums [left] + nums[right] > 0){
                    right--;
                }else if(nums[i] + nums [left] + nums[right] < 0){
                    left++;
                }else{
                    result.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    //对后两个数去重
                    while(right > left && nums[left] == nums[left+1]){
                        left++;
                    }
                    while(right > left && nums[right] == nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return result;


    }
}
