package dev.jesus.component_detail_service.exception.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import dev.jesus.component_detail_service.exception.strategy.ErrorHandlerStrategy;
import dev.jesus.component_detail_service.exception.model.CustomErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebInputException;

import java.util.Arrays;

@Slf4j
@Component
public class EnumDeserializationErrorHandler implements ErrorHandlerStrategy {
    @Override
    public boolean supports(Throwable error) {
        return error instanceof ServerWebInputException;
    }

    @Override
    public CustomErrorResponse handle(Throwable error) {
        InvalidFormatException ex = findInvalidFormatException(error);

        if (ex == null) {
            log.error("No InvalidFormatException found in the error " +
                    "chain for ServerWebInputException: {}", error.getMessage(), error);

            return new CustomErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Invalid input data: unable to process the request",
                    "Invalid Json payload"
            );
        }

        String value = ex.getValue() != null
                ? ex.getValue().toString()
                : "null";

        String message = getCustomMessage(ex, value);

        return new CustomErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                message,
                "Invalid JSON payload"
        );
    }

    private InvalidFormatException findInvalidFormatException(Throwable throwable) {
        while (throwable != null) {
            if (throwable instanceof InvalidFormatException e) {
                return e;
            }
            throwable = throwable.getCause();
        }
        return null;
    }

    private static String getCustomMessage(InvalidFormatException ex, String value) {
        String fieldName = ex.getPath() != null && !ex.getPath().isEmpty()
                ? ex.getPath().getFirst().getFieldName()
                : "unknown";

        String allowedValues = ex.getTargetType() != null && ex.getTargetType().getEnumConstants() != null
                ? Arrays.toString(ex.getTargetType().getEnumConstants())
                : "unknown";

        return String.format("Invalid value '%s' for field '%s'. Expected one of: %s",
                value, fieldName, allowedValues);
    }
}
