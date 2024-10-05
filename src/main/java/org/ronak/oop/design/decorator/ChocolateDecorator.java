package org.ronak.oop.design.decorator;

public class ChocolateDecorator implements Beverage {
    private static final double CHOCO_COST = 2.0;
    Beverage beverage;

    public ChocolateDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double calculateCost() {
        return CHOCO_COST + beverage.calculateCost();
    }
}
