package org.ronak.oop.design.decorator;

import java.util.List;

public class SimpleCoffeeShop implements CoffeeShop {


    @Override
    public double getTotalCost(List<Beverage> orderedBeverages) {
        double total = 0.0;

        for (Beverage b : orderedBeverages) {
            total += b.calculateCost();
        }
        return total;
    }
}
