package dev.jesus.component_detail_service.domain.out.strategy;

import dev.jesus.component_detail_service.domain.in.enums.ComponentType;
import dev.jesus.component_detail_service.domain.out.model.BoardProperties;
import dev.jesus.component_detail_service.domain.out.model.ComponentProperties;
import org.springframework.stereotype.Component;

@Component
public class BoardPropertiesStrategy implements ComponentPropertiesStrategy {
    @Override
    public ComponentType getSupportedType() {
        return ComponentType.BOARD;
    }

    @Override
    public Class<? extends ComponentProperties> getTargetClass() {
        return BoardProperties.class;
    }
}
