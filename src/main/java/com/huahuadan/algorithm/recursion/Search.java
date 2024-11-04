package com.huahuadan.algorithm.recursion;

/**
 * @author liuyichen
 * @version 1.0
 */
public class Search {
    private static int binarySearch(int[] arr, int target, int i, int j) {
        if(i>j){
            return -1;
        }
        int mid = (i+j)>>>1;
        if(target<arr[mid]){
            return binarySearch(arr, target, i, mid-1);
        }else if(target>arr[mid]){
            return binarySearch(arr, target, mid+1, j);
        }
        return mid;
    }

    public static int binarySearch(int[] arr, int target) {
        return binarySearch(arr, target, 0, arr.length-1);
    }



}
