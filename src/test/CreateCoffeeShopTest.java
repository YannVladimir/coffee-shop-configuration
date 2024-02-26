package test;

import model.CoffeeConfig;

// Step 1: Define the CreateCoffeeShopTest class extending CoffeeTest
public class CreateCoffeeShopTest extends CoffeeTest {
    // Step 1.1: Override the executeTest method
    @Override
    public void executeTest() {
        // Step 1.2: Create a new CoffeeConfig instance for the coffee shop "Simba"
        //ytCoffeeConfig coffeeConfig = new CoffeeConfig("Easy Day Shop");

        // Step 1.3: Create an OptionSet named "Milk" in the coffeeConfig
        //coffeeConfig.createOptionSet("Milk choices");

        // Step 1.4: Create an Option named "Milk-1" with price 2000 in the "Milk" OptionSet
        //coffeeConfig.createOption("Milk choices", "Almond", 800);

        // Step 1.5: Use the createCoffeeShop method from the CreateApi to create the coffee shop
       // getCreateApi().createCoffeeShop("Easy Day Shop", coffeeConfig);
    	System.out.println("Test 1");
        // Use the configureCoffeeShop method from the CreateApi to configure the coffee shop from a file
        getCreateApi().configureCoffeeShop("Shop_Config.txt");

        // Use the printCoffeeShop method from the CreateApi to print the details of the coffee shop "Easy Day Shop"
        getCreateApi().printCoffeeShop("Default_shop");
        
        System.out.println("Test 2");
        getCreateApi().configureCoffeeShop("file_which_is_not_there.txt"); // This should raise the exception
        
        // Use the printCoffeeShop method from the CreateApi to print the details of the coffee shop "Easy Day Shop"
        getCreateApi().printCoffeeShop("Default_shop");
        
        
    }
}
