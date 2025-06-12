package dev.jesus.component_detail_service.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(Long id) {
        super("Item not found, with id: " + id);
    }
}
