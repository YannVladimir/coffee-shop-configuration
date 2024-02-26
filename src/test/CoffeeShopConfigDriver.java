package test;

public class CoffeeShopConfigDriver {
 public static void main(String[] args) {
     // Declaring instances of CoffeeTest for creating and updating coffee shop tests
     CoffeeTest createCoffeeTest;
     CoffeeTest updateCoffeeTest;

     // Initialize the createCoffeeTest and execute the test
     createCoffeeTest = new CreateCoffeeShopTest();
     createCoffeeTest.executeTest();

     //initialize the updateCoffeeTest and execute the test
     //updateCoffeeTest = new UpdateCoffeeShopTest();
     //updateCoffeeTest.executeTest();
 }
}
