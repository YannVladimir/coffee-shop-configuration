
package exceptions;

import model.CoffeeConfig;
import wrapper.CoffeeShopConfigAPI;

// Define the InvalidOptionException class extending ExceptionHandler
public class MissingOptionPriceException extends ExceptionHandler {
    // Constructor with message and CoffeeConfig
    public MissingOptionPriceException(String message, CoffeeConfig coffeeConfig) {
        super(message, coffeeConfig);
    }

    // Override the fix method to provide a solution
    @Override
    public void fixException() {
        // Retrieve the coffeeConfig and the required information
        CoffeeConfig coffeeConfig = getCoffeeConfig();
        String optionSetName = coffeeConfig.getOptionSetName();

        // Check if the optionSetName is not null and newOptionName is empty
        if (optionSetName != null && coffeeConfig.getOptionName().isEmpty()) {
            // Create a default option with name "DefaultOption" and price 500
            coffeeConfig.createOption(optionSetName, "DefaultOption", 500);
        }
    }
}
