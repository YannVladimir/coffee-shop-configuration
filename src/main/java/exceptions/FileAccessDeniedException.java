package exceptions;

import model.CoffeeConfig;
import wrapper.CoffeeShopConfigAPI;

// Custom exception class for handling file access denied exception
public class FileAccessDeniedException extends ExceptionHandler {
    
    // Constructor to initialize the exception with a message and the associated CoffeeConfig
    public FileAccessDeniedException(String message, CoffeeConfig coffeeConfig) {
        super(message, coffeeConfig);
    }

    // Override the fixException method to provide a custom fix for file access denied exception
    @Override
    public void fixException() {
        // Print an error message indicating that there is a file access denied exception
        System.out.println("File Access Denied");

        System.out.println("Building coffee shop configuration from the default configurations file, default_shop_configurations.txt");

        // Create a new instance of CoffeeShopConfigAPI to simulate creating a new coffee shop
        CoffeeShopConfigAPI coffeeShopConfigAPI = new CoffeeShopConfigAPI();

        // Simulate creating a new coffee shop using the default configurations file
        coffeeShopConfigAPI.configureCoffeeShop("default_shop_configurations.txt");
    }
}
