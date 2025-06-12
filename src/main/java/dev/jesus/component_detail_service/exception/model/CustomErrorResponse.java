package dev.jesus.component_detail_service.exception.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomErrorResponse {
    private int status;
    private String error;
    private String message;
    private Object details;

    public CustomErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public CustomErrorResponse(int status, String error, String message, Object details) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.details = details;
    }
}
