package dev.jesus.component_detail_service.exception.handler;

import dev.jesus.component_detail_service.exception.strategy.ErrorHandlerStrategy;
import dev.jesus.component_detail_service.exception.ItemUpdatedException;
import dev.jesus.component_detail_service.exception.model.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ItemUpdateExceptionHandler implements ErrorHandlerStrategy {
    @Override
    public boolean supports(Throwable error) {
        return error instanceof ItemUpdatedException;
    }

    @Override
    public CustomErrorResponse handle(Throwable error) {
        ItemUpdatedException ex = (ItemUpdatedException) error;
        return new CustomErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Updated Failed",
                ex.getMessage()
        );
    }
}
