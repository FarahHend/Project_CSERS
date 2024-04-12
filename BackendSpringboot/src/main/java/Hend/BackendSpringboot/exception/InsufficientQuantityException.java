package Hend.BackendSpringboot.exception;


public class InsufficientQuantityException extends RuntimeException {
    public InsufficientQuantityException() {
        super("Insufficient quantity available.");
    }

    public InsufficientQuantityException(String message) {
        super(message);
    }
}
