package wrapper;

import model.CoffeeConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeShopConfigApiTest {

    private final CoffeeShopConfigAPI api = new CoffeeShopConfigAPI();

    @AfterEach
    void cleanUp() {
        // ProxyCoffeeShops.configs is a static, process-wide map; clear it between tests
        // so tests don't leak state into each other.
        ProxyCoffeeShops.configs.clear();
    }

    @Test
    void createAndRetrieveCoffeeShop() {
        CoffeeConfig config = new CoffeeConfig("Test Shop", 1200.0);
        api.createCoffeeShop("Test Shop", config);

        assertTrue(ProxyCoffeeShops.configs.containsKey("Test Shop"));
        assertSame(config, ProxyCoffeeShops.configs.get("Test Shop"));
    }

    @Test
    void updateBasePriceOnlyAffectsExistingShop() {
        CoffeeConfig config = new CoffeeConfig("Test Shop", 1200.0);
        api.createCoffeeShop("Test Shop", config);

        api.updateBasePrice("Test Shop", 1999.0);
        assertEquals(1999.0, config.getBasePrice());

        // Updating a shop that was never created must not throw and must not create one.
        assertDoesNotThrow(() -> api.updateBasePrice("Unknown Shop", 50.0));
        assertFalse(ProxyCoffeeShops.configs.containsKey("Unknown Shop"));
    }

    @Test
    void deleteCoffeeShopRemovesItFromConfigs() {
        CoffeeConfig config = new CoffeeConfig("Test Shop", 1200.0);
        api.createCoffeeShop("Test Shop", config);

        api.deleteCoffeeShop("Test Shop");

        assertFalse(ProxyCoffeeShops.configs.containsKey("Test Shop"));
    }

    @Test
    void deletingUnknownShopDoesNotThrow() {
        assertDoesNotThrow(() -> api.deleteCoffeeShop("Never Created"));
    }

    @Test
    void updateOptionSetNameAndOptionPriceDelegateToCoffeeConfig() {
        CoffeeConfig config = new CoffeeConfig("Test Shop", 1200.0);
        config.createOptionSet("Milk choices");
        config.createOption("Milk choices", "Almond", 500.0);
        api.createCoffeeShop("Test Shop", config);

        api.updateOptionSetName("Test Shop", "Milk choices", "Milk options");
        api.updateOptionPrice("Test Shop", "Milk options", "Almond", 650.0);

        String rendered = config.toString();
        assertTrue(rendered.contains("Milk options"));
        assertTrue(rendered.contains("650.0"));
    }
}
