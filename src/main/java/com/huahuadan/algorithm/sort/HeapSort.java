package com.huahuadan.algorithm.sort;

import java.util.Arrays;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/25 19:16
 * @description 堆排序
 */
public class HeapSort {
    public static void heapSort(int[] arr) {
        int right = arr.length - 1;
        heapify(arr);
        for (int i = right; i > 0; i--) {
            swap(arr, 0, i);
            down(arr, 0, i);
        }
    }

    public static void heapify(int[] arr) {
        for(int i = arr.length/2 - 1; i >= 0; i--){
            down(arr, i, arr.length);
        }
    }
    public static void down(int[] arr, int parent,int size) {
        while(true){
            int left = parent * 2 + 1;
            int right = left + 1;
            int max = parent;
            if(left < size && arr[left] > arr[max]){
                max = left;
            }
            if(right < size && arr[right] > arr[max]){
                max = right;
            }
            if(parent == max){//相当于当前节点比孩子节点大
                break;
            }
            swap(arr, parent, max);
            parent = max;
        }


    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 1, 7, 6, 4, 5};
        System.out.println(Arrays.toString(a));
        heapSort(a);
        System.out.println(Arrays.toString(a));
    }
}
