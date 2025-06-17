package dev.jesus.component_detail_service.exception;


import lombok.Getter;

@Getter
public class DataBaseException extends RuntimeException {
    private final String code;
    private final String message;
    private final String detail;
    private final String tableName;
    private final String constraintName;

    public DataBaseException(String code, String message, String detail,
                             String tableName, String constraintName) {
        super(message);
        this.code = code;
        this.message = message;
        this.detail = detail;
        this.tableName = tableName;
        this.constraintName = constraintName;
    }
}
