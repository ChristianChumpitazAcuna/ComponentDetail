package dev.jesus.component_detail_service.exception;

public class ItemUpdatedException extends RuntimeException {
    public ItemUpdatedException(String message) {
        super(message);
    }

    public ItemUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
