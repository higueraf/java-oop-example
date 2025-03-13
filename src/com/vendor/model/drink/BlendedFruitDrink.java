package com.vendor.model.drink;

import com.vendor.Vendor;
import com.vendor.model.InventoryManager;
import com.vendor.model.Sellable;
import com.vendor.model.Ingredient;

import java.util.HashMap;
import java.util.Map;

public abstract class BlendedFruitDrink implements Sellable {
    private String name;
    private Map<Ingredient, Double> recipe;
    private int size;
    private double basePrice;

    public BlendedFruitDrink(String name, Map<Ingredient, Double> recipe, int size, double basePrice) {
        this.name = name;
        this.recipe = recipe;
        this.size = size;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public Map<Ingredient, Double> getRecipe() {
        return recipe;
    }

    public int getSize() {
        return size;
    }

    @Override
    public double getPrice() {
        return basePrice * (size / 300.0);
    }

    public double calculateRequiredQuantity(Ingredient ingredient) {
        return recipe.getOrDefault(ingredient, 0.0) * (size / 100.0);
    }

    @Override
    public void sell(InventoryManager inventoryManager, int quantity) {
        Map<Ingredient, Double> requiredIngredients = new HashMap<>();

        for (Map.Entry<Ingredient, Double> entry : recipe.entrySet()) {
            String ingredientName = entry.getKey().getName(); // Usa el nombre
            Ingredient ingredient = ((Vendor) inventoryManager).getIngredientByName(ingredientName); // Obtén el ingrediente del inventario
            double requiredQuantity = entry.getValue() * (size / 300.0) * quantity;
            requiredIngredients.put(ingredient, requiredQuantity);
        }

        if (!inventoryManager.checkInventory(requiredIngredients)) {
            System.out.println("Sale denied: Not enough ingredients to make " + quantity + " " + name);
            return;
        }

        inventoryManager.reduceInventory(requiredIngredients);
        double totalPrice = getPrice() * quantity;
        inventoryManager.addDailySale(name + " (" + getSizeDescription() + ")", quantity, totalPrice);
        System.out.println("Sold " + quantity + " " + name + " (" + getSizeDescription() + ") for $" + totalPrice);
    }


    private String getSizeDescription() {
        if (size == 200) {
            return "Small";
        } else if (size == 300) {
            return "Medium";
        } else if (size == 400) {
            return "Large";
        } else {
            return "Custom Size";
        }
    }
}