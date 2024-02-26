package exceptions;

import model.CoffeeConfig;

// Define the MissingNameException class extending ExceptionHandler
public class MissingNameException extends ExceptionHandler {
    // Constructor with message and CoffeeConfig
    public MissingNameException(String message, CoffeeConfig coffeeConfig) {
        super(message, coffeeConfig);
    }

    //Now let me Override the fixException method to provide a solution
    @Override
    public void fixException() {
        // Set a default coffee name to Yann's coffee when there is a missing name
        this.getCoffeeConfig().setName("Default Coffee Name");
    }
}
