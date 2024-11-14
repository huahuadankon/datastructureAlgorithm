package com.huahuadan.algorithm.heap;

import com.huahuadan.datastructure.heap.MaxHeap;
import com.huahuadan.datastructure.heap.MinHeap;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/14 19:55
 * @description 找到数组中第k大的元素
 */
public class LeetCode215 {
    public static void main(String[] args) {
        int[] arr = {1,3,6,4,2,6,3,7};
        int kthLargest = findKthLargest(arr, 3);
        System.out.println(kthLargest);
    }

    //大顶堆的解法
    public static int findKthLargest(int[] nums, int k) {
        MaxHeap maxHeap = new MaxHeap(nums);
        int result = 0;
        for (int i = 0; i < k; i++) {
            result = maxHeap.poll();
        }
         return result;
    }

    //小顶堆的揭发
    public int findKthLargest2(int[] numbers, int k) {
        MinHeap heap = new MinHeap(k);
        for (int i = 0; i < k; i++) {
            heap.offer(numbers[i]);
        }
        for (int i = k; i < numbers.length; i++) {
            if(numbers[i] > heap.peek()) {
                heap.replace(numbers[i]);
            }
        }
        return heap.peek();
    }

}
