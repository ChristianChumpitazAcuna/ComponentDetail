package dev.jesus.component_detail_service.domain.out.strategy;

import dev.jesus.component_detail_service.domain.in.enums.ComponentType;
import dev.jesus.component_detail_service.domain.out.model.ComponentProperties;

public interface ComponentPropertiesStrategy {
    ComponentType getSupportedType();

    Class<? extends ComponentProperties> getTargetClass();
}
