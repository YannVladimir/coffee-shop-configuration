package exceptions;

import model.CoffeeConfig;

// Step 1: Define the ExceptionFactory class
public class ExceptionFactory {
    // Step 1.1: Declare instance variable
    private ExceptionHandler exceptionHandler;

    // Step 2: Method to create and return a specific exception based on the message
    public ExceptionHandler createException(String message, CoffeeConfig coffeeConfig) {
        // Step 2.1: Check the message and create an appropriate exception
        if (message.equalsIgnoreCase("Missing Base Price")) {
            exceptionHandler = new MissingBasePriceException(message, coffeeConfig);
        }
        if (message.equalsIgnoreCase("Missing Name")) {
            exceptionHandler = new MissingNameException(message, coffeeConfig);
        }
        if (message.equalsIgnoreCase("Duplicate Option")) {
            exceptionHandler = new DuplicateOptionSetException(message, coffeeConfig);
        }
        if (message.equalsIgnoreCase("Invalid OptionSet")) {
            exceptionHandler = new InvalidOptionException(message, coffeeConfig);
        }
        if (message.equalsIgnoreCase("Invalid Option")) {
            exceptionHandler = new InvalidOptionException(message, coffeeConfig);
        }
        if (message.equalsIgnoreCase("File not found")) {
            exceptionHandler = new FileNotFoundException(message, coffeeConfig);
        }
        if (message.equalsIgnoreCase("File access denied")) {
            exceptionHandler = new FileAccessDeniedException(message, coffeeConfig);
        }
        if (message.equalsIgnoreCase("Other I/O")) {
            exceptionHandler = new FileIOException(message, coffeeConfig);
        }
        
        // Step 2.2: Return the created exception
        return exceptionHandler;
    }
}
