package dev.jesus.component_detail_service.infrastructure.rest.out;

import dev.jesus.component_detail_service.domain.in.enums.ComponentType;
import dev.jesus.component_detail_service.domain.out.model.ComponentProperties;
import dev.jesus.component_detail_service.domain.out.service.ExternalService;
import dev.jesus.component_detail_service.domain.out.strategy.ComponentPropertiesStrategyResolver;
import dev.jesus.component_detail_service.exception.ExternalServiceException;
import dev.jesus.component_detail_service.exception.model.CustomErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExternalServiceImpl implements ExternalService {
    private final WebClient webClient;
    private final ComponentPropertiesStrategyResolver strategyResolver;

    @Value("${external-services.component-service.url}")
    private String componentUrl;

    @Override
    public Mono<ComponentProperties> getComponentProperties(ComponentType componentType, String id) {
        if (componentType == null || id == null) {
            return Mono.error(new IllegalArgumentException("Component type or id is null"));
        }
        String uri = componentUrl + "/" + componentType.toUriParam() + "/" + id;

        Class<? extends ComponentProperties> targetClass = strategyResolver.getTargetClass(componentType);

        return webClient
                .get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse ->
                        clientResponse.bodyToMono(CustomErrorResponse.class)
                                .map(ExternalServiceException::new)
                                .switchIfEmpty(Mono.error(new ExternalServiceException(
                                        new CustomErrorResponse(
                                                clientResponse.statusCode().value(),
                                                "Unknown Error",
                                                "No error details provided by the service",
                                                null
                                        )
                                )))
                                .flatMap(Mono::error)
                )
                .bodyToMono(targetClass)
                .cast(ComponentProperties.class)
                .doOnError(throwable ->
                        log.error("Error getting data with id {}: {}", id, throwable.getMessage())
                );
    }
}
