package com.huahuadan.algorithm.sort;

import java.util.Arrays;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/25 19:01
 * @description 选择排序
 */
public class SelectionSort {
    /**
     * 非递归实现
     * @param a
     */
    public static void selectionSort(int[] a) {
        for(int right = a.length - 1; right > 0; right--){
            int max = right;
            for(int i = 0; i < right; i++){
                if(a[i] > a[max]){
                    max = i;
                }
            }
            if(max != right){
                swap(a, right, max);
            }
        }
    }

    /**
     * 递归实现
     * @param a
     * @param right
     */
    public static void selectionSort2(int[] a , int right) {
        if(right <= 0){
            return;
        }
        int max = right;
        for(int i = 0; i < right; i++){
            if(a[i] > a[max]){
                max = i;
            }
        }
        if(max != right){
            swap(a, right, max);
        }
        selectionSort2(a, right-1);

    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = { 6,5,4,4,3,2,1 };
        System.out.println(Arrays.toString(a));
        selectionSort2(a,a.length-1);
        System.out.println(Arrays.toString(a));
    }
}
