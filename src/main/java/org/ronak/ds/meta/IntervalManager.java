package org.ronak.ds.meta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalManager {
    private List<int[]> mergedIntervals;

    public IntervalManager() {
        mergedIntervals = new ArrayList<>();
    }

    public static void main(String[] args) {
        IntervalManager manager = new IntervalManager();
        manager.add(1, 3);
        manager.add(5, 7);
        manager.add(2, 6);  // This will merge [1,3] and [5,7] into [1,7]

        List<int[]> result = manager.query();
        for (int[] interval : result) {
            System.out.println(Arrays.toString(interval));
        }
    }

    // Function to add an interval and merge overlapping intervals
    public void add(int start, int end) {
        int[] interval = new int[]{start, end};
        if (mergedIntervals.isEmpty()) {
            mergedIntervals.add(interval);
            return;
        }

        int insertIndex = searchMergePosition(interval);
        boolean overlap = true;

        while (true) {
            int[] intervalCurr = mergedIntervals.get(insertIndex++);
            if (interval[0] > intervalCurr[1]) {
                break;
            }
            intervalCurr[1] = Math.max(intervalCurr[1], end);
        }
    }

    // Function to query and return the merged intervals
    public List<int[]> query() {
        return mergedIntervals;
    }

    private int searchMergePosition(int[] target) {
        int left = 0, right = mergedIntervals.size() - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            int[] midInterval = mergedIntervals.get(mid);

            if (target[0] <= midInterval[1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
