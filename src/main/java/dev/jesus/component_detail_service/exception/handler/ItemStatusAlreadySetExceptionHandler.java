package dev.jesus.component_detail_service.exception.handler;

import dev.jesus.component_detail_service.exception.ErrorHandlerStrategy;
import dev.jesus.component_detail_service.exception.ItemStatusAlreadySetException;
import dev.jesus.component_detail_service.exception.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ItemStatusAlreadySetExceptionHandler implements ErrorHandlerStrategy {
    @Override
    public boolean supports(Throwable error) {
        return error instanceof ItemStatusAlreadySetException;
    }

    @Override
    public ErrorResponse handle(Throwable error) {
        ItemStatusAlreadySetException ex = (ItemStatusAlreadySetException) error;
        return new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Conflict",
                ex.getMessage()
        );
    }
}
