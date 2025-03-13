package com.vendor.model.drink;

import com.vendor.model.Ingredient;

import java.util.HashMap;
import java.util.Map;

public class MangoDrink extends BlendedFruitDrink {
    public MangoDrink(int size) {
        super("Mango Drink", createRecipe(), size, 2.4);
    }

    private static Map<Ingredient, Double> createRecipe() {
        Map<Ingredient, Double> recipe = new HashMap<>();
        recipe.put(new Ingredient("Mangoes", 140, 0.12), 140.0);
        recipe.put(new Ingredient("Ice", 30, 0.01), 30.0);
        recipe.put(new Ingredient("Condensed Milk", 20, 0.05), 20.0);
        recipe.put(new Ingredient("Sugar", 8, 0.02), 8.0);
        return recipe;
    }
}