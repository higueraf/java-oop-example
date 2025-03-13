package com.vendor.model;

public interface Sellable {
    void sell(InventoryManager inventoryManager, int quantity);
    double getPrice();
}