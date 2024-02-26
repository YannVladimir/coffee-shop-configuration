package exceptions;

import model.CoffeeConfig;
import wrapper.CoffeeShopConfigAPI;

// Custom exception class for handling file not found exceptions
public class FileNotFoundException extends ExceptionHandler {
    
    // Constructor to initialize the exception with a message and the associated CoffeeConfig
    public FileNotFoundException(String message, CoffeeConfig coffeeConfig) {
        super(message, coffeeConfig);
    }

    // Override the fixException method to provide a custom fix for the file not found exception
    @Override
    public void fixException() {
        // Print an error message indicating that the file was not found
    	System.out.println();
        System.out.println("****File Not Found EXCEPTION CAUGHT****");
        System.out.println();
        System.out.println("****Now Handling the excepiton***");
        System.out.println("Building coffee shop configuration from the default configurations file, default_shop_configurations.txt");

        // Create a new instance of CoffeeShopConfigAPI to simulate creating a new coffee shop
        CoffeeShopConfigAPI coffeeShopConfigAPI = new CoffeeShopConfigAPI();

        // Simulate creating a new coffee shop using the default configurations file
        coffeeShopConfigAPI.configureCoffeeShop("default_shop_configurations.txt");
    }
}
