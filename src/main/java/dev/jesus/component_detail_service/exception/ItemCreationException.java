package dev.jesus.component_detail_service.exception;

public class ItemCreationException extends RuntimeException {
    public ItemCreationException() {
        super("Error creating item");
    }

}
