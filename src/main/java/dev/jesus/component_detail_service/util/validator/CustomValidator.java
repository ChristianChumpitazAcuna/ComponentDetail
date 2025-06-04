package dev.jesus.component_detail_service.util.validator;

import dev.jesus.component_detail_service.exception.CustomValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CustomValidator {
    private final Validator validator;

    public <T> Mono<T> validate(T object) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(object, object.getClass().getName());
        validator.validate(object, bindingResult);

        if (bindingResult.hasErrors()) {
            return Mono.error(new CustomValidationException(bindingResult));
        }
        return Mono.just(object);
    }
}
