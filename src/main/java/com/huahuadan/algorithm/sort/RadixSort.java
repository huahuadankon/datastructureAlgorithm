package com.huahuadan.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/26 19:36
 * @description 基数排序 整数数据：适合对非负整数排序，尤其是范围较大的数据，因为其复杂度与位数相关，而不是直接与数据范围相关。
 * 字符串数据：可用于排序固定长度的字符串（如字母、数字组合）。
 */
public class RadixSort {
    public static void radixSort(String[] a, int length) {
        // 1. 准备桶
        ArrayList<String>[] buckets = new ArrayList[128];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        // 2. 进行多轮按位桶排序
        for (int i = length - 1; i >= 0; i--) {
            // 将字符串放入合适的桶
            for (String s : a) {
                buckets[s.charAt(i)].add(s);
            }
            // 重新取出排好序的字符串，放回原始数组
            int k = 0;
            for (ArrayList<String> bucket : buckets) {
                for (String s : bucket) {
                    a[k++] = s;
                }
                bucket.clear();
            }
//            System.out.println(Arrays.toString(a));
        }
    }

    public static void main(String[] args) {
        String[] phoneNumbers = new String[10];  // 0~127
        phoneNumbers[0] = "13812345678";  // int long
        phoneNumbers[1] = "13912345678";
        phoneNumbers[2] = "13612345678";
        phoneNumbers[3] = "13712345678";
        phoneNumbers[4] = "13512345678";
        phoneNumbers[5] = "13412345678";
        phoneNumbers[6] = "15012345678";
        phoneNumbers[7] = "15112345678";
        phoneNumbers[8] = "15212345678";
        phoneNumbers[9] = "15712345678";

        /*String[] phoneNumbers = new String[6];
        phoneNumbers[0] = "158";
        phoneNumbers[1] = "151";
        phoneNumbers[2] = "235";
        phoneNumbers[3] = "138";
        phoneNumbers[4] = "139";
        phoneNumbers[5] = "159";*/

        /*
            0
            1   151
            2
            3
            4
            5   135
            6
            7
            8   158 138
            9   139 159
            151 135 158 138 139 159  按个位排

            0
            1
            2
            3   135 138 139
            4
            5   151 158 159
            6
            7
            8
            9
            135 138 139 151 158 159  按十位排
         */

        RadixSort.radixSort(phoneNumbers, 11);
        for (String phoneNumber : phoneNumbers) {
            System.out.println(phoneNumber);
        }
    }
}
