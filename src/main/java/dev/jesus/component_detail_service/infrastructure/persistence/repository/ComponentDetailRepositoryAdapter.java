package dev.jesus.component_detail_service.infrastructure.persistence.repository;

import dev.jesus.component_detail_service.domain.in.model.ComponentDetail;
import dev.jesus.component_detail_service.domain.in.repository.ComponentDetailRepository;
import dev.jesus.component_detail_service.exception.DataBaseException;
import dev.jesus.component_detail_service.util.converter.CustomConverter;
import io.r2dbc.postgresql.api.PostgresqlException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class ComponentDetailRepositoryAdapter implements ComponentDetailRepository {
    private final ComponentDetailReactivePostgresRepository mongoRepository;
    private final CustomConverter customConverter;

    @Override
    public Mono<ComponentDetail> save(ComponentDetail componentDetail) {
        return Mono.just(componentDetail)
                .map(componentDetail1 -> customConverter.writeDomainToEntity(componentDetail))
                .flatMap(mongoRepository::save)
                .map(customConverter::readEntityToDomain)
                .onErrorMap(e -> {
                    Throwable cause = e.getCause();
                    if (cause instanceof PostgresqlException ex) {
                        return new DataBaseException(
                                ex.getErrorDetails().getCode(),
                                ex.getErrorDetails().getMessage(),
                                ex.getErrorDetails().getDetail().orElse("N/A"),
                                ex.getErrorDetails().getTableName().orElse("N/A"),
                                ex.getErrorDetails().getConstraintName().orElse("N/A")
                        );
                    }
                    return new DataBaseException("UNKNOWN", e.getMessage(), null, null, null);
                });
    }

    @Override
    public Mono<ComponentDetail> findById(Long id) {
        return mongoRepository.findById(id)
                .map(customConverter::readEntityToDomain);
    }

    @Override
    public Mono<Void> changeStatus(Long id, Boolean status) {
        return mongoRepository.changeStatus(id, status);
    }

    @Override
    public Flux<ComponentDetail> findByStatus(Boolean status) {
        return mongoRepository.findByStatus(status)
                .map(customConverter::readEntityToDomain);
    }
}
