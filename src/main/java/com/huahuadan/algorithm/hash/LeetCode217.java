package com.huahuadan.algorithm.hash;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/24 19:02
 * @description 判断数组是否存在重复元素
 */
public class LeetCode217 {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            /*if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);*/
            if(!set.add(nums[i])){
                return true;
            }
        }
        return false;
    }
    public boolean containsDuplicate1(int[] nums) { // 6ms
        HashMap<Integer, Object> map = new HashMap<>(nums.length * 2);
        Object value = new Object();
        for (int key : nums) {
            Object put = map.put(key, value);
            if (put != null) {
                return true;
            }
        }
        return false;
    }
}
