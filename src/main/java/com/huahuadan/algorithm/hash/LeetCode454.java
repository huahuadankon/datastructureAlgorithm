package com.huahuadan.algorithm.hash;

import java.util.HashMap;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2025/3/3 19:44
 * @description 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 */
public class LeetCode454 {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int res = 0;
        int sum = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i : nums1){
            for(int j : nums2){
                sum = i+j;
                map.put(sum, map.getOrDefault(sum,0)+1);
            }
        }

        for(int i : nums3){
            for(int j : nums4){
                res += map.getOrDefault(0-i-j,0);
            }
        }
        return res;
    }
}
