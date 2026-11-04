package exceptions;

import model.CoffeeConfig;
import wrapper.CoffeeShopConfigAPI;

// Step 1: Define the ExceptionHandler abstract class
public abstract class ExceptionHandler extends Exception {
    // Step 1.1: Declare instance variable
    private CoffeeConfig coffeeConfig;

    // Step 1.2: Constructor with exception message and CoffeeConfig
    public ExceptionHandler(String message, CoffeeConfig coffeeConfig) {
        super(message);
        this.coffeeConfig = coffeeConfig;
    }

    // Step 2: Getter and setter for CoffeeConfig
    public CoffeeConfig getCoffeeConfig() {
        return coffeeConfig;
    }

    public void setCoffeeConfig(CoffeeConfig coffeeConfig) {
        this.coffeeConfig = coffeeConfig;
    }

    // Step 3: Abstract method to be implemented by subclasses
    public abstract void fixException();
}
