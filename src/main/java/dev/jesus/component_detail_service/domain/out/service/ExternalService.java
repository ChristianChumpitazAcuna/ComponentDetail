package dev.jesus.component_detail_service.domain.out.service;


import dev.jesus.component_detail_service.domain.out.model.ComponentInfo;
import reactor.core.publisher.Mono;

public interface ExternalService {
    Mono<ComponentInfo> getComponentInfo(String id);
}
