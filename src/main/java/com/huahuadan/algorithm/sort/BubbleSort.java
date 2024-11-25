package com.huahuadan.algorithm.sort;

import java.util.Arrays;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/25 18:38
 * @description 冒泡排序
 */
public class BubbleSort {
    /**
     * 非递归版本
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        int j = arr.length - 1; //关键优化，利用j来记录未排序位置的边界
        do{
            int x = 0;
            for(int i = 0; i < j; i++){
                if(arr[i] > arr[i+1]){
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    x = i;
                }
            }
            j = x;
        }while (j!=0);
    }

    /**
     *
     * @param arr 数组
     * @param j 未排序的右边界
     */
    public static void bubbleSort2(int[] arr,int j) {
        if(j == 0){
            return;
        }
        int x = 0;
        for (int i = 0; i < j; i++) {
            if(arr[i] > arr[i+1]){
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
                x = i;
            }
        }
        bubbleSort2(arr,x);
    }

    /**
     * 经典写法
     * @param arr
     */
    public static void bubbleSort3(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    public static void main(String[] args) {
        int[] a = {6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(a));
        //bubbleSort(a);
        bubbleSort2(a,a.length-1);
        System.out.println(Arrays.toString(a));
    }




}
