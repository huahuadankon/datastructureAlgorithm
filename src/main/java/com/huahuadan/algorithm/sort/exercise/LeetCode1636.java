package com.huahuadan.algorithm.sort.exercise;

import java.util.Arrays;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/26 20:28
 * @description
 */
public class LeetCode1636 {
    public int[] frequencySort(int[] nums) {
        // 1. 统计出现频率
        int[] count = new int[201];
        for (int i : nums) {
            count[i + 100]++;
        }
        // 2. 比较器 按频率升序、再按数值降序
        return Arrays.stream(nums).boxed().sorted((a, b) -> {
            int af = count[a + 100];
            int bf = count[b + 100];
            if (af < bf) {
                return -1;
            } else if (af > bf) {
                return 1;
            } else {
                return b - a;
            }
        }).mapToInt(Integer::intValue).toArray();
    }
}
