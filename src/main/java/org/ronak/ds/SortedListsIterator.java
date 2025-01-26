package org.ronak.ds;

import java.util.*;

/***
 *
 *
 * class SortedIterator implements Iterator {
 * public SortedIterator(List<List> lists) {}
 * public boolean hasNext() {}
 * public Integer next() {}
 *
 *
 * }
 */

public class SortedListsIterator implements Iterator<Integer> {
    List<List<Integer>> lists;
    PriorityQueue<ListItem> minHeap;

    public SortedListsIterator(List<List<Integer>> lists) {
        this.lists = lists;
        minHeap = new PriorityQueue<>(Comparator.comparingInt(i -> i.value));
        initializeHeap();
    }

    public static void main(String[] args) {
        List<Integer> list1 = List.of(2, 3, 8);
        List<Integer> list2 = List.of(5, 7);
        List<Integer> list3 = List.of(1, 4, 5);
        List<Integer> list4 = List.of(4);

        List<List<Integer>> sortedLists = new ArrayList<>();
        sortedLists.add(list1);
        sortedLists.add(list2);
        sortedLists.add(list3);
        sortedLists.add(list4);

        SortedListsIterator listsIterator = new SortedListsIterator(sortedLists);

        while (listsIterator.hasNext()) {
            System.out.printf("HasNext: true, NextItem:%s%n", listsIterator.next());
        }
    }

    void initializeHeap() {
        for (List<Integer> list : lists) {
            if (!list.isEmpty()) {
                ListIterator<Integer> iterator = list.listIterator();
                int firstValue = iterator.next();

                minHeap.offer(new ListItem(firstValue, iterator));
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !minHeap.isEmpty();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new IllegalArgumentException("No next");
        }

        // Steps:
        // 1. Remove min from minHeap
        // 2. Add next element from list
        // 3. Add listIterator to minHeap

        ListItem minItem = minHeap.poll();
        if (minItem != null && minItem.iterator.hasNext()) {
            minHeap.offer(new ListItem(minItem.iterator.next(), minItem.iterator));
        }

        return minItem.value;
    }

    static class ListItem {
        int value;
        ListIterator<Integer> iterator;

        public ListItem(int value, ListIterator<Integer> iterator) {
            this.value = value;
            this.iterator = iterator;
        }
    }
}
