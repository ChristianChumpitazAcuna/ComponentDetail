package dev.jesus.component_detail_service.exception.strategy;


import dev.jesus.component_detail_service.exception.model.CustomErrorResponse;

public interface ErrorHandlerStrategy {
    boolean supports(Throwable error);

    CustomErrorResponse handle(Throwable error);
}
