package exceptions;

import model.CoffeeConfig;
import wrapper.CoffeeShopConfigAPI;

// Custom exception class for handling other I/O exceptions
public class FileIOException extends ExceptionHandler {
    
    // Constructor to initialize the exception with a message and the associated CoffeeConfig
    public FileIOException(String message, CoffeeConfig coffeeConfig) {
        super(message, coffeeConfig);
    }

    // Override the fixException method to provide a custom fix for other I/O exceptions
    @Override
    public void fixException() {
        // Print an error message indicating that there is an other I/O exception
        System.out.println("Other I/O Exception");

        System.out.println("Building coffee shop configuration from the default configurations file, default_shop_configurations.txt");

        // Create a new instance of CoffeeShopConfigAPI to simulate creating a new coffee shop
        CoffeeShopConfigAPI coffeeShopConfigAPI = new CoffeeShopConfigAPI();

        // Simulate creating a new coffee shop using the default configurations file
        coffeeShopConfigAPI.configureCoffeeShop("default_shop_configurations.txt");
    }
}
