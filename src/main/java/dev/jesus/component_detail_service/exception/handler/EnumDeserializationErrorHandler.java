package dev.jesus.component_detail_service.exception.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import dev.jesus.component_detail_service.exception.ErrorHandlerStrategy;
import dev.jesus.component_detail_service.exception.model.ErrorResponse;
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
    public ErrorResponse handle(Throwable error) {
        String message = "Invalid input format value" + error.toString();
        InvalidFormatException invalidFormatException = findInvalidFormatException(error);
        if (invalidFormatException != null) {
            message = String.format("Invalid value '%s' for field '%s'. Expected one of: %s",
                    invalidFormatException.getValue(),
                    invalidFormatException.getPath().getFirst().getFieldName(),
                    invalidFormatException.getTargetType().getEnumConstants() != null
                            ? Arrays.toString(invalidFormatException.getTargetType().getEnumConstants())
                            : "unknown");
        } else if (error.getCause() instanceof NullPointerException || error.getMessage().contains("null")) {
            message = "The field cannot be null";
        }
        return new ErrorResponse(
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
}
