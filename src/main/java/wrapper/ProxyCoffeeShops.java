package wrapper;

import model.CoffeeConfig;
import io.CoffeeShopConfigReader;

import java.io.IOException;
import java.util.LinkedHashMap;


import exceptions.ExceptionHandler;

// Define the ProxyCoffeeShops abstract class
public abstract class ProxyCoffeeShops {
    // Declare a static LinkedHashMap to store coffee shop configurations
    public static LinkedHashMap<String, CoffeeConfig> configs = new LinkedHashMap<>();

    // Method to create a new coffee shop with the provided configuration
    //
    // BUGFIX (concurrency): every method below used to be declared "synchronized" at the
    // instance level (synchronized on "this"). "configs" is a *static* field shared by every
    // ProxyCoffeeShops/CoffeeShopConfigAPI instance, so synchronizing on "this" only protects
    // callers that happen to share a single instance (as MultipleUsersTestDriver does by luck).
    // Any code that creates more than one CoffeeShopConfigAPI (which the class is clearly
    // designed to allow) would get no real mutual exclusion on the shared map and could corrupt
    // it under concurrent access. Synchronizing on "configs" itself fixes that for every caller.
    public void createCoffeeShop(String coffeeShopName, CoffeeConfig coffeeConfig) {
        synchronized (configs) {
            // Put the coffeeConfig in the LinkedHashMap using the coffeeShopName as the key
            configs.put(coffeeConfig.getName(), coffeeConfig);
        }
    }

    // Stub implementation for configuring a coffee shop from a file
    public void configureCoffeeShop(String filename) {
        System.out.println("Building coffee shop configuration from file: " + filename);

        try {
            // Read all the shop configurations as a list of objects
            CoffeeConfig shopConfigs = CoffeeShopConfigReader.buildCoffeeShopConfig(filename);

            createCoffeeShop(shopConfigs.getName(), shopConfigs);

            System.out.println("Read applied successfully.");

        } catch (ExceptionHandler e) {
            // Handle the custom exception
            e.fixException();

        } catch (IOException e) {
            // Handle other I/O exceptions
            e.printStackTrace();
        }
    }

    // Method to print the details of a specific coffee shop
    public void printCoffeeShop(String coffeeShopName) {
        CoffeeConfig coffeeConfig;
        synchronized (configs) {
            // Get the coffeeConfig from the LinkedHashMap using the coffeeShopName
            coffeeConfig = configs.get(coffeeShopName);
        }
        // Print the details of the coffeeConfig or a message if not found
        if (coffeeConfig != null) {
            System.out.println(coffeeConfig);
        } else {
            System.out.println("Coffee shop with name '" + coffeeShopName + "' not found.");
        }
    }

    // Method to update the name of an OptionSet for a specific coffee shop
    public void updateOptionSetName(String coffeeShopName, String optionSetName, String newName) {
        synchronized (configs) {
            // Get the coffeeConfig from the LinkedHashMap using the coffeeShopName
            CoffeeConfig coffeeConfig = configs.get(coffeeShopName);
            // Update the OptionSet name or print a message if the coffee shop is not found
            if (coffeeConfig != null) {
                coffeeConfig.updateOptionSet(optionSetName, newName);
                System.out.println("OptionSet name updated successfully for coffee shop: " + coffeeShopName);
            } else {
                System.out.println("Coffee shop with name '" + coffeeShopName + "' not found.");
            }
        }
    }

    // Method to update the base price of a specific coffee shop
    public void updateBasePrice(String coffeeShopName, double newPrice) {
        synchronized (configs) {
            // Step 6.1: Get the coffeeConfig from the LinkedHashMap using the coffeeShopName
            CoffeeConfig coffeeConfig = configs.get(coffeeShopName);
            // Step 6.2: Update the base price or print a message if the coffee shop is not found
            if (coffeeConfig != null) {
                coffeeConfig.setBasePrice(newPrice);
                System.out.println("Base price updated successfully for coffee shop: " + coffeeShopName);
            } else {
                System.out.println("Coffee shop with name '" + coffeeShopName + "' not found.");
            }
        }
    }

    // Method to update the price of an Option for a specific coffee shop
    public void updateOptionPrice(String coffeeShopName, String optionSetName, String optionName, double newPrice) {
        synchronized (configs) {
            // Step 7.1: Get the coffeeConfig from the LinkedHashMap using the coffeeShopName
            CoffeeConfig coffeeConfig = configs.get(coffeeShopName);
            // Step 7.2: Update the Option price or print a message if the coffee shop is not found
            if (coffeeConfig != null) {
                coffeeConfig.updateOptionPrice(optionSetName, optionName, newPrice);
                System.out.println("Option price updated successfully for coffee shop: " + coffeeShopName);
            } else {
                System.out.println("Coffee shop with name '" + coffeeShopName + "' not found.");
            }
        }
    }

    // Method to delete CoffeeShop
    public void deleteCoffeeShop(String coffeeShopName) {
        CoffeeConfig removedConfig;
        synchronized (configs) {
            // Remove the shop from config hash map
            removedConfig = configs.remove(coffeeShopName);
        }

        // Check if the removed value is null
        if (removedConfig == null) {
            System.out.println("Cannot delete Coffee shop with name '" + coffeeShopName + "' as it is not found.");
        } else {
            System.out.println("Coffee shop '" + coffeeShopName + "' removed successfully.");
        }
    }

}
