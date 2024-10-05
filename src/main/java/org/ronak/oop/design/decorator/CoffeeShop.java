package org.ronak.oop.design.decorator;

import java.util.List;

public interface CoffeeShop {

    double getTotalCost(List<Beverage> orderedBeverages);
}
