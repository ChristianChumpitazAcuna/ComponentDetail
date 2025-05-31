package dev.jesus.component_detail_service.domain.in.model.dto;

import dev.jesus.component_detail_service.domain.in.enums.StatusType;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComponentDetailRequestDTO {
    @Size(min = 12, max = 12, message = "12 numeric digits are required")
    private int heritageCode;

    private String year;

    private String serialNumber;

    private String internalCode;

    @NotBlank(message = "ComponentId is required")
    private String componentId;

    private String observation;

    @Pattern(
            regexp = "^(https?://).+\\.(png|jpg|jpeg|gif)$",
            message = "Image URI must be a valid URL ending with .png, .jpg, .jpeg, or .gif"
    )

    private String imageUri;

    @NotNull(message = "ComponentStatus is required")
    private StatusType componentStatus;
}
