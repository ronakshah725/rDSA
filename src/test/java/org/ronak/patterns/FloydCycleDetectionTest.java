package org.ronak.patterns;

import org.junit.jupiter.api.Test;
import org.ronak.ds.ListNode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FloydCycleDetectionTest {

    @Test
    public void testDetectCycle() {
        FloydCycleDetection floydCycleDetection = new FloydCycleDetection();


        // Create a linked list with no cycle
        ListNode<Integer> node1 = new ListNode<>(1, null);
        ListNode<Integer> node2 = new ListNode<>(2, null);
        ListNode<Integer> node3 = new ListNode<>(3, null);
        node1.setNext(node2);
        node2.setNext(node3);

        assertFalse(floydCycleDetection.detectCycle(node1));

        // Create a linked list with a cycle
        ListNode<Integer> node4 = new ListNode<>(4, null);
        ListNode<Integer> node5 = new ListNode<>(5, null);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node3); // Cycle here

        assertTrue(floydCycleDetection.detectCycle(node1));
    }
}