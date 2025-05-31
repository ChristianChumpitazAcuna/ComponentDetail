package dev.jesus.component_detail_service.domain.in.repository;

import dev.jesus.component_detail_service.domain.in.model.ComponentDetail;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ComponentDetailRepository {
    Mono<ComponentDetail> save(ComponentDetail componentDetail);

    Mono<ComponentDetail> findById(String id);

    Mono<Void> changeStatus(String id, Boolean status);

    Flux<ComponentDetail> findByStatus(Boolean status);
}
