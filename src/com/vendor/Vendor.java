package com.vendor;

import com.vendor.model.*;
import com.vendor.model.drink.*;

import java.util.*;

public class Vendor implements InventoryManager {
    private List<Ingredient> inventory;
    private List<BlendedFruitDrink> drinks;
    private Map<String, Double> dailySales;

    public Vendor() {
        inventory = new ArrayList<>();
        drinks = new ArrayList<>();
        dailySales = new HashMap<>();

        inventory.add(new Ingredient("Strawberries", 1000, 0.1));
        inventory.add(new Ingredient("Bananas", 1200, 0.08));
        inventory.add(new Ingredient("Mangoes", 1400, 0.12));
        inventory.add(new Ingredient("Ice", 5000, 0.01));
        inventory.add(new Ingredient("Condensed Milk", 3000, 0.05));
        inventory.add(new Ingredient("Sugar", 1000, 0.02));

        drinks.add(new StrawberryDrink(300));
        drinks.add(new BananaDrink(300));
        drinks.add(new MangoDrink(300));
        drinks.add(new MixedFruitDrink(300));
    }

    public Ingredient getIngredientByName(String name) {
        return inventory.stream()
                .filter(ingredient -> ingredient.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found: " + name));
    }

    public void listInventory() {
        System.out.println("Current Inventory:");
        for (Ingredient ingredient : inventory) {
            System.out.println(ingredient);
        }
    }

    @Override
    public void reduceInventory(Map<Ingredient, Double> requiredIngredients) {
        for (Map.Entry<Ingredient, Double> entry : requiredIngredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            double requiredQuantity = entry.getValue();
            ingredient.reduceQuantity(requiredQuantity);
        }
    }

    @Override
    public boolean checkInventory(Map<Ingredient, Double> requiredIngredients) {
        for (Map.Entry<Ingredient, Double> entry : requiredIngredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            double requiredQuantity = entry.getValue();
            if (ingredient.getQuantity() < requiredQuantity) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void checkLowInventoryWarning() {
        for (Ingredient ingredient : inventory) {
            double requiredForFourDrinks = 0;
            for (BlendedFruitDrink drink : drinks) {
                requiredForFourDrinks += drink.calculateRequiredQuantity(ingredient) * 4;
            }
            if (ingredient.getQuantity() < requiredForFourDrinks) {
                System.out.println("Warning: Low inventory for " + ingredient.getName());
            }
        }
    }

    @Override
    public void addDailySale(String drinkName, int quantity, double totalPrice) {
        dailySales.put(drinkName, dailySales.getOrDefault(drinkName, 0.0) + totalPrice);
    }

    @Override
    public void printDailySalesReport() {
        System.out.println("Daily Sales Report:");
        for (Map.Entry<String, Double> entry : dailySales.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }
}