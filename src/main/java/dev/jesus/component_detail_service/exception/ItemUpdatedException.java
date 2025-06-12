package dev.jesus.component_detail_service.exception;

public class ItemUpdatedException extends RuntimeException {
    public ItemUpdatedException(Long id) {
        super("Error updating item with id: " + id);
    }
}
