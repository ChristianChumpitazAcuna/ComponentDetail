package dev.jesus.component_detail_service.exception.handler;

import dev.jesus.component_detail_service.exception.CustomValidationException;
import dev.jesus.component_detail_service.exception.strategy.ErrorHandlerStrategy;
import dev.jesus.component_detail_service.exception.model.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ValidationExceptionHandler implements ErrorHandlerStrategy {

    @Override
    public boolean supports(Throwable error) {
        return error instanceof CustomValidationException;
    }

    @Override
    public CustomErrorResponse handle(Throwable error) {
        CustomValidationException ex = (CustomValidationException) error;

        List<Map<String, String>> errors = new ArrayList<>();

        ex.getErrors().getFieldErrors().forEach(fieldError -> {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("field", fieldError.getField());
            errorMap.put("message", fieldError.getDefaultMessage());
            errors.add(errorMap);
        });

        return new CustomErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Invalid request parameters",
                errors
        );
    }
}
