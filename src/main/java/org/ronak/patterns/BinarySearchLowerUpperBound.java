package org.ronak.patterns;

public class BinarySearchLowerUpperBound {

    public static int searchLowerBound(int[] nums, double target) {

        int left = 0, right = nums.length - 1, answer = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            // left stores find potential answers.
            if(nums[mid] >= target){
                answer = mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        return answer;
    }

    public static int searchUpperBound(int[] nums, double target) {

        int left = 0, right = nums.length - 1, answer = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            // left stores find potential answers.
            if(nums[mid] <= target){
                answer = mid;
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }

        return answer;
    }
}
