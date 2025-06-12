package dev.jesus.component_detail_service.exception;

public class ItemFoundDisabledException extends RuntimeException {
    public ItemFoundDisabledException(String id) {
        super("The item with id: " + id + " was found but it is disabled");
    }
}
