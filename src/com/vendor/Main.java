package com.vendor;

import com.vendor.model.drink.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Vendor vendor = new Vendor();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Sell a drink");
            System.out.println("2. Print current inventory");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    sellDrink(vendor, scanner);
                    break;
                case 2:
                    vendor.listInventory();
                    break;
                case 3:
                    vendor.printDailySalesReport();
                    System.out.println("Closing system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private static void sellDrink(Vendor vendor, Scanner scanner) {
        System.out.println("\nSelect a drink:");
        System.out.println("1. Strawberry Drink");
        System.out.println("2. Banana Drink");
        System.out.println("3. Mango Drink");
        System.out.println("4. Mixed Fruit Drink");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > 4) {
            System.out.println("Invalid choice.");
            return;
        }

        System.out.println("\nSelect size:");
        System.out.println("1. Small (200ml)");
        System.out.println("2. Medium (300ml)");
        System.out.println("3. Large (400ml)");
        System.out.print("Enter size: ");
        int sizeChoice = scanner.nextInt();

        int size = switch (sizeChoice) {
            case 1 -> 200;
            case 2 -> 300;
            case 3 -> 400;
            default -> {
                System.out.println("Invalid size, using Medium (300ml) by default.");
                yield 300;
            }
        };

        BlendedFruitDrink drink = switch (choice) {
            case 1 -> new StrawberryDrink(size);
            case 2 -> new BananaDrink(size);
            case 3 -> new MangoDrink(size);
            case 4 -> new MixedFruitDrink(size);
            default -> null;
        };

        if (drink == null) return;

        System.out.print("Enter quantity to sell: ");
        int quantity = scanner.nextInt();

        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }

        drink.sell(vendor, quantity);
        vendor.checkLowInventoryWarning();
    }
}
