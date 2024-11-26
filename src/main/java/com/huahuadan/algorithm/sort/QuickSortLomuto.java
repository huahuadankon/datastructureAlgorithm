package com.huahuadan.algorithm.sort;

import java.util.Arrays;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/26 8:36
 * @description 单边快速排序
 */
public class QuickSortLomuto {
    public static void sort(int[] a){
        quick(a, 0, a.length - 1);
    }


    public static void quick(int[] arr, int left, int right) {
        if(left >= right){
            return;
        }
        int p = partition(arr,left,right);//p代表基准点元素，在基准点左边的都比p小，在基准点右边的都比p大。
        quick(arr,left,p-1);
        quick(arr,p+1,right);
    }

    /**
     * <h3>单边循环快排（lomuto 洛穆托分区方案）</h3>
     * <p>核心思想：每轮找到一个基准点元素，把比它小的放到它左边，比它大的放到它右边，这称为分区</p>
     * <ol>
     * <li>选择最右元素作为基准点元素</li>
     * <li>j 找比基准点小的，i 找比基准点大的，一旦找到，二者进行交换</li>
     * <ul>
     * <li>交换时机：j 找到小的，且与 i 不相等</li>
     * <li>i 找到 >= 基准点元素后，不应自增</li>
     * </ul>
     * <li>最后基准点与 i 交换，i 即为基准点最终索引</li>
     * </ol>
     * @param arr
     * @param left
     * @param right
     * @return 基准点元素的下标
     */
    private static int partition(int[] arr, int left, int right) {
        int pv = arr[right];
        int i = left;
        int j = left;
        while(j < right){
            if(arr[j] < pv){
               if(i != j) {
                   swap(arr,i,j);
               }
                i++;
            }
            j++;
        }
        swap(arr,i,right);
        return i;
    }


    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {5, 3, 7, 2, 9, 8, 6, 1};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
