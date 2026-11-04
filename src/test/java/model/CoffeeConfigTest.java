package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeConfigTest {

    @Test
    void defaultConstructorAssignsDefaultName() {
        // The no-arg constructor unconditionally runs the "Missing Name" fix-up, which
        // should give every new, unnamed CoffeeConfig the same default name.
        CoffeeConfig config = new CoffeeConfig();
        assertEquals("Default Coffee Name", config.getName());
    }

    @Test
    void namedAndPricedConstructorSetsFieldsDirectly() {
        CoffeeConfig config = new CoffeeConfig("Latte", 1500.0);
        assertEquals("Latte", config.getName());
        assertEquals(1500.0, config.getBasePrice());
        assertTrue(config.getOptionSets().isEmpty());
    }

    @Test
    void createOptionSetAddsOptionSetOnlyOnce() {
        CoffeeConfig config = new CoffeeConfig("Latte", 1500.0);
        config.createOptionSet("Milk choices");
        config.createOptionSet("Milk choices");

        assertEquals(1, config.getOptionSets().size());
        assertEquals("Milk choices", config.getOptionSetName());
    }

    @Test
    void createOptionTracksLastCreatedOptionName() {
        // Regression test for a bug where CoffeeConfig.createOption had
        // "optionName = optionName;" instead of "this.optionName = optionName;", so
        // getOptionName() always returned "" no matter how many options were created.
        CoffeeConfig config = new CoffeeConfig("Latte", 1500.0);
        config.createOptionSet("Milk choices");

        assertEquals("", config.getOptionName());

        config.createOption("Milk choices", "Almond", 500.0);

        assertEquals("Almond", config.getOptionName());
    }

    @Test
    void updateOptionPriceUpdatesExistingOption() {
        CoffeeConfig config = new CoffeeConfig("Latte", 1500.0);
        config.createOptionSet("Milk choices");
        config.createOption("Milk choices", "Almond", 500.0);

        config.updateOptionPrice("Milk choices", "Almond", 750.0);

        OptionSet optionSet = config.getOptionSets().get(0);
        assertEquals(750.0, optionSet.readOption("Almond").getPrice());
    }

    @Test
    void updateOptionPriceIsNoOpWhenOptionSetMissing() {
        CoffeeConfig config = new CoffeeConfig("Latte", 1500.0);
        // Should not throw even though "Milk choices" does not exist yet.
        assertDoesNotThrow(() -> config.updateOptionPrice("Milk choices", "Almond", 750.0));
    }

    @Test
    void deleteOptionSetRemovesIt() {
        CoffeeConfig config = new CoffeeConfig("Latte", 1500.0);
        config.createOptionSet("Milk choices");

        config.deleteOptionSet("Milk choices");

        assertTrue(config.getOptionSets().isEmpty());
    }
}
