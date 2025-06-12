package dev.jesus.component_detail_service.exception.handler;

import dev.jesus.component_detail_service.exception.strategy.ErrorHandlerStrategy;
import dev.jesus.component_detail_service.exception.ItemNotFoundException;
import dev.jesus.component_detail_service.exception.model.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ItemNotFoundExceptionHandler implements ErrorHandlerStrategy {
    @Override
    public boolean supports(Throwable error) {
        return error instanceof ItemNotFoundException;
    }

    @Override
    public CustomErrorResponse handle(Throwable error) {
        ItemNotFoundException ex = (ItemNotFoundException) error;
        return new CustomErrorResponse(
                HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage()
        );
    }
}
