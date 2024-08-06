package org.ronak.patterns;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpperBoundTest {

    @Test
    void testSmallerThanMin() {
        int[] nums = {2, 4, 5, 7, 9};

        assertEquals(-1, BinarySearchLowerUpperBound.searchUpperBound(nums, 1.0));
    }

    @Test
    void testEqual() {
        int[] nums = {2, 4, 5, 7, 9};

        assertEquals(1, BinarySearchLowerUpperBound.searchUpperBound(nums, 4));
    }

    @Test
    void testDouble() {
        int[] nums = {2, 4, 5, 7, 9};

        assertEquals(0, BinarySearchLowerUpperBound.searchUpperBound(nums, 2.5));
    }

    @Test
    void testRightHalf() {
        int[] nums = {2, 4, 5, 7, 9};

        assertEquals(3, BinarySearchLowerUpperBound.searchUpperBound(nums, 8.2));
    }

    @Test
    void testHigherThanMax() {
        int[] nums = {2, 4, 5, 7, 9};

        assertEquals(4, BinarySearchLowerUpperBound.searchUpperBound(nums, 10.0));
    }

}