package com.huahuadan.algorithm.search;

/**
 * 二分查找
 */
public class BinarySearch{
    public static int binarySearch(int[] a, int target) {
        int i = 0, j = a.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1;//无符号右移运算，相比直接除以二，不会导致出现超出java正整数所能表示的最大存储范围，
            // 而且能自动向下取整，二进制，java中二进制是带符号的，可能算出来的正数变成负数。
            if (target < a[m]) {			// 在左边
                j = m - 1;
            } else if (a[m] < target) {		// 在右边
                i = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    //另一种写法
    public static int binarySearch2(int[] a, int target) {
        int i = 0, j = a.length;
        while (i < j) { //在比较时不会取到右指针
            int m = (i + j) >>> 1;
            if (target < a[m]) {			// 在左边
                j = m;
            } else if (a[m] < target) {		// 在右边
                i = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    //平衡版二分查找

    /**
     * 1. 左闭右开的区间，i 指向的可能是目标，而 j 指向的不是目标
     * 2. 不奢望循环内通过 m 找出目标, 缩小区间直至剩 1 个, 剩下的这个可能就是要找的（通过 i）
     *    j - i > 1 的含义是，在范围内待比较的元素个数 > 1
     * 3. 改变 i边界时，它指向的可能是目标，因此不能 m+1
     * 4. 循环内的平均比较次数减少了
     * 5. 时间复杂度log(n)
     * @param a
     * @param target
     * @return
     */
    public static int binarySearchBalance(int[] a, int target) {
        int i = 0, j = a.length;
        while (1 < j - i) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m;
            } else {
                i = m;
            }
        }
        return (a[i] == target) ? i : -1;
    }

}

