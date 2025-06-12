package dev.jesus.component_detail_service.infrastructure.persistence.repository;


import dev.jesus.component_detail_service.infrastructure.persistence.entity.ComponentDetailEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ComponentDetailReactivePostgresRepository extends ReactiveCrudRepository<ComponentDetailEntity, Long> {

    @Query("UPDATE component_detail SET status=?2 WHERE id=?1")
    Mono<Void> changeStatus(Long id, Boolean status);

    Flux<ComponentDetailEntity> findByStatus(Boolean status);
}
