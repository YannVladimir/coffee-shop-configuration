package io;

import model.CoffeeConfig;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

import exceptions.ExceptionFactory;
import exceptions.ExceptionHandler;

public class CoffeeShopConfigReader {

    // Method to build a CoffeeConfig object from a configuration file
    public static CoffeeConfig buildCoffeeShopConfig(String filename) throws ExceptionHandler, IOException {
        // Initialize a CoffeeConfig object to store the configuration
        CoffeeConfig currentShopConfig = new CoffeeConfig();
        // Variable to store the current OptionSet name
        String currentOptionSetName = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            // Read each line from the configuration file
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Check for different types of configuration entries
                if (line.startsWith("Shop_name=")) {
                    processShopName(line.substring(10), currentShopConfig);
                } else if (line.startsWith("Base_price=")) {
                    processBasePrice(line.substring(11), currentShopConfig);
                } else if (line.startsWith("Option_set_name=")) {
                    currentOptionSetName = processOptionSetName(line.substring(16), currentShopConfig);
                } else if (line.startsWith("Options=")) {
                    processOptions(line.substring(8), currentOptionSetName, currentShopConfig);
                }
            }

        } catch (FileNotFoundException e) {
            handleFileNotFound(e, currentShopConfig);

        } catch (AccessDeniedException e) {
            handleAccessDenied(e, currentShopConfig);

        } catch (IOException e) {
            handleOtherIOException(e, currentShopConfig);
        }

        // Return the CoffeeConfig object with the built configuration
        return currentShopConfig;
    }

    // Method to process and set the shop name in CoffeeConfig
    private static void processShopName(String shopName, CoffeeConfig currentShopConfig) {
        currentShopConfig.setName(shopName);
    }

    // Method to process and set the base price in CoffeeConfig
    private static void processBasePrice(String basePrice, CoffeeConfig currentShopConfig) {
        currentShopConfig.setBasePrice(Double.parseDouble(basePrice));
    }

    // Method to process and set the OptionSet name in CoffeeConfig
    private static String processOptionSetName(String optionSetName, CoffeeConfig currentShopConfig) {
        currentShopConfig.createOptionSet(optionSetName);
        return optionSetName;
    }

    // Method to process and add options to the current OptionSet in CoffeeConfig
    private static void processOptions(String options, String optionSetName, CoffeeConfig currentShopConfig) {
        // Parse and add options to the current OptionSet
        String[] optionStrings = options.split(",");
        for (String optionString : optionStrings) {
            String[] optionParts = optionString.split(":");
            String optionName = optionParts[0];
            double optionPrice = Double.parseDouble(optionParts[1]);
            currentShopConfig.createOption(optionSetName, optionName, optionPrice);
        }
    }

    // Method to handle the FileNotFoundException
    private static void handleFileNotFound(FileNotFoundException e, CoffeeConfig currentShopConfig) throws ExceptionHandler {
        throw new ExceptionFactory().createException("File not found", currentShopConfig);
    }

    // Method to handle the AccessDeniedException
    private static void handleAccessDenied(AccessDeniedException e, CoffeeConfig currentShopConfig) throws ExceptionHandler {
        throw new ExceptionFactory().createException("File access denied", currentShopConfig);
    }

    // Method to handle other IOExceptions
    private static void handleOtherIOException(IOException e, CoffeeConfig currentShopConfig) throws ExceptionHandler {
        throw new ExceptionFactory().createException("Other I/O", currentShopConfig);
    }
}
