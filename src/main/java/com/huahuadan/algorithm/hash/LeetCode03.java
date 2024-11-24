package com.huahuadan.algorithm.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/24 16:59
 * @description 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串的长度。
 */
public class LeetCode03 {
    public int lengthOfLongestSubstring1(String s) {
        int i = 0, j = 0;
        int maxlength = 0;
        HashSet<Character> set = new HashSet<>();
        while (j < s.length()) {
            char c = s.charAt(j);
            if (!set.contains(c)) {
                set.add(c);
                maxlength = Math.max(maxlength, j - i + 1); // 更新最长长度
                j++;
            } else {
                set.remove(s.charAt(i)); // 移除左边字符
                i++;
            }
        }

        return maxlength;
    }

    public int lengthOfLongestSubstring(String s) {
        int[] map = new int[128]; //利用字符的ASCII码作为hash映射
        Arrays.fill(map, -1);
        int maxlength = 0;
        int begin = 0;
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if(map[c] != -1) {
                begin = Math.max(begin,map[c]+1);
            }
            map[c] = end;
            System.out.println(s.substring(begin, end + 1));
            maxlength = Math.max(maxlength, end - begin + 1);
        }
        return maxlength;
    }

    public static void main(String[] args) {
        LeetCode03 leetcode = new LeetCode03();
        System.out.println(leetcode.lengthOfLongestSubstring("abcabcbb"));
    }
}
