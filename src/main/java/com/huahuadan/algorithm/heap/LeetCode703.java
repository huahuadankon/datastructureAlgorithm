package com.huahuadan.algorithm.heap;

import com.huahuadan.datastructure.heap.MaxHeap;
import com.huahuadan.datastructure.heap.MinHeap;

import java.util.Map;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/14 20:15
 * @description
 */
public class LeetCode703 {
    private MinHeap minHeap;
    public LeetCode703(int k, int[] nums) {
        minHeap = new MinHeap(k);
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int add(int val) {
        if(!minHeap.isFull()){
            minHeap.offer(val);
        } else if (minHeap.peek() < val) {
            minHeap.replace(val);
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        LeetCode703 test = new LeetCode703(3,
                new int[]{});

        System.out.println(test.add(3)); // [3] 3
        System.out.println(test.add(5)); // [3 5] 3
        System.out.println(test.add(10));
        System.out.println(test.add(9));
        System.out.println(test.add(4));
    }


}
