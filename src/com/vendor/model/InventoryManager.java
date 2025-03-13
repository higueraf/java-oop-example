package com.vendor.model;

import java.util.Map;

public interface InventoryManager {
    void reduceInventory(Map<Ingredient, Double> requiredIngredients);
    boolean checkInventory(Map<Ingredient, Double> requiredIngredients);
    void checkLowInventoryWarning();
    void addDailySale(String drinkName, int quantity, double totalPrice);
    void printDailySalesReport();
}