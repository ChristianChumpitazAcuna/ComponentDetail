package dev.jesus.component_detail_service.util.validator;

import jakarta.validation.ConstraintValidatorContext;

public class CustomConstrainViolation {
    public static void addConstraintViolation(ConstraintValidatorContext context,
                                              String message, String propertyNode) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(propertyNode)
                .addConstraintViolation();
    }
}
