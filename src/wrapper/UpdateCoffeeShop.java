package wrapper;

public interface UpdateCoffeeShop {
    void updateOptionSetName(String coffeeShopName, String optionSetName, String newName);
    void updateBasePrice(String coffeeShopName, double newPrice);
    void updateOptionPrice(String coffeeShopName, String optionSetName, String optionName, double newPrice);
}