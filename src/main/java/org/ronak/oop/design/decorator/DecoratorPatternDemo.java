package org.ronak.oop.design.decorator;

import java.util.ArrayList;
import java.util.List;

public class DecoratorPatternDemo {

    public static void main(String[] args) {
        CoffeeShop coffeeShop = new SimpleCoffeeShop();
        List<Beverage> orderedBeverages = selectDrinks();
        double cost = coffeeShop.getTotalCost(orderedBeverages);
        System.out.println(cost);
    }

    private static List<Beverage> selectDrinks() {
        List<Beverage> drinks = new ArrayList<>();

        // Create a HouseBlend coffee with milk and sugar
        Beverage houseBlend = new HouseBlend();
        houseBlend = new MilkDecorator(houseBlend);
        houseBlend = new SugarDecorator(houseBlend);
        drinks.add(houseBlend);

        // Create a DarkRoast coffee with whip and no condiments
        Beverage darkRoast = new DarkRoast();
        darkRoast = new ChocolateDecorator(darkRoast);
        darkRoast = new WhipDecorator(darkRoast);
        drinks.add(darkRoast);

        // Create a Decaf coffee with milk, sugar, and vanilla syrup
        Beverage decaf = new Decaf();
        decaf = new MilkDecorator(decaf);
        decaf = new SugarDecorator(decaf);
        decaf = new VanillaSyrupDecorator(decaf);
        drinks.add(decaf);

        return drinks;
    }


}

/***
 *
 * Design a coffee shop
 * 1. User can select multiple types of base beverages:
 * HouseBlend, DarkRoast, Decaf, Esspresso
 * 2. User can add condiments: steamed milk, soy, chocolate
 * 3. Top off with Milk
 *
 *
 * Menu steps per drink :
 * 1. select Base beverage
 * 2. Optionally select one or more condiments
 * 3. Optionally top off with milk
 *
 *
 */
