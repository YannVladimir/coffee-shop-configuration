package scaletests;

import wrapper.CoffeeShopConfigAPI;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultipleUsersTestDriver {

    public static void main(String[] args) {
        // Instantiate a CoffeeShopConfigAPI object
        CoffeeShopConfigAPI coffeeShopConfigAPI = new CoffeeShopConfigAPI();

        // Create multiple SimulatedUser objects (threads) that will modify their own list of CoffeeShops
        Thread user1Thread = new Thread(new SimulatedUser(coffeeShopConfigAPI, "User1", "Shop1", 1000.0, "CoffeeSize", "Option1", 200.0, "Shop1_Config.txt"));
        Thread user2Thread = new Thread(new SimulatedUser(coffeeShopConfigAPI, "User2", "Shop2", 1500.0, "CoffeeSize", "Option2", 300.0, "Shop2_Config.txt"));

        // Start the threads
        user1Thread.start();
        user2Thread.start();

        // Create a thread pool for additional threads
        ExecutorService executorService = Executors.newFixedThreadPool(1000);

        // Add 10 more SimulatedUser threads to the thread pool
        for (int i = 1; i <= 10; i++) {
            SimulatedUser additionalUser = new SimulatedUser(
                    coffeeShopConfigAPI,
                    "User" + i,
                    "Default_shop",
                    1000.0 + i * 500.0,
                    "CoffeeSize",
                    "Option" + i,
                    200.0 + i * 100.0,
                    "Shop" + i + "_Config.txt"
            );
            executorService.submit(additionalUser);
        }

        // Shutdown the thread pool after all threads finish
        executorService.shutdown();

        // Wait for all threads to finish
        try {
            user1Thread.join();
            user2Thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print coffee shop details after all threads finish
        coffeeShopConfigAPI.printCoffeeShop("Wannabuy");
    }
}
