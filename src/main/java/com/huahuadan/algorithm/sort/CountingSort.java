package com.huahuadan.algorithm.sort;

import java.util.Arrays;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/26 18:31
 * @description 计数排序,计数排序通过统计每个元素的出现次数，然后利用这些统计结果将元素按顺序填回到原数组中，避免了元素之间的比较。
 * 将元素的值映射成新数组的下标，然后遍历新数组，一次取出不为零的元素覆盖回原始数组，就自然排好序了，适合数据不大，重复元素多的。
 */
public class CountingSort {
    /*
            {5, 1, 1, 3, 0, -1}  原始数组 a

            [1   1   2   0   1   0   1 ] count
             0   1   2   3   4   5   6
             -1  0   1       3       5
    */
    public static void sort(int[] a) {
        int max = a[0];
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
            }
            if (min > a[i]) {
                min = a[i];
            }
        }
        int[] count = new int[max - min + 1];
        for (int i = 0; i < a.length; i++) {
            count[a[i] - min]++;//减去min相当于右移，此时count 下标i 就是原始数组相应值的 映射
        }
        System.out.println(Arrays.toString(count));
        int k = 0;
        for (int i = 0; i < count.length; i++) {
            while(count[i] > 0){
                a[k++] = i + min; //加上偏移量，得到原始数组中的值
                count[i]--;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {5, 1, 1, 3, 0, -1};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
