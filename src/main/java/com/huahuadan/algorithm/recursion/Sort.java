package com.huahuadan.algorithm.recursion;

/**
 * @author liuyichen
 * @version 1.0
 */
public class Sort {
    public static void bubbleSort(int[] a,int low,int high) {
        if(low == high){
            return;
        }
        int j = low;
        for (int i = low; i < high; i++) {
            if(a[i] > a[i+1]){
                swap(a,i,i+1);
                j = i;
            }
        }
        bubbleSort(a,low,j);

    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
