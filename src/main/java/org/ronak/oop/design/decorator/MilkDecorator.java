package org.ronak.oop.design.decorator;

public class MilkDecorator implements Beverage {
    private static final double MILK_COST = 1.3;
    Beverage beverage;

    public MilkDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double calculateCost() {
        return MILK_COST + beverage.calculateCost();
    }
}
