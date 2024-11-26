package com.huahuadan.algorithm.sort;

import com.huahuadan.datastructure.array.DynamicArray;

import java.util.Arrays;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/26 19:08
 * @description 桶排序，将原始数组的元素分组，放入不同的桶中，后面的桶中存储的元素大于前面的桶中的，在对每个桶排序，最后一次取出桶的元素，放入原始数组,适用于数据不太大的情况
 * 不是严格意义的分治： 桶排序更接近于一种基于数据分布特点的分组处理，而非严格的递归分治。
 */
public class BucketSort {
    /*
        0   0,1,2
        1   3,4,5
        2   6,7,8
        3   9
     */
    public static void sort(int[] a, int range) {
        int max = a[0];
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
            if (a[i] < min) {
                min = a[i];
            }
        }
        // 1. 准备桶
        DynamicArray[] buckets = new DynamicArray[(max - min) / range + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new DynamicArray();
        }
        // 2. 放入数据
        for (int age : a) {
            buckets[(age - min) / range].addLast(age);
        }
        int k = 0;
        for (DynamicArray bucket : buckets) {
            // 3. 排序桶内元素
            int[] array = bucket.array();
            InsertionSort.insertionSort(array);
            System.out.println(Arrays.toString(array));
            // 4. 把每个桶排序好的内容，依次放入原始数组
            for (int v : array) {
                a[k++] = v;
            }
        }
    }

    public static void main(String[] args) {
        int[] ages = {5,2,1,4,3,5,7,6,9,8,10,22};
        System.out.println(Arrays.toString(ages));
        sort(ages, 3);
        System.out.println(Arrays.toString(ages));


    }
}
