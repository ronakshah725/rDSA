package org.ronak.random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class OrderPrioritizerTest {

    @Test
    void validPrimeOrder(){
        OrderPrioritizer.Order nonPrime = new OrderPrioritizer.Order("zld 93 12");
        OrderPrioritizer.Order prime = new OrderPrioritizer.Order("10a echo show");

        Assertions.assertTrue(prime.isPrime());
        Assertions.assertFalse(nonPrime.isPrime());
    }


    @Test
    void prioritizeAllPrime() {

        List<String> orders = new ArrayList<>();
        orders.add("zld 93 12");
        orders.add("fp kindle book");
        orders.add("10a echo show");
        orders.add("17g 12 25 6");
        orders.add("ab1 kindle book");
        orders.add("125 echo dot second generation");

        List<String> expectedOrders = new ArrayList<>();
        expectedOrders.add("125 echo dot second generation");
        expectedOrders.add("10a echo show");
        expectedOrders.add("ab1 kindle book");
        expectedOrders.add("fp kindle book");
        expectedOrders.add("zld 93 12");
        expectedOrders.add("17g 12 25 6");

        OrderPrioritizer prioritizer = new OrderPrioritizer(orders);
        List<String> prioritizedOrders = prioritizer.prioritize();
        Assertions.assertEquals(prioritizedOrders, expectedOrders);
    }

    @Test
    void prioritizeOnlyNonPrime() {
        List<String> orders = new ArrayList<>();
        orders.add("zld 93 12");
        orders.add("17g 12 25 6");
        orders.add("17a 13 25 6");

        OrderPrioritizer prioritizer = new OrderPrioritizer(orders);
        List<String> prioritizedOrders = prioritizer.prioritize();
        Assertions.assertEquals(prioritizedOrders, orders);
    }
}