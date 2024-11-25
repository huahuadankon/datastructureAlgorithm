package com.huahuadan.algorithm.sort;

import java.util.Arrays;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/25 19:36
 * @description 插入排序
 */
public class InsertionSort {
    /**
     * 非递归版本
     * low指向未排序区域的最左边
     * low-1指向已排序区域最右边
     * @param a
     */
    public static void insertionSort(int[] a){
        for (int low = 1; low < a.length; low++){
            int key = a[low];
            int j = low - 1;
            while (j>=0 && a[j] > key){ // 大于就向右移，空出位置
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    /**
     * 递归版本
     * @param a
     */
    public static void insertionSort2(int[] a, int low) {
        if (low == a.length) {
            return;
        }
        int key = a[low];
        int j = low - 1;
        while (j >= 0 && a[j] > key) { // 大于就向右移，空出位置
            a[j + 1] = a[j];
            j--;
        }
        a[j + 1] = key;
        insertionSort2(a, low + 1);

    }

    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        System.out.println(Arrays.toString(a));
        //insertionSort(a);
        insertionSort2(a,1);
        System.out.println(Arrays.toString(a));
    }


}
