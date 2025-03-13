package com.vendor.model.drink;

import com.vendor.model.Ingredient;

import java.util.HashMap;
import java.util.Map;

public class MixedFruitDrink extends BlendedFruitDrink {
    public MixedFruitDrink(int size) {
        super("Mixed Fruit Drink", createRecipe(), size, 2.5);
    }

    private static Map<Ingredient, Double> createRecipe() {
        Map<Ingredient, Double> recipe = new HashMap<>();
        recipe.put(new Ingredient("Strawberries", 50, 0.1), 50.0);
        recipe.put(new Ingredient("Bananas", 60, 0.08), 60.0);
        recipe.put(new Ingredient("Ice", 30, 0.01), 30.0);
        recipe.put(new Ingredient("Condensed Milk", 20, 0.05), 20.0);
        recipe.put(new Ingredient("Sugar", 8, 0.02), 8.0);
        return recipe;
    }
}