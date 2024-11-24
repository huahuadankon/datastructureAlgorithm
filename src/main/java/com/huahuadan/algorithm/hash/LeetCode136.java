package com.huahuadan.algorithm.hash;

import java.util.HashSet;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/24 19:13
 * @description 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 */
public class LeetCode136 {
    public int singleNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums){
            if(!set.add(num)){
                set.remove(num);
            }
        }
        return set.iterator().next();
    }

    /*
        输入：nums = [2,2,1]
        输出：1        1

        输入：nums = [4,1,2,1,2]
        输出：4        4

        思路2
        1. 任何相同的数字异或，结果都是 0
        2. 任何数字与 0 异或，结果是数字本身
     */
    public int singleNumber2(int[] nums) {
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            num = num ^ nums[i]; //整体看还是消掉了的
        }
        return num;
    }
}
