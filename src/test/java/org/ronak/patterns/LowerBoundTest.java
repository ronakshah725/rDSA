package org.ronak.patterns;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LowerBoundTest {

    @Test
    void testSmallerThanMin() {
        int[] nums = {2, 4, 5, 7, 9};

        assertEquals(0, BinarySearchLowerUpperBound.searchLowerBound(nums, 1.0));
    }

    @Test
    void testEqual() {
        int[] nums = {2, 4, 5, 7, 9};

        assertEquals(0, BinarySearchLowerUpperBound.searchLowerBound(nums, 2));
    }

    @Test
    void testDouble() {
        int[] nums = {2, 4, 5, 7, 9};

        assertEquals(1, BinarySearchLowerUpperBound.searchLowerBound(nums, 2.5));
    }

    @Test
    void testRightHalf() {
        int[] nums = {2, 4, 5, 7, 9};

        assertEquals(4, BinarySearchLowerUpperBound.searchLowerBound(nums, 8.0));
    }

    @Test
    void testHigherThanMax() {
        int[] nums = {2, 4, 5, 7, 9};

        assertEquals(-1, BinarySearchLowerUpperBound.searchLowerBound(nums, 10.0));
    }

}