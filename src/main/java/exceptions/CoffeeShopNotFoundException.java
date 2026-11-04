package exceptions;

public class CoffeeShopNotFoundException extends RuntimeException {
    public CoffeeShopNotFoundException(String message) {
        super(message);
    }
}
