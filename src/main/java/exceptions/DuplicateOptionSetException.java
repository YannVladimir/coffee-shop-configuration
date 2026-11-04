package exceptions;

import model.CoffeeConfig;
import wrapper.CoffeeShopConfigAPI;

import java.util.LinkedHashMap;

// Define the DuplicateOptionSetException class extending ExceptionHandler
public class DuplicateOptionSetException extends ExceptionHandler {
    // Constructor with message and CoffeeConfig
    public DuplicateOptionSetException(String message, CoffeeConfig coffeeConfig) {
        super(message, coffeeConfig);
    }

    // Step 2: Override the fix method to provide a solution
    @Override
    public void fixException() {
        // Step 2.1: Retrieve the coffeeConfig and the existing configs from CoffeeShopConfigAPI
        CoffeeConfig coffeeConfig = getCoffeeConfig();
        LinkedHashMap<String, CoffeeConfig> configs = CoffeeShopConfigAPI.configs;
        // Step 2.2: Get the original name of the duplicate OptionSet
        String originalName = coffeeConfig.getOptionSetName();

        // Step 2.3: Generate a unique name for the new OptionSet
        int counter = 1;
        String newName;
        do {
            newName = originalName + counter;
            counter++;
        } while (configs.containsKey(newName));

        // Step 2.4: Create the new OptionSet with the unique name
        coffeeConfig.createOptionSet(newName);
    }
}
