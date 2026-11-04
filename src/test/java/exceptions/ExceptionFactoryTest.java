package exceptions;

import model.CoffeeConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionFactoryTest {

    private final ExceptionFactory factory = new ExceptionFactory();
    private final CoffeeConfig config = new CoffeeConfig("Test Shop", 1000.0);

    @Test
    void createsMissingBasePriceException() {
        ExceptionHandler handler = factory.createException("Missing Base Price", config);
        assertInstanceOf(MissingBasePriceException.class, handler);
    }

    @Test
    void createsMissingNameException() {
        ExceptionHandler handler = factory.createException("Missing Name", config);
        assertInstanceOf(MissingNameException.class, handler);
    }

    @Test
    void lookupIsCaseInsensitive() {
        ExceptionHandler handler = factory.createException("missing name", config);
        assertInstanceOf(MissingNameException.class, handler);
    }

    @Test
    void unknownMessageThrowsInsteadOfReturningNull() {
        // Regression test: createException used to fall through every "if" (not
        // "else if") and silently return null for an unrecognized message, which caused a
        // NullPointerException at the caller's "throw handler;" site instead of a clear
        // error. It must now fail fast and obviously.
        assertThrows(IllegalArgumentException.class, () -> factory.createException("Not A Real Reason", config));
    }

    @Test
    void missingNameExceptionFixSetsDefaultName() {
        CoffeeConfig unnamed = new CoffeeConfig("", 0.0);
        MissingNameException exception = new MissingNameException("Missing Name", unnamed);

        exception.fixException();

        assertEquals("Default Coffee Name", unnamed.getName());
    }

    @Test
    void missingBasePriceExceptionFixSetsDefaultPrice() {
        CoffeeConfig config = new CoffeeConfig("Test Shop", 0.0);
        MissingBasePriceException exception = new MissingBasePriceException("Missing Base Price", config);

        exception.fixException();

        assertEquals(1000.0, config.getBasePrice());
    }
}
