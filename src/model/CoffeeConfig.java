package model;

import exceptions.ExceptionFactory;
import exceptions.ExceptionHandler;

import java.io.*;
import java.util.ArrayList;

// Define the CoffeeConfig class which implementing Serializable
public class CoffeeConfig implements Serializable {
    // Declare instance variables
    private ExceptionHandler exceptionHandler;
    private ExceptionFactory exceptionFactory = new ExceptionFactory();
    private String name;
    private double basePrice;
    private ArrayList<OptionSet> optionSets = new ArrayList<>();

    private String optionSetName;
    private String optionName = "";

    // Default constructor with exception handling
    public CoffeeConfig() {
        this.basePrice = basePrice;
        try {
            exceptionHandler = exceptionFactory.createException("Missing Name", this);
            throw exceptionHandler;
        } catch (ExceptionHandler exception) {
            exception.fixException();
        }
    }

    // Constructor with name and exception handling
    public CoffeeConfig(String name) {
        this.name = name;

        // Fix any missing base price, duplicate, or invalid optionSet/option
        try {
            exceptionHandler = exceptionFactory.createException("Missing Base Price", this);
            throw exceptionHandler;
        } catch (ExceptionHandler exception) {
            exception.fixException();
        }
        try {
            exceptionHandler = exceptionFactory.createException("Duplicate Option", this);
            throw exceptionHandler;
        } catch (ExceptionHandler exception) {
            exception.fixException();
        }
        try {
            exceptionHandler = exceptionFactory.createException("Invalid OptionSet", this);
            throw exceptionHandler;
        } catch (ExceptionHandler exception) {
            exception.fixException();
        }
        try {
            exceptionHandler = exceptionFactory.createException("Invalid Option", this);
            throw exceptionHandler;
        } catch (ExceptionHandler exception) {
            exception.fixException();
        }
    }

    // Constructor with name and basePrice
    public CoffeeConfig(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    // CRUD operations for OptionSet
    public void createOptionSet(String name) {
        // Create a new OptionSet and add it to the list if it doesn't exist
        if (getOptionSet(name) == null) {
            OptionSet optionSet = new OptionSet(name);
            optionSets.add(optionSet);
            optionSetName = optionSet.getName();
        }
    }

    // Getter for OptionSetName
    public String getOptionSetName() {
        return optionSetName;
    }

    // Read an OptionSet by index
    public void readOptionSet(int index) {
        // Display OptionSet details for a given index
        if (index >= 0 && index < optionSets.size()) {
            OptionSet optionSet = optionSets.get(index);
            System.out.println("OptionSet Name: " + optionSet.getName());
            System.out.println(optionSet);
        } else {
            System.out.println("Invalid index.");
        }
    }

    // Helper method to find the index of an OptionSet by name
    private int findIndex(String name) {
        // Find and return the index of an OptionSet by name
        for (int i = 0; i < optionSets.size(); i++) {
            if (optionSets.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    // Helper method to get one OptionSet by name
    private OptionSet getOptionSet(String name) {
        // Get and return an OptionSet by name
        for (int i = 0; i < optionSets.size(); i++) {
            if (optionSets.get(i).getName().equalsIgnoreCase(name)) {
                return optionSets.get(i);
            }
        }
        return null;
    }

    // Update the name of an OptionSet
    public void updateOptionSet(String oldName, String newName) {
        OptionSet optionSet = getOptionSet(oldName);
        if (optionSet != null) {
            optionSet.setName(newName);
            System.out.println("OptionSet updated successfully.");
        } else {
            System.out.println("OptionSet not found.");
        }
    }
 
    // Delete an OptionSet by name
    public void deleteOptionSet(String name) {
        OptionSet optionSet = getOptionSet(name);
        if (optionSet != null) {
            optionSets.remove(optionSet);
            System.out.println("OptionSet deleted successfully.");
        } else {
            System.out.println("OptionSet not found.");
        }
    }

    // Update the price of an option
    public void updateOptionPrice(String optionSetName, String optionName, double newPrice) {
        OptionSet optionSet = getOptionSet(optionSetName);
        if (optionSet != null) {
            optionSet.updateOptionPrice(optionName, newPrice);
            System.out.println("Option price updated successfully.");
        } else {
            System.out.println("OptionSet not found.");
        }
    } 

    // Create a new option
    public void createOption(String optionSetName, String optionName, double price) {
        OptionSet optionSet = getOptionSet(optionSetName);
        if (optionSet != null) {
            optionSet.createOption(optionName, price);
            optionName = optionName;
        } else {
            System.out.println("OptionSet not found.");
        }
    }

    // Getter for newOptionName
    public String getOptionName() {
        return optionName;
    }

    // Read details of an option
    public void readOption(String optionSetName, String optionName) {
        OptionSet optionSet = getOptionSet(optionSetName);
        if (optionSet != null) {
            optionSet.readOption(optionName);
        } else {
            System.out.println("OptionSet not found.");
        }
    }

    // Update the name of an option
    public void updateOption(String optionSetName, String oldOptionName, String optionName) {
        OptionSet optionSet = getOptionSet(optionSetName);
        if (optionSet != null) {
            optionSet.updateOption(oldOptionName, optionName);
        } else {
            System.out.println("OptionSet not found.");
        }
    }

    // Delete an option
    public void deleteOption(String optionSetName, String optionName) {
        OptionSet optionSet = getOptionSet(optionSetName);
        if (optionSet != null) {
            optionSet.deleteOption(optionName);
        } else {
            System.out.println("OptionSet not found.");
        }
    }

    // Serialization method
    public void serializeCoffeeConfigs(ArrayList<CoffeeConfig> configs, String fileName) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(configs);
        }
    }

    // Deserialization method
    public ArrayList<CoffeeConfig> deserializeCoffeeConfigs(String fileName) throws IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (ArrayList<CoffeeConfig>) objectInputStream.readObject();
        }
    }

    // Print function to display class details
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Coffee Configuration: ").append(name).append("\n").append(basePrice).append("\n");
        for (OptionSet optionSet : optionSets) {
            sb.append(optionSet).append("\n");
        }
        return sb.toString();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public ArrayList<OptionSet> getOptionSets() {
        return optionSets;
    }
}
