package com.huahuadan.algorithm.hash;

import java.util.*;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/24 18:29
 * @description 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 */
public class LeetCode49 {
    /*
       解法1 思路
       1. 遍历字符串数组，每个字符串中的字符重新排序后作为 key
       2. 所谓分组，其实就是准备一个集合，把这些单词加入到 key 相同的集合中
       3. 返回分组结果
    */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray); // 利用排序后的字符串作为key
            /*if (list == null) {
                list = new ArrayList<>();
                map.put(key, list);
            }*/
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(str);
        }
        return new ArrayList<>(map.values());
    }

    static class ArrayKey {
        int[] key = new int[26];

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ArrayKey arrayKey = (ArrayKey) o;

            return Arrays.equals(key, arrayKey.key);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(key);
        }

        public ArrayKey(String str) {
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i); // 'a'97-97=0  'b'98-97  'a'
                key[ch - 97]++;
            }
        }
    }

    /*
        题目中有说明：strs[i] 仅包含小写字母
        key = [2, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0]  26
        key = [2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0]  26
        "eaat", "teaa"
     */
    public List<List<String>> groupAnagrams2(String[] strs) { // 5ms
        HashMap<ArrayKey, List<String>> map = new HashMap<>();
        for (String str : strs) {
            ArrayKey key = new ArrayKey(str);//利用字符串映射形成的26位的int数组作为key，以区分
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(str);
        }
        return new ArrayList<>(map.values());
    }

}
