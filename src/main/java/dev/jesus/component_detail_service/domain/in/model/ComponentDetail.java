package dev.jesus.component_detail_service.domain.in.model;

import dev.jesus.component_detail_service.domain.in.enums.ComponentType;
import dev.jesus.component_detail_service.domain.in.enums.StatusType;
import dev.jesus.component_detail_service.domain.out.model.ComponentProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ComponentDetail {
    @Schema(description = "Unique Component detail identifier")
    private String id;

    @Schema(description = "Component type")
    private ComponentType componentType;

    @Schema(description = "Component detail component id")
    private String componentId;

    @Schema(description = "Component attributes")
    private ComponentProperties componentAttributes;

    @Schema(description = "Component detail heritage code")
    private int heritageCode;

    @Schema(description = "Component detail year heritage code")
    private Year yearHeritageCode;

    @Schema(description = "Component detail year")
    private Year year;

    @Schema(description = "Component detail serial number")
    private String serialNumber;

    @Schema(description = "Component detail internal code")
    private String internalCode;

    @Schema(description = "Component detail observation")
    private String observation;

    @Schema(description = "Component detail image URL")
    private List<String> imageUri;

    @Schema(description = "Component detail status: (REGULAR, RISK, BAD)")
    private StatusType componentStatus;

    @Schema(description = "Component detail creation time")
    private LocalDateTime creationTime;

    @Schema(description = "Component detail update time")
    private LocalDateTime updateTime;

    @Schema(description = "Component detail status: (active or inactive)")
    private Boolean status;
}
