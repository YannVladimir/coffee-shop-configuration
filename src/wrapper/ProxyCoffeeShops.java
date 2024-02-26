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
    public synchronized void createCoffeeShop(String coffeeShopName, CoffeeConfig coffeeConfig) {
    	
	        // Put the coffeeConfig in the LinkedHashMap using the coffeeShopName as the key
	        configs.put(coffeeConfig.getName(), coffeeConfig);
    	
    }

    // Stub implementation for configuring a coffee shop from a file
    
 
    public synchronized void configureCoffeeShop(String filename) {
    	
	        System.out.println("Building coffee shop configuration from file: " + filename);
	
	        try {
	            // Read all the shop configurations as a list of objects
	            CoffeeConfig shopConfigs = CoffeeShopConfigReader.buildCoffeeShopConfig(filename);
	
	            createCoffeeShop(shopConfigs.getName(), shopConfigs);
	            //configs.put(shopConfigs.getName(), shopConfigs);
	            
	
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
    public synchronized void printCoffeeShop(String coffeeShopName) {
        // Get the coffeeConfig from the LinkedHashMap using the coffeeShopName
        CoffeeConfig coffeeConfig = configs.get(coffeeShopName);
        // Print the details of the coffeeConfig or a message if not found
        if (coffeeConfig != null) {
            System.out.println(coffeeConfig);
        } else {
            System.out.println("Coffee shop with name '" + coffeeShopName + "' not found.");
        }
    }

    // Method to update the name of an OptionSet for a specific coffee shop
    public synchronized void updateOptionSetName(String coffeeShopName, String optionSetName, String newName) {
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

    // Method to update the base price of a specific coffee shop
    public synchronized void updateBasePrice(String coffeeShopName, double newPrice) {
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

    // Method to update the price of an Option for a specific coffee shop
    public synchronized void updateOptionPrice(String coffeeShopName, String optionSetName, String optionName, double newPrice) {
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
    
    
    // Method to delete CoffeeShop
    public synchronized void deleteCoffeeShop(String coffeeShopName) {
        // Remove the shop from config hash map
        CoffeeConfig removedConfig = configs.remove(coffeeShopName);

        // Check if the removed value is null
        if (removedConfig == null) {
            System.out.println("Cannot delete Coffee shop with name '" + coffeeShopName + "' as it is not found.");
        } else {
            System.out.println("Coffee shop '" + coffeeShopName + "' removed successfully.");
        }
    }

}
