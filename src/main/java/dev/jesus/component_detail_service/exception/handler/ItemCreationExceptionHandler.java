package dev.jesus.component_detail_service.exception.handler;

import dev.jesus.component_detail_service.exception.strategy.ErrorHandlerStrategy;
import dev.jesus.component_detail_service.exception.ItemCreationException;
import dev.jesus.component_detail_service.exception.model.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ItemCreationExceptionHandler implements ErrorHandlerStrategy {
    @Override
    public boolean supports(Throwable error) {
        return error instanceof ItemCreationException;
    }

    @Override
    public CustomErrorResponse handle(Throwable error) {
        ItemCreationException ex = (ItemCreationException) error;
        return new CustomErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Creation Failed",
                ex.getMessage()
        );
    }
}
