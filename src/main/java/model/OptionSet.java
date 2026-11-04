package model;

import java.io.Serializable;
import java.util.ArrayList;

// Define the OptionSet class implementing Serializable
class OptionSet implements Serializable {
    // Declare instance variables
    private String name;
    private ArrayList<Option> options = new ArrayList<>();

    // Constructor with name
    public OptionSet(String name) {
        this.name = name;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // CRUD operations for options
    public void createOption(String optionName, double price) {
        // Create a new Option and add it to the list if it doesn't exist
        if (readOption(optionName) == null) {
            Option option = new Option(optionName);
            option.setPrice(price);
            options.add(option);
            // Add option to the map (comment suggests this operation but not implemented)
        }
    }

    public Option readOption(String name) {
        // Find and return an Option by name
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).getOptionName().equalsIgnoreCase(name)) {
                return options.get(i);
            }
        }
        return null;
    }

    public void updateOption(String oldOptionName, String optionName) {
        // Update the name of an existing Option
        Option option = readOption(oldOptionName);
        if (option != null) {
            option.setOptionName(optionName);
        }
    }

    public void deleteOption(String name) {
        // Delete an Option by name
        Option option = readOption(name);
        if (option != null) {
            options.remove(option);
            // Remove option from the map (comment suggests this operation but not implemented)
            System.out.println("Option deleted successfully.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void updateOptionPrice(String optionName, double newPrice) {
        // Update the price of an existing Option
        Option option = readOption(optionName);
        if (option != null) {
            option.setPrice(newPrice);
            System.out.println("Option price updated successfully.");
        } else {
            System.out.println("Option not found.");
        }
    }

    // Getters and setters
    public ArrayList<Option> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<Option> options) {
        this.options = options;
    }

    public int getNumOptions() {
        return options.size();
    }

    // Print all Options
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OptionSet: ").append(name).append("\n");
        for (int i = 0; i < options.size(); i++) {
            sb.append("Option ").append(i + 1).append(": ").append(options.get(i)).append("\n");
        }
        return sb.toString();
    }

    // Define the Option class implementing Serializable
    class Option implements Serializable {
        // Declare instance variables for Option
        private String optionName;
        private double price; // Add price field

        // Constructor with optionName
        public Option(String optionName) {
            this.optionName = optionName;
        }

        // Getters and setters for Option
        public String getOptionName() {
            return optionName;
        }

        public void setOptionName(String optionName) {
            this.optionName = optionName;
        }

        // Getter and setter for price
        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        // ToString method for Option
        @Override
        public String toString() {
            return "Option: " + optionName + " - Price: " + price;
        }
    }
}
