package dev.jesus.component_detail_service.domain.out.strategy;

import dev.jesus.component_detail_service.domain.in.enums.ComponentType;
import dev.jesus.component_detail_service.domain.out.model.AccessoryProperties;
import dev.jesus.component_detail_service.domain.out.model.ComponentProperties;
import org.springframework.stereotype.Component;

@Component
public class AccessoryPropertiesStrategy implements ComponentPropertiesStrategy {
    @Override
    public ComponentType getSupportedType() {
        return ComponentType.ACCESSORY;
    }

    @Override
    public Class<? extends ComponentProperties> getTargetClass() {
        return AccessoryProperties.class;
    }
}
