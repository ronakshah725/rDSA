package org.ronak.oop.design.decorator;

public class WhipDecorator implements Beverage {
    private static final double WHIP_COST = 1.0;
    private Beverage beverage;

    public WhipDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double calculateCost() {
        return WHIP_COST + beverage.calculateCost();
    }
}