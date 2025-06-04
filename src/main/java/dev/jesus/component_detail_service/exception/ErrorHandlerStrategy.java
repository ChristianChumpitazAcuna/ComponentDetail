package dev.jesus.component_detail_service.exception;


import dev.jesus.component_detail_service.exception.model.ErrorResponse;

public interface ErrorHandlerStrategy {
    boolean supports(Throwable error);

    ErrorResponse handle(Throwable error);
}
