package scaletests;

import wrapper.CoffeeShopConfigAPI;

public class SimulatedUser implements Runnable {
    private final CoffeeShopConfigAPI coffeeShopConfigAPI;
    private final String userName;
    private final String shopName;
    private final double basePrice;
    private final String optionSetName;
    private final String optionName;
    private final double optionPrice;
    private final String configFile;

    // Constructor to initialize SimulatedUser with required parameters
    public SimulatedUser(CoffeeShopConfigAPI coffeeShopConfigAPI, String userName, String shopName,
                         double basePrice, String optionSetName, String optionName, double optionPrice, String configFile) {
        this.coffeeShopConfigAPI = coffeeShopConfigAPI;
        this.userName = userName;
        this.shopName = shopName;
        this.basePrice = basePrice;
        this.optionSetName = optionSetName;
        this.optionName = optionName;
        this.optionPrice = optionPrice;
        this.configFile = configFile;
    }

    @Override
    public void run() {
        // Simulate user interactions for a specified number of iterations (5 in this case)
        for (int i = 0; i < 5; i++) {
            // Simulate creating a new coffee shop using the provided configuration file
            coffeeShopConfigAPI.configureCoffeeShop(configFile);

            // Print details of the coffee shop with the specified name
            coffeeShopConfigAPI.printCoffeeShop(shopName);

            // Simulate updating the base price of the coffee shop
            coffeeShopConfigAPI.updateBasePrice(shopName, 15.99);

            // Simulate updating the price of a specific option for the coffee shop
            coffeeShopConfigAPI.updateOptionPrice(shopName, optionSetName, optionName, 1200.0);

            // Print details of the coffee shop again after the updates
            coffeeShopConfigAPI.printCoffeeShop(shopName);

            // Simulate updating the name of an option set for the coffee shop
            coffeeShopConfigAPI.updateOptionSetName(shopName, optionSetName, "CoffeeSizes");

            // Simulate removing the coffee shop while iterating
            coffeeShopConfigAPI.deleteCoffeeShop(shopName);
        }
    }
}
