package dev.jesus.component_detail_service.exception;

public class ItemStatusAlreadySetException extends RuntimeException {
    public ItemStatusAlreadySetException(Long id, Boolean status) {
        super("The item with id: " + id + " is already set with request status: " + status);
    }
}
