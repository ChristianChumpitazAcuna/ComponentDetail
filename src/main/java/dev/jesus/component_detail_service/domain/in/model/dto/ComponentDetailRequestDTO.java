package dev.jesus.component_detail_service.domain.in.model.dto;

import dev.jesus.component_detail_service.domain.in.enums.ComponentType;
import dev.jesus.component_detail_service.domain.in.enums.StatusType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Year;
import java.util.List;

@Getter
@Setter
public class ComponentDetailRequestDTO {
    @NotNull(message = "The field is required")
    @Schema(description = "Component type", example = "ACCESSORY")
    private ComponentType componentType;

    @NotNull(message = "The field is required")
    @Schema(description = "Component detail component id", example = "123")
    private String componentId;

    @Pattern(regexp = "^[0-9]{12}$", message = "12 numerics digits are required")
    @Schema(description = "Component detail heritage code", example = "740012123344")
    private String heritageCode;

    @Schema(description = "Component detail year heritage code", example = "2022")
    private Integer yearHeritageCode;

    @Schema(description = "Component detail year", example = "2024")
    private Integer year;

    @NotNull(message = "The field is required")
    @Pattern(
            regexp = "^[a-zA-Z0-9-]+$",
            message = "Only letters, numbers and hyphens are allowed"
    )
    @Schema(description = "Component detail serial number", example = "X8GR012000")
    private String serialNumber;

    @Schema(description = "Component detail internal code", example = "120")
    private String internalCode;

    @Pattern(
            regexp = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ][a-zA-Z0-9áéíóúÁÉÍÓÚñÑ -]+$",
            message = "Only letters, numbers, accents, and hyphens are allowed"
    )
    @Schema(description = "Component detail observation",
            example = "The protective cable was found broken")
    private String observation;

    @Schema(description = "Component detail image URL",
            example = "[\"https://example.com/image.jpg\", \"https://example.com/image.jpg\"]")
    private List<@Pattern(
            regexp = "^(https?://.*\\.(?:png|jpg|jpeg|gif|webp|bmp))$",
            message = "Invalid URL format"
    ) String> imageUri;

    @NotNull(message = "The field is required")
    @Schema(description = "Component detail status: (REGULAR, RISK, BAD)", example = "REGULAR")
    private StatusType statusType;
}
