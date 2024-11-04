package com.huahuadan.algorithm.recursion;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuyichen
 * @version 1.0
 */
class SortTest {
    @Test
    public void testBubbleSort() {
        int[] a = {3, 2, 6, 1, 5, 4, 7};
        Sort.bubbleSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
    }


