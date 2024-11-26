package com.huahuadan.algorithm.sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <h3>双边循环快排 - 处理相等元素</h3>
 */
public class QuickSortHandleDuplicate {

    public static void sort(int[] a) {
        quick(a, 0, a.length - 1);
    }

    private static void quick(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int p = partition(a, left, right);
        quick(a, left, p - 1);
        quick(a, p + 1, right);
    }

    /*
        循环内
            i 从 left + 1 开始，从左向右找大的或相等的
            j 从 right 开始，从右向左找小的或相等的
            交换，i++ j--

        循环外 j 和 基准点交换，j 即为分区位置
     */

    /**p
     * 这里先i后j，还是先j后i都无所谓了，因为无论如何j最后都会指向小于或等于基准点的元素，i和j是平等的
     * @param a
     * @param left
     * @param right
     * @return
     */
    private static int partition(int[] a, int left, int right) {
        int idx = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
        swap(a, left, idx);
        int pv = a[left];
        int i = left; //可以是left,也可以是left+1
        int j = right;
        while (i <= j) { //这里必须是<=，与之前不一样的是，这里的j不一定始终指向小于或等于基准点的元素，
            // 如果最后一轮i=j这时候j指向的是比基准点大的元素，就不会进入循环，也就导致基准点与比自己大的元素交换，分区失败。
            // i 从左向右找大的或者相等的
            while (i <= j && a[i] < pv) {
                i++;
            }
            // j 从右向左找小的或者相等的
            while (i <= j && a[j] > pv) { //内层循环也必须是<=，因为如果是<.这时候j指向的是比基准点小的元素，j不会动，但是i不满足条件也不会动。
                // 最后进入if条件j--,i++，这时候基准点再与j交换，也是不对的，所以必须是<=，让i++,防止进入if。
                j--;
            }
            if (i <= j) {
                swap(a, i, j);
                i++; //防止i,j指向的元素与基准点相等，陷入死循环
                j--;
            }
        }
        swap(a, j, left);
        return j;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
//        int[] a = {4, 2, 1, 3, 2, 4}; // 最外层循环 = 要加
        //int[] a = {2, 1, 3, 2}; // 内层循环 = 要加
        int[] a = {2, 2, 1, 3, 2}; // 内层if要加
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
