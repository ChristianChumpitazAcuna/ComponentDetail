package dev.jesus.component_detail_service.infrastructure.rest.in.handler;

import dev.jesus.component_detail_service.domain.in.model.ComponentDetail;
import dev.jesus.component_detail_service.domain.in.model.dto.ComponentDetailRequestDTO;
import dev.jesus.component_detail_service.domain.in.useCases.ComponentDetailUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ComponentDetailHandler {
    private final ComponentDetailUseCases useCases;

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(ComponentDetailRequestDTO.class)
                .flatMap(useCases::createComponentDetail)
                .flatMap(componentDetail -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(componentDetail)
                );
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");
        return request.bodyToMono(ComponentDetailRequestDTO.class)
                .flatMap(dto -> useCases.updateComponentDetail(id, dto))
                .flatMap(componentDetail -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(componentDetail)
                );
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        String id = request.pathVariable("id");
        return useCases.findComponentDetailById(id)
                .flatMap(componentDetail -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(componentDetail)
                );
    }

    public Mono<ServerResponse> getByStatus(ServerRequest request) {
        Boolean status = Boolean.parseBoolean(request.pathVariable("status"));
        Flux<ComponentDetail> componentDetailFlux = useCases.findComponentDetailByStatus(status);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(componentDetailFlux, ComponentDetail.class);
    }


}
