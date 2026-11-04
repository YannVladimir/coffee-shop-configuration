package io;

import exceptions.ExceptionHandler;
import model.CoffeeConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeShopConfigReaderTest {

    private Path tempFile;

    @AfterEach
    void cleanUp() throws IOException {
        if (tempFile != null) {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    void parsesShopNameBasePriceAndOptions() throws Exception {
        tempFile = Files.createTempFile("shop-config", ".txt");
        Files.writeString(tempFile, String.join("\n",
                "# Coffee Shop Configurations",
                "Shop_name=Test_shop",
                "Base_price=2000.0",
                "Option_set_name=CoffeeSize",
                "Options=Small:0.0,Medium:1000.0,Large:2000.0"
        ), StandardCharsets.UTF_8);

        CoffeeConfig config = CoffeeShopConfigReader.buildCoffeeShopConfig(tempFile.toString());

        assertEquals("Test_shop", config.getName());
        assertEquals(2000.0, config.getBasePrice());
        assertEquals(1, config.getOptionSets().size());

        String rendered = config.toString();
        assertTrue(rendered.contains("CoffeeSize"));
        assertTrue(rendered.contains("Small"));
        assertTrue(rendered.contains("Medium"));
        assertTrue(rendered.contains("Large"));
    }

    @Test
    void trimsWhitespaceAroundOptionNamesAfterComma() throws Exception {
        // Regression test: "Regular:0.0, Almond:500.0, Soy:0.0" has a space after each
        // comma. Before the fix, every option after the first one was stored with a
        // leading space in its name (" Almond" instead of "Almond"), which silently broke
        // later name-based lookups such as updateOptionPrice.
        tempFile = Files.createTempFile("shop-config", ".txt");
        Files.writeString(tempFile, String.join("\n",
                "Shop_name=Test_shop",
                "Base_price=2000.0",
                "Option_set_name=Sweetener choices",
                "Options=Regular:0.0, Almond:500.0, Soy:0.0"
        ), StandardCharsets.UTF_8);

        CoffeeConfig config = CoffeeShopConfigReader.buildCoffeeShopConfig(tempFile.toString());

        // This lookup only succeeds if the option name was stored as exactly "Almond"
        // (no leading space); otherwise CoffeeConfig prints "Option not found." and
        // leaves the price untouched.
        config.updateOptionPrice("Sweetener choices", "Almond", 750.0);

        String rendered = config.toString();
        assertTrue(rendered.contains("Almond"));
        assertTrue(rendered.contains("750.0"));
        // "Option: " already supplies one space; a leaked leading space in the option
        // name would show up as a second, consecutive space before the name.
        assertFalse(rendered.contains("  Almond"), "option name must not retain a leading space");
    }

    @Test
    void missingFilePropagatesAsExceptionHandler() {
        // buildCoffeeShopConfig() deliberately re-throws a "File not found" ExceptionHandler
        // when the file can't be opened; it is the caller's job (ProxyCoffeeShops) to catch
        // it and call fixException() to fall back to the default configuration file.
        assertThrows(ExceptionHandler.class,
                () -> CoffeeShopConfigReader.buildCoffeeShopConfig("this_file_does_not_exist.txt"));
    }
}
