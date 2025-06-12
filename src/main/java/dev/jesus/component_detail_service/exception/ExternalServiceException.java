package dev.jesus.component_detail_service.exception;

import dev.jesus.component_detail_service.exception.model.CustomErrorResponse;

public class ExternalServiceException extends RuntimeException {
    private final CustomErrorResponse customErrorResponse;

    public ExternalServiceException(CustomErrorResponse customErrorResponse) {
        super(customErrorResponse.getMessage());
        this.customErrorResponse = customErrorResponse;
    }

    public CustomErrorResponse getErrorResponse() {
        return customErrorResponse;
    }
}
