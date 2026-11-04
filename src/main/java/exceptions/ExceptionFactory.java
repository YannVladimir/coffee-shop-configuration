package exceptions;

import model.CoffeeConfig;

// Step 1: Define the ExceptionFactory class
public class ExceptionFactory {
    // Step 1.1: Declare instance variable
    private ExceptionHandler exceptionHandler;

    // Step 2: Method to create and return a specific exception based on the message
    public ExceptionHandler createException(String message, CoffeeConfig coffeeConfig) {
        // Step 2.1: Check the message and create an appropriate exception
        // BUGFIX: these were previously separate "if" statements (not "else if"). That was
        // harmless only because every message string is distinct, but it also meant an
        // unrecognized message fell through all of them, leaving exceptionHandler unset
        // (null for a fresh factory instance) and causing a NullPointerException at the
        // caller's "throw" site instead of a clear error. Using else-if plus an explicit
        // fallback below makes the unsupported-message case fail predictably.
        if (message.equalsIgnoreCase("Missing Base Price")) {
            exceptionHandler = new MissingBasePriceException(message, coffeeConfig);
        } else if (message.equalsIgnoreCase("Missing Name")) {
            exceptionHandler = new MissingNameException(message, coffeeConfig);
        } else if (message.equalsIgnoreCase("Duplicate Option")) {
            exceptionHandler = new DuplicateOptionSetException(message, coffeeConfig);
        } else if (message.equalsIgnoreCase("Invalid OptionSet")) {
            exceptionHandler = new InvalidOptionException(message, coffeeConfig);
        } else if (message.equalsIgnoreCase("Invalid Option")) {
            exceptionHandler = new InvalidOptionException(message, coffeeConfig);
        } else if (message.equalsIgnoreCase("File not found")) {
            exceptionHandler = new FileNotFoundException(message, coffeeConfig);
        } else if (message.equalsIgnoreCase("File access denied")) {
            exceptionHandler = new FileAccessDeniedException(message, coffeeConfig);
        } else if (message.equalsIgnoreCase("Other I/O")) {
            exceptionHandler = new FileIOException(message, coffeeConfig);
        } else {
            throw new IllegalArgumentException("Unsupported exception type: " + message);
        }

        // Step 2.2: Return the created exception
        return exceptionHandler;
    }
}
