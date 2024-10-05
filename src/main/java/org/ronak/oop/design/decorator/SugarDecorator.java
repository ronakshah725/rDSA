package org.ronak.oop.design.decorator;

public class SugarDecorator implements Beverage {
    private static final double SUGAR_COST = 0.5;
    private Beverage beverage;

    public SugarDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double calculateCost() {
        return SUGAR_COST + beverage.calculateCost();
    }
}