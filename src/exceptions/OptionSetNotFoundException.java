package exceptions;

public class OptionSetNotFoundException extends RuntimeException {
    public OptionSetNotFoundException(String message) {
        super(message);
    }
}
