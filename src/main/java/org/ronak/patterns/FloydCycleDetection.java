package org.ronak.patterns;

import org.ronak.ds.ListNode;

/**
 * Detects a cycle in a linked list
 */
public class FloydCycleDetection implements LCPattern {
    private ListNode<Integer> head;

    @Override
    public void init() {
        // Create a sample linked list with a cycle
        ListNode<Integer> node1 = new ListNode<>(1, null);
        ListNode<Integer> node2 = new ListNode<>(2, null);
        ListNode<Integer> node3 = new ListNode<>(3, null);
        ListNode<Integer> node4 = new ListNode<>(4, null);
        ListNode<Integer> node5 = new ListNode<>(5, null);

        // Link the nodes
        node1 = new ListNode<>(1, node2);
        node2 = new ListNode<>(2, node3);
        node3 = new ListNode<>(3, node4);
        node4 = new ListNode<>(4, node5);
//        node5 = new Node<>(5, null); // Create a cycle by pointing node5 to node3

        // Set head to the first node
        head = node1;
    }

    @Override
    public void demo() {
        if (detectCycle(head)) {
            System.out.println("Cycle found");
        } else {
            System.out.println("No cycle found");
        }
    }

    public boolean detectCycle(ListNode<Integer> head) {
        // 1-2-3-4-5->3
        // 1-2-3-4-5->null
        ListNode<Integer> slow = head, fast = head;

        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();

            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
