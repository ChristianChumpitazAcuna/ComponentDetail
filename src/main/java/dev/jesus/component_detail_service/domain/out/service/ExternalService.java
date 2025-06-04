package dev.jesus.component_detail_service.domain.out.service;

import dev.jesus.component_detail_service.domain.in.enums.ComponentType;
import dev.jesus.component_detail_service.domain.out.model.ComponentProperties;
import reactor.core.publisher.Mono;

public interface ExternalService {

    Mono<ComponentProperties> getComponentProperties(ComponentType componentType, String id);
}