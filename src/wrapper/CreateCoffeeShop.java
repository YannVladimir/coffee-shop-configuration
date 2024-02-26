package wrapper;

import model.CoffeeConfig;

import java.util.ArrayList;

public interface CreateCoffeeShop {
    void createCoffeeShop(String coffeeShopName, CoffeeConfig coffeeConfigs);
    void configureCoffeeShop(String filename);
    void printCoffeeShop(String coffeeShopName);
}
