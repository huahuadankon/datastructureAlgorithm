package com.huahuadan.algorithm.sort.exercise;

import java.util.Arrays;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/26 20:00
 * @description 给你两个数组，arr1 和 arr2，arr2 中的元素各不相同，arr2 中的每个元素都出现在 arr1 中。
 *对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 */
public class LeetCode1122 {
    /*public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int p = 0;
        for (int i = 0; i < arr2.length; i++){
            for(int j = 0; j < arr1.length; j++){
                if(arr1[j] == arr2[i]){
                    swap(arr1,j,p);
                    p++;
                }
            }
        }
        sort(arr1,p,arr1.length-1);
        return arr1;
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort(int[] arr, int left, int right){
        for(int low = left+1; low <= right; low++){
            int temp = arr[low];
            int i = low - 1;
            while(i >= left && arr[i] > temp){
                arr[i+1] = arr[i];
                i--;
            }
            arr[i+1] = temp;
        }
    }*/

    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] count = new int[1001];
        for (int i : arr1) {
            count[i]++;
        }
        System.out.println(Arrays.toString(count));
        // 2, 4, 1
        // 1  2  3
        // 1 1 2 2 2 2 3 原始count排序
        // 2 2 2 2 3 1 1 要求的
        int[] result = new int[arr1.length];
        int k = 0;
        for (int i : arr2) {
            while (count[i] > 0) {
                result[k++] = i;
                count[i]--;
            }
        }
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                result[k++] = i;
                count[i]--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {2,2,3,2,6,3,8,9};
        int[] arr2 = {3,2,6};
        relativeSortArray(arr1,arr2);
        System.out.println(Arrays.toString(arr1));
    }
}
