package dev.jesus.component_detail_service.domain.out.strategy;

import dev.jesus.component_detail_service.domain.in.enums.ComponentType;
import dev.jesus.component_detail_service.domain.out.model.ComponentProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ComponentPropertiesStrategyResolver {
    private final Map<ComponentType, ComponentPropertiesStrategy> strategies;

    public ComponentPropertiesStrategyResolver(List<ComponentPropertiesStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(
                        ComponentPropertiesStrategy::getSupportedType,
                        Function.identity(),
                        (existing, replacement) -> {
                            throw new IllegalStateException(String.format("Duplicate key %s", existing));
                        }
                ));
    }

    public Class<? extends ComponentProperties> getTargetClass(ComponentType type) {
        ComponentPropertiesStrategy strategy = strategies.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException(String.format("No strategy found for type %s", type));
        }
        return strategy.getTargetClass();
    }
}
