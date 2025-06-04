package dev.jesus.component_detail_service.infrastructure.rest.in.router;

import dev.jesus.component_detail_service.domain.in.model.ComponentDetail;
import dev.jesus.component_detail_service.domain.in.model.dto.ComponentDetailRequestDTO;
import dev.jesus.component_detail_service.infrastructure.rest.in.handler.ComponentDetailHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ComponentDetailRouter {
    private static final String BASE_PATH = "/component/api/v1/componentDetail";

    @RouterOperations({
            @RouterOperation(
                    path = BASE_PATH + "/create",
                    method = RequestMethod.POST,
                    beanClass = ComponentDetailHandler.class,
                    beanMethod = "create",
                    operation = @Operation(
                            tags = {"ComponentDetail API"},
                            operationId = "createComponentDetail",
                            summary = "Create new ComponentDetail",
                            requestBody = @RequestBody(
                                    required = true,
                                    content = @Content(
                                            schema = @Schema(implementation = ComponentDetailRequestDTO.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "201", description = "CREATED",
                                            content = @Content(schema = @Schema(implementation = ComponentDetail.class))
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = BASE_PATH + "/update/{id}",
                    method = RequestMethod.PUT,
                    beanClass = ComponentDetailHandler.class,
                    beanMethod = "update",
                    operation = @Operation(
                            tags = {"ComponentDetail API"},
                            operationId = "updateComponentDetail",
                            summary = "Update existing ComponentDetail",
                            parameters = {
                                    @Parameter(
                                            name = "id",
                                            in = ParameterIn.PATH,
                                            required = true
                                    )
                            },
                            requestBody = @RequestBody(
                                    required = true,
                                    content = @Content(
                                            schema = @Schema(implementation = ComponentDetailRequestDTO.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "OK",
                                            content = @Content(schema = @Schema(implementation = ComponentDetail.class))
                                    )
                            }
                    )
            ),
//            @RouterOperation(
//                    path = BASE_PATH + "/changeStatus/{status}/{id}",
//                    method = RequestMethod.PATCH,
//                    beanClass = ComponentDetailHandler.class,
//                    beanMethod = "changeStatus",
//                    operation = @Operation(
//                            tags = {"ComponentDetail API"},
//                            operationId = "changeComponentDetailStatus",
//                            summary = "Inactive or reactive ComponentDetail",
//                            parameters = {
//                                    @Parameter(
//                                            name = "status",
//                                            in = ParameterIn.PATH,
//                                            description = "Status of ComponentDetail. (true): Reactivate, (false): deactivate",
//                                            required = true,
//                                            schema = @Schema(type = "boolean")
//                                    ),
//                                    @Parameter(
//                                            name = "id",
//                                            in = ParameterIn.PATH,
//                                            description = "Id of ComponentDetail",
//                                            required = true,
//                                            schema = @Schema(type = "string")
//                                    )
//                            },
//                            responses = {
//                                    @ApiResponse(responseCode = "204", description = "NO CONTENT")
//                            }
//                    )
//            ),
            @RouterOperation(
                    path = BASE_PATH + "/{id}",
                    method = RequestMethod.GET,
                    beanClass = ComponentDetailHandler.class,
                    beanMethod = "getById",
                    operation = @Operation(
                            tags = {"ComponentDetail API"},
                            operationId = "getComponentDetailById",
                            summary = "Get ComponentDetail by id",
                            parameters = {
                                    @Parameter(
                                            name = "id",
                                            in = ParameterIn.PATH,
                                            description = "Id of ComponentDetail",
                                            required = true,
                                            schema = @Schema(type = "string")
                                    )
                            },
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "OK",
                                            content = @Content(schema = @Schema(implementation = ComponentDetail.class))
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = BASE_PATH + "/findByStatus/{status}",
                    method = RequestMethod.GET,
                    beanClass = ComponentDetailHandler.class,
                    beanMethod = "getByStatus",
                    operation = @Operation(
                            tags = {"ComponentDetail API"},
                            operationId = "getComponentDetailByStatus",
                            summary = "Get ComponentDetail by status",
                            parameters = {
                                    @Parameter(
                                            name = "status",
                                            in = ParameterIn.PATH,
                                            description = "Status of item. " +
                                                    "(true): Actives, (false): Inactives",
                                            required = true,
                                            schema = @Schema(type = "boolean")
                                    )
                            },
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "OK",
                                            content = @Content(schema = @Schema(implementation = ComponentDetail.class))
                                    )
                            }
                    )
            )
    })

    @Bean
    public RouterFunction<ServerResponse> componentDetailRoutes(ComponentDetailHandler handler) {
        return RouterFunctions
                .nest(path(BASE_PATH),
                        route(POST("/create"), handler::create)
                                .andRoute(PUT("/update/{id}"), handler::update)
                                .andRoute(GET("/{id}"), handler::getById)
                                .andRoute(GET("/findByStatus/{status}"), handler::getByStatus)
                );
    }
}
