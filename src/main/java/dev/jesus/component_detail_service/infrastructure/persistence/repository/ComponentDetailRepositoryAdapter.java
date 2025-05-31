package dev.jesus.component_detail_service.infrastructure.persistence.repository;

import dev.jesus.component_detail_service.domain.in.model.ComponentDetail;
import dev.jesus.component_detail_service.domain.in.repository.ComponentDetailRepository;
import dev.jesus.component_detail_service.infrastructure.persistence.mapper.ComponentDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ComponentDetailRepositoryAdapter implements ComponentDetailRepository {
    private final ComponentDetailReactiveMongoRepository mongoRepository;
    private final ComponentDetailMapper mapper;

    @Override
    public Mono<ComponentDetail> save(ComponentDetail componentDetail) {
        return Mono.just(componentDetail)
                .map(mapper::domainToEntity)
                .flatMap(mongoRepository::save)
                .map(mapper::entityToDomain);
    }

    @Override
    public Mono<ComponentDetail> findById(String id) {
        return mongoRepository.findById(id)
                .map(mapper::entityToDomain);
    }

    @Override
    public Mono<Void> changeStatus(String id, Boolean status) {
        return mongoRepository.changeStatus(id, status);
    }

    @Override
    public Flux<ComponentDetail> findByStatus(Boolean status) {
        return mongoRepository.findByStatus(status)
                .map(mapper::entityToDomain);
    }
}
