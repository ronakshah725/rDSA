package org.ronak.random;

import java.util.ArrayList;
import java.util.List;

public class AmazonDistributeWights {

    static List<Integer> minimalHeaviestSetA(int[] arr){
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        int sumA = 0;
        int maxSumA = 0;
        List<Integer> aList = new ArrayList<>();
        List<Integer> maxAList = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            sumA += arr[i];
            aList.add(arr[i]);
            if (sumA > totalSum - sumA && sumA > maxSumA) {
                maxSumA = sumA;
                maxAList = new ArrayList<>(aList);
            }
        }

        // Sort the list in ascending order
        maxAList.sort((a, b) -> a - b);

        return maxAList;
    }

    /***
     * A covert agent has some crucial information stored in the form of an array of integers. The array
     * contains sensitive information and it must not be revealed to anyone. However, there are few
     * things about the array which are known.
     * An array is said to be analogous to the secret array if all of the following conditions are true:
     *  The length of the array is equal to the length of the secret array.
     *  Each integer in the array lies in the interval [lowerBound, upperBound].
     *  The difference between each pair of consecutive integers of the array must be equal to the
     * difference between the respective pair of consecutive integers in the secret array. In other
     * words, let the secret array be [s[0], s[1],...s[n-1]]and let the analogous array be [a[0], a[1],...a[n-
     * 1]], then (a[i-1] - a[i]) must be equal to (s[i-1] - s[i]) for each i from 1 to n-1.
     * Given the value of the integers lowerBound and upperBound, inclusive, and the array of
     * differences between each pair of consecutive integers of the secret array, find the number of
     * arrays that are analogous to the secret array. If there is no array analogous to the secret array,
     * return 0.
     * For example:
     * consecutiveDifference = [-2, -1, -2, 5]
     * lowerBound=3
     * upperBound=10
     * The logic to create an analogous array starting from the lower bound is:
     * Start with a value of 3.
     * Subtract consecutiveDistances[0], 3 - (-2) = 5
     * Subtract consecutiveDistances[1], 5 - (-1) = 6
     * Subtract consecutiveDistances[2], 6 - (-2) = 8
     * Subtract consecutiveDistances[3], 8 - 5 = 3
     * Note that none of the values is out of bounds. All possible analogous arrays are:
     * Amazon Confidential
     * 4
     * [3, 5, 6, 8, 3]
     * [4, 6, 7, 9, 4]
     * [5, 7, 8, 10, 5]
     * The answer is 3.
     * Function Description
     * Complete the function countAnalogousArrays in the editor below.
     * @param difference
     * @return
     */
    static int contiguousSubarray(int[] difference, int lowerBound, int upperBound){

        if(lowerBound > upperBound || difference.length == 0){
            return 0;
        }
        int left = lowerBound, right = upperBound;

        while(left <= right){
            int mid = left + (right - left) / 2;

            if(validAnalogArray(mid, difference, lowerBound, upperBound)){
                right = mid -1;
            }else {
                left = mid + 1;
            }
        }

        // left has the highest
        return right - lowerBound + 1;
    }

    private static boolean validAnalogArray(int previous, int[] difference, int lowerBound, int upperBound){

        for (int diff : difference) {
            int curr = previous - diff;
            if (curr < lowerBound || curr > upperBound ) {
                return false;
            }
            previous = curr;
        }
        return true;

    }


    public static void main(String[] args) {
        int[] weights = {3, 2, 7, 6, 5};
        List<Integer> result = minimalHeaviestSetA(weights);

        System.out.println(result);

        testContiguousSubarray_SimpleCase();
        testContiguousSubarray_OutOfBounds();
        testContiguousSubarray_EmptyArray();
        testContiguousSubarray_SingleElementArray();
        testContiguousSubarray_LargeArray();

    }


    public static void testContiguousSubarray_SimpleCase() {
        int[] difference = {-2, -1, -2, 5};
        int lowerBound = 3;
        int upperBound = 10;
        int expected = 3;
        int result = contiguousSubarray(difference, lowerBound, upperBound);
        if (result == expected) {
            System.out.println("Test case 1 passed");
        } else {
            System.out.println("Test case 1 failed");
        }
    }

    public static void testContiguousSubarray_OutOfBounds() {
        int[] difference = {-2, -1, -2, 5};
        int lowerBound = 10;
        int upperBound = 3;
        int expected = 0;
        int result = contiguousSubarray(difference, lowerBound, upperBound);
        if (result == expected) {
            System.out.println("Test case 2 passed");
        } else {
            System.out.println("Test case 2 failed");
        }
    }

    public static void testContiguousSubarray_EmptyArray() {
        int[] difference = {};
        int lowerBound = 3;
        int upperBound = 10;
        int expected = 8;
        int result = contiguousSubarray(difference, lowerBound, upperBound);
        if (result == expected) {
            System.out.println("Test case 3 passed");
        } else {
            System.out.println("Test case 3 failed");
        }
    }

    public static void testContiguousSubarray_SingleElementArray() {
        int[] difference = {-2};
        int lowerBound = 3;
        int upperBound = 10;
        int expected = 7;
        int result = contiguousSubarray(difference, lowerBound, upperBound);
        if (result == expected) {
            System.out.println("Test case 4 passed");
        } else {
            System.out.println("Test case 4 failed");
        }
    }

    public static void testContiguousSubarray_LargeArray() {
        int[] difference = {-2, -1, -2, 5, -3, 4, -2, 1};
        int lowerBound = 3;
        int upperBound = 15;
        int expected = 5;
        int result = contiguousSubarray(difference, lowerBound, upperBound);
        if (result == expected) {
            System.out.println("Test case 5 passed");
        } else {
            System.out.println("Test case 5 failed");
        }
    }
}