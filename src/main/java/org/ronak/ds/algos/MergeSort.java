package org.ronak.ds.algos;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {7, 4, 2, 3, -1, 5};
        mergeSort(arr);
        
        for(int elem : arr) {
            System.out.print(elem + " ");
        }
    }
    
    public static void mergeSort(int[] arr) {
        // Base case: arrays of length 0 or 1 are already sorted
        if(arr.length < 2) {
            return;
        }
        
        // Divide step: split array into two roughly equal parts
        int mid = arr.length / 2;
        
        // Create temporary arrays for the two halves
        // leftArr will contain elements from index 0 to mid-1
        int[] leftArr = Arrays.copyOfRange(arr, 0, mid);
        // rightArr will contain elements from mid to the end
        int[] rightArr = Arrays.copyOfRange(arr, mid, arr.length);
        
        // Recursive step: sort each half
        mergeSort(leftArr);   // Sort left subarray
        mergeSort(rightArr);  // Sort right subarray
        
        // Combine step: merge the sorted halves back together
        merge(arr, leftArr, rightArr);
    }
    
    private static void merge(int[] arr, int[] leftArr, int[] rightArr) {
        // Three pointers: i for leftArr, j for rightArr, k for main arr
        int i = 0;  // Index for left subarray
        int j = 0;  // Index for right subarray
        int k = 0;  // Index for merged array
        
        // Compare elements from both arrays and place smaller one first
        while(i < leftArr.length && j < rightArr.length) {
            if(leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        
        // Copy any remaining elements from left array
        while(i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }
        
        // Copy any remaining elements from right array
        while(j < rightArr.length) {
            arr[k++] = rightArr[j++];
        }
    }
}