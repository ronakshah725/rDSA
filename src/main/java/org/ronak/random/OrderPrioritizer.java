package org.ronak.random;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public class OrderPrioritizer {

    private final List<String> allOrders;
    private final PriorityQueue<Order> primeOrders;
    private final List<Order>  nonPrimeOrders;

    public OrderPrioritizer(List<String> allOrders){
        this.allOrders = allOrders;
        primeOrders = new PriorityQueue<>(getPrimeComparator());
        nonPrimeOrders = new ArrayList<>();
    }

    private static Comparator<Order> getPrimeComparator() {
        return (o1, o2) -> {
            int metadataComparison = o1.metadata.compareToIgnoreCase(o2.metadata);
            if (metadataComparison == 0) {
                return o1.idCode.compareToIgnoreCase(o2.idCode);
            }
            return metadataComparison;
        };
    }

    List<String> prioritize(){
        populateOrders();

        List<String> finalList = primeOrders.stream()
                .map(Order::toString)
                .collect(Collectors.toList());

        for(Order o: nonPrimeOrders){
            finalList.add(o.toString());
        }

        return finalList;
    }

    private void populateOrders() {
        for(String order : allOrders){
            Order o = new Order(order);
            if(o.isPrime()){
                primeOrders.add(o);
            }else{
                nonPrimeOrders.add(o);
            }
        }
    }

    static class Order {
        private final String order;
        private final String idCode;
        private final String metadata;

        @Getter
        boolean prime;

        public Order(String order){
            this.order = order;
            int firstSpace = order.indexOf(' ');
            this.idCode = order.substring(0, firstSpace);
            this.metadata = order.substring(firstSpace);
            this.prime = checkPrime(metadata);
        }

        private boolean checkPrime(String metadata) {
            for (char c: metadata.toCharArray()) {
                if(c == ' ') continue;
                if (c < 'a' || c > 'z') return false;
            }
            return true;
        }

        public String toString(){
            return order;
        }
    }
}
