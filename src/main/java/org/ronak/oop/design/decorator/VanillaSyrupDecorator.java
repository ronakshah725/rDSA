package org.ronak.oop.design.decorator;

public class VanillaSyrupDecorator implements Beverage {
    private static final double VANIALLA_COST = 1.0;
    Beverage beverage;

    public VanillaSyrupDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double calculateCost() {
        return VANIALLA_COST + beverage.calculateCost();
    }
}