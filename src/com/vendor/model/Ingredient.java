package com.vendor.model;

public class Ingredient {
    private String name;
    private double quantity;
    private double costPerUnit;

    public Ingredient(String name, double quantity, double costPerUnit) {
        this.name = name;
        this.quantity = quantity;
        this.costPerUnit = costPerUnit;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getCostPerUnit() {
        return costPerUnit;
    }

    public void reduceQuantity(double amount) {
        if (amount <= quantity) {
            quantity -= amount;
        } else {
            throw new IllegalArgumentException("Not enough " + name + " in inventory.");
        }
    }

    @Override
    public String toString() {
        return name + ": " + quantity + " units, Cost: $" + costPerUnit + " per unit";
    }
}