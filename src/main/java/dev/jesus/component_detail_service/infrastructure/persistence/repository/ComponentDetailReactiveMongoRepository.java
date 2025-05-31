package dev.jesus.component_detail_service.infrastructure.persistence.repository;

import dev.jesus.component_detail_service.infrastructure.persistence.entity.ComponentDetailEntity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ComponentDetailReactiveMongoRepository extends ReactiveMongoRepository<ComponentDetailEntity, String> {
    @Query("{_id: ?0}")
    @Update("{$set: {'status':  ?1}}")
    Mono<Void> changeStatus(String id, Boolean status);

    Flux<ComponentDetailEntity> findByStatus(Boolean status);
}
