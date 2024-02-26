package exceptions;

import model.CoffeeConfig;

import java.util.concurrent.ThreadLocalRandom;

// Step 1: Define the MissingBasePriceException class extending ExceptionHandler
public class MissingBasePriceException extends ExceptionHandler {
    // Step 1.1: Constructor with message and CoffeeConfig
    public MissingBasePriceException(String message, CoffeeConfig coffeeConfig) {
        super(message, coffeeConfig);
    }

    // Override the fixException method to provide a solution
    @Override
    public void fixException() {
         // Set the default base price to 1000.0
        this.getCoffeeConfig().setBasePrice(1000.0);
    }
}
