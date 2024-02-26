package test;

import model.CoffeeConfig;

// Define the UpdateCoffeeShopTest class extending CoffeeTest
public class UpdateCoffeeShopTest extends CoffeeTest {
    // Override the executeTest method
    @Override
    public void executeTest() {
        // Use the updateOptionSetName method from the UpdateApi to update the OptionSet name
        getUpdateApi().updateOptionSetName("Default_shop", "Milk choices", "Almond");

        // Use the updateBasePrice method from the UpdateApi to update the base price
        getUpdateApi().updateBasePrice("Default_shop", 2500.0);

        // Use the updateOptionPrice method from the UpdateApi to update the Option price
        getUpdateApi().updateOptionPrice("Default_shop", "Milk choices", "Almond", 100.0);

        getCreateApi().printCoffeeShop("Default_shop");
    }
}
