package com.huahuadan.algorithm.recursion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuyichen
 * @version 1.0
 */
class SearchTest {
    @Test
    void testBinarySearch() {
        int[] a = {1, 2, 4, 4, 4, 5, 6, 7};
        assertEquals(1, Search.binarySearch(a, 2));
        assertEquals(2, Search.binarySearch(a, 3));
    }

}