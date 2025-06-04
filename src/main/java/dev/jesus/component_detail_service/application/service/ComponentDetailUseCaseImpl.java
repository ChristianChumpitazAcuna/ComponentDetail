package dev.jesus.component_detail_service.application.service;

import dev.jesus.component_detail_service.application.mapper.ComponentDetailUseCaseMapper;
import dev.jesus.component_detail_service.domain.in.model.ComponentDetail;
import dev.jesus.component_detail_service.domain.in.model.dto.ComponentDetailRequestDTO;
import dev.jesus.component_detail_service.domain.in.repository.ComponentDetailRepository;
import dev.jesus.component_detail_service.domain.in.useCases.ComponentDetailUseCases;
import dev.jesus.component_detail_service.domain.out.model.ComponentProperties;
import dev.jesus.component_detail_service.domain.out.service.ExternalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
                    componentDetail.setComponentAttributes(componentProperties);
                    return componentDetail;
                })
                .flatMap(repository::save)
                .onErrorMap(e -> {
                    log.error("Error saving component detail: {}", e.getMessage());
                    return new RuntimeException("Error, saving component detail");
                });
    }

    @Override
    public Mono<ComponentDetail> updateComponentDetail(String id, ComponentDetailRequestDTO dto) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Component detail not found with id: " + id)))
                .flatMap(existingComponentDetail -> validateComponentId(dto))
                .map(__ -> {
                    ComponentDetail componentDetail = mapper.dtoToEntity(dto);
                    componentDetail.setId(id);
                    componentDetail.setStatus(true);
                    return componentDetail;
                })
                .flatMap(repository::save)
                .onErrorMap(e -> {
                    log.error("Error updating component detail: {}", e.getMessage());
                    return new RuntimeException("Error, updating component detail");
                });
    }

    @Override
    public Mono<ComponentDetail> findComponentDetailById(String id) {
        return null;
    }

    @Override
    public Mono<Void> changeStatusComponentDetail(String id, Boolean status) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Component detail not found with id: " + id)))
                .flatMap(componentDetail -> {
                    if (componentDetail.getStatus().equals(status)) {
                        return Mono.error(new RuntimeException("Component detail already in the request status: " + componentDetail.getId()));
                    }
                    return repository.changeStatus(id, status);
                })
                .onErrorMap(e -> {
                    if (e.getCause() instanceof RuntimeException) {
                        return e;
                    }
                    log.error("Error chaining componentDetail status: {}", e.getMessage());
                    return new RuntimeException("Error, chaining componentDetail status");
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
                .onErrorMap(e -> {
                    log.error("Error getting component detail: {}", e.getMessage());
                    return new RuntimeException("Error, getting component detail");
                });
    }
}
