package dev.jesus.component_detail_service.exception.handler;

import dev.jesus.component_detail_service.exception.DataBaseException;
import dev.jesus.component_detail_service.exception.model.CustomErrorResponse;
import dev.jesus.component_detail_service.exception.strategy.ErrorHandlerStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataBaseErrorHandler implements ErrorHandlerStrategy {
    @Override
    public boolean supports(Throwable error) {
        return error instanceof DataBaseException;
    }

    @Override
    public CustomErrorResponse handle(Throwable error) {
        DataBaseException ex = (DataBaseException) error;
        Map<String, String> details = new HashMap<>();
        details.put("code", ex.getCode());
        details.put("message", ex.getMessage());
        details.put("detail", ex.getDetail());
        details.put("table", ex.getTableName());
        details.put("constraintName", ex.getConstraintName());

        return new CustomErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Data Base Error",
                details
        );
    }
}
