package dev.jesus.component_detail_service.exception;

public class ItemChangeStatusException extends RuntimeException {
    public ItemChangeStatusException(Long id, Boolean status) {
        super("Error changing item status: " + id + " with request status: " + status);
    }
}
