package com.huahuadan.algorithm.sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/26 9:04
 * @description 双边循环快排
 */
public class QuickSortHoare {
    private static void sort(int[] a) {
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

    /**
     * <h3>双边快排分区，需要注意的点有</h3>
     * <ol>
     *     <li>使用随机数作为基准点，防止出现逆序排好序的情况，导致一轮分区极度不平衡</li>
     *     <li>选择left为基准点的话，内层循环的先j后i，举例说明(5，1，3，4，6，8，7，9)先i后j的话，一轮循环后i停在6，j也停在6 ,最后5,6,交换明显分区失败
     *         先j,后i一轮循环后j停在4,i停在4，4，5，交换分区成功,原理也很简单，
     *         因为一轮循环后j指向的位置一定是小于或等于基准点元素的，基准点与比自身小的元素交换不会出错，但是与比自身大的元素交换很明显就有问题了。
     *         如果是先i后j，有可能j走的时候还没遇到比基准点小或等于的元素就停了下来</li>
     *         <li>内层循环也要保证i&lt;j</li>
     * </ol>
     * @param a
     * @param left
     * @param right
     * @return
     */
    private static int partition(int[] a, int left, int right) {
        int idx = ThreadLocalRandom.current().nextInt(right-left+1) + left;//使用随机数做基准点
        int pv = a[idx];
        swap(a,idx,left);
        int i =left;//不能是left+1，只有两个元素的时候，会直接交换这两个元素，根本不会判断大小
        int j = right;
        while (i < j) {
            //i,j并不平等
            // 1. j 从右向左找小(等)的 j指向的始终是比基准点小或等于的元素.
            while (i < j && a[j] > pv) {
                j--;
            }
            // 2. i 从左向右找大的
            while (i < j && a[i] <= pv) { //a[i]这里必须是<=pv，因为如果i停在a[i]=pv,j也停在a[j]=pv，即便是交换后，二者也无法移动，陷入死循环
                i++;
            }
            swap(a,i,j);
        }
        swap(a,left,i);
        return i;
    }


    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 8, 5, 1, 4};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }


}
