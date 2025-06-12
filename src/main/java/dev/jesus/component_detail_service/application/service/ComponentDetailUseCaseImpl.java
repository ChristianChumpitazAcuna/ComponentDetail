package dev.jesus.component_detail_service.application.service;

import dev.jesus.component_detail_service.application.mapper.ComponentDetailUseCaseMapper;
import dev.jesus.component_detail_service.domain.in.model.ComponentDetail;
import dev.jesus.component_detail_service.domain.in.model.dto.ComponentDetailRequestDTO;
import dev.jesus.component_detail_service.domain.in.repository.ComponentDetailRepository;
import dev.jesus.component_detail_service.domain.in.useCases.ComponentDetailUseCases;
import dev.jesus.component_detail_service.domain.out.model.ComponentProperties;
import dev.jesus.component_detail_service.domain.out.service.ExternalService;
import dev.jesus.component_detail_service.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class ComponentDetailUseCaseImpl implements ComponentDetailUseCases {
    private final ComponentDetailRepository repository;
    private final ComponentDetailUseCaseMapper mapper;
    private final ExternalService externalService;

    @Override
    public Mono<ComponentDetail> createComponentDetail(ComponentDetailRequestDTO dto) {
        return validateComponentId(dto)
                .map(componentProperties -> {
                    ComponentDetail componentDetail = mapper.dtoToEntity(dto);
                    componentDetail.setCreationTime(LocalDateTime.now());
                    componentDetail.setUpdateTime(null);
                    componentDetail.setComponentAttributes(componentProperties);
                    componentDetail.setStatus(true);
                    return componentDetail;
                })
                .flatMap(repository::save)
                .onErrorMap(e -> {
                    if (e instanceof IllegalArgumentException || e instanceof ExternalServiceException ||
                            e instanceof ItemFoundDisabledException
                    ) {
                        return e;
                    }
                    log.error("Error saving component detail: {}", e.getMessage(), e);
                    return new ItemCreationException();
                });
    }

    @Override
    public Mono<ComponentDetail> updateComponentDetail(Long id, ComponentDetailRequestDTO dto) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ItemNotFoundException(id)))
                .flatMap(existingComponentDetail -> validateComponentId(dto))
                .map(__ -> {
                    ComponentDetail componentDetail = mapper.dtoToEntity(dto);
                    componentDetail.setId(id);
                    componentDetail.setStatus(true);
                    return componentDetail;
                })
                .flatMap(repository::save)
                .onErrorMap(e -> {
                    log.error("Error updating component detail with id: {} {}", id, e.getMessage(), e);
                    return new ItemUpdatedException(id);
                });
    }

    @Override
    public Mono<ComponentDetail> findComponentDetailById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ItemNotFoundException(id)));
    }

    @Override
    public Mono<Void> changeComponentStatus(Long id, Boolean status) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ItemNotFoundException(id)))
                .flatMap(componentDetail -> {
                    if (componentDetail.getStatus().equals(status)) {
                        return Mono.error(new ItemStatusAlreadySetException(id, status));
                    }
                    return repository.changeStatus(id, status);
                })
                .onErrorMap(e -> {
                    if (e instanceof ItemNotFoundException || e instanceof ItemStatusAlreadySetException) {
                        return e;
                    }
                    log.error("Error changing componentDetail status with id: {} {}", id, e.getMessage(), e);
                    return new ItemChangeStatusException(id, status);
                });
    }

    @Override
    public Flux<ComponentDetail> findComponentDetailByStatus(Boolean status) {
        return repository.findByStatus(status);
    }


    private Mono<ComponentProperties> validateComponentId(ComponentDetailRequestDTO dto) {
        return externalService.getComponentProperties(
                        dto.getComponentType(), dto.getComponentId()
                )
                .flatMap(componentProperties -> {
                    if (componentProperties.getStatus().equals(false)) {
                        return Mono.error(new ItemFoundDisabledException(dto.getComponentId()));
                    }
                    return Mono.just(componentProperties);
                })
                .onErrorMap(e -> {
                    if (e instanceof IllegalArgumentException || e instanceof ExternalServiceException ||
                            e instanceof ItemFoundDisabledException
                    ) {
                        return e;
                    }
                    var formatId = Long.parseLong(dto.getComponentId());
                    log.error("Error validating component detail with id: {} {}", dto.getComponentId(), e.getMessage(), e);
                    return new ItemNotFoundException(formatId);
                });
    }
}
