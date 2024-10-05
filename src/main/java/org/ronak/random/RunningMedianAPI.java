package org.ronak.random;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class RunningMedianAPI implements CustomArray {

    private final Queue<Integer> minHeap;
    private final Queue<Integer> maxHeap;

    RunningMedianAPI(){
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    @Override
    public void ingest(int x) {
        minHeap.offer(x);

        // pop smallest from min and add to max. minHeap [ 7, 8, 9], maxHeap = [6, 5, 3, 1]
        maxHeap.offer(minHeap.poll());

        if(maxHeap.size() > minHeap.size()){
            minHeap.offer(maxHeap.poll());
        }

    }

    void print(){
        System.out.print("Numbers: ");
        System.out.println(maxHeap.stream().sorted().toList() + "::" + minHeap.stream().sorted().toList());
    }

    @Override
    public double getRunningMedian() {
        int totalElems = minHeap.size() + maxHeap.size();
        if(totalElems == 0){
            return 0L;
        }

        return totalElems % 2 != 0 ? minHeap.peek() : ((maxHeap.peek() + minHeap.peek()) / 2.0);
    }

    public static void main(String[] args) {
        RunningMedianAPI medianAPI = new RunningMedianAPI();

        // Original elements
        medianAPI.ingest(2);
        medianAPI.print();
        System.out.println("Median: " + medianAPI.getRunningMedian());
        assert medianAPI.getRunningMedian() == 2 : "Expected median: 2";

        medianAPI.ingest(7);
        medianAPI.print();
        System.out.println("Median: " + medianAPI.getRunningMedian());
        assert medianAPI.getRunningMedian() == 4.5 : "Expected median: 4.5";

        medianAPI.ingest(5);
        medianAPI.print();
        System.out.println("Median: " + medianAPI.getRunningMedian());
        assert medianAPI.getRunningMedian() == 5 : "Expected median: 5";

        // Adding 15 more elements
        int[] additionalElements = {10, 12, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90};
        double[] expectedMedians = {6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0, 20.0, 21.0, 22.0, 23.0};

        for (int i = 0; i < additionalElements.length; i++) {
            medianAPI.ingest(additionalElements[i]);
            medianAPI.print();
            double currentMedian = medianAPI.getRunningMedian();
            System.out.println("Median: " + currentMedian);
            assert currentMedian == expectedMedians[i] : "Expected median: " + expectedMedians[i];
        }
    }
}

interface CustomArray{
    void ingest(int x);
    double getRunningMedian();
}

/***
 *
 *
 * ingest(2), median = 2
 * ingest(8), median = 5
 * ingest(4), median = 2.5
 *
 * Median divides into two, all elems smaller than median in one half and greater in other
 *
 * Naive: keep everything in an array, sort and find mind. O(n log n)
 *
 *
 */
