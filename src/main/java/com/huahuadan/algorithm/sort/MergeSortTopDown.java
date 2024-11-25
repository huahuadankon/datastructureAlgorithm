package com.huahuadan.algorithm.sort;

import java.util.Arrays;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/25 20:33
 * @description 归并排序,分治法的典型应用,自上向下
 */
public class MergeSortTopDown {
    /**
     * 合
     * @param a1 原始数组
     * @param i 原始数组第一块有序部分的起始下标
     * @param iEnd 结束下标
     * @param j 原始数组第二块有序部分的起始下标
     * @param jEnd 结束下标
     * @param a2 临时数组，用于存放合并后的结果
     */
    public static void merge(int[] a1, int i,int iEnd, int j, int jEnd, int[] a2) {
        int k = i;
        while (i <= iEnd && j <= jEnd) {
            if(a1[i] < a1[j]){
                a2[k] = a1[i];
                i++;
            }else {
                a2[k] = a1[j];
                j++;
            }
            k++;
        }

        if(i>iEnd){
            System.arraycopy(a1,j,a2,k,jEnd-j+1);
        }
        if(j>jEnd){
            System.arraycopy(a1,i,a2,k,iEnd-i+1);
        }
    }

    /**
     * 递归代码
     * @param a1
     * @param i
     * @param iEnd
     * @param j
     * @param jEnd
     * @param a2
     * @param k
     */
    public static void merge(int[] a1, int i, int iEnd, int j, int jEnd,
                             int[] a2, int k) {
        if (i > iEnd) {
            System.arraycopy(a1, j, a2, k, jEnd - j + 1);
            return;
        }
        if (j > jEnd) {
            System.arraycopy(a1, i, a2, k, iEnd - i + 1);
            return;
        }
        if (a1[i] < a1[j]) {
            a2[k] = a1[i];
            merge(a1, i + 1, iEnd, j, jEnd, a2, k + 1);
        } else {
            a2[k] = a1[j];
            merge(a1, i, iEnd, j + 1, jEnd, a2, k + 1);
        }
    }
    public static void sort(int[] a1) {
        int[] a2 = new int[a1.length];
        split(a1,0,a1.length-1,a2);
    }


    /**
     * 分
     * @param a1 原始数组
     * @param left 原始数组起始下标
     * @param right 原始数组结束下标
     * @param a2 配合merge使用
     */
    public static void split(int[] a1, int left, int right, int[] a2) {
        if(left >= right){
            return;
        }
        int mid = (left + right) >>> 1;
        split(a1, left, mid, a2);
        split(a1, mid + 1, right, a2);
        //治，在归的过程中相当于合并一个部分有序的数组
        merge(a1,left, mid, mid+1,right, a2);
        System.arraycopy(a2,left,a1,left,right-left+1);
        System.out.println(left+","+mid+","+right);
    }

    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 8, 5, 6, 4};
        System.out.println(Arrays.toString(a));
        System.out.println("===============");
        sort(a);
        System.out.println("===============");
        System.out.println(Arrays.toString(a));
    }

}
