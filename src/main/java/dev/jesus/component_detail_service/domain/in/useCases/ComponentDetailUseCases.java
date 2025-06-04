package dev.jesus.component_detail_service.domain.in.useCases;

import dev.jesus.component_detail_service.domain.in.model.ComponentDetail;
import dev.jesus.component_detail_service.domain.in.model.dto.ComponentDetailRequestDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ComponentDetailUseCases {
    Mono<ComponentDetail> createComponentDetail(ComponentDetailRequestDTO dto);

    Mono<ComponentDetail> updateComponentDetail(String id, ComponentDetailRequestDTO dto);

    Mono<ComponentDetail> findComponentDetailById(String id);

    Mono<Void> changeStatusComponentDetail(String id, Boolean status);

    Flux<ComponentDetail> findComponentDetailByStatus(Boolean status);
}
