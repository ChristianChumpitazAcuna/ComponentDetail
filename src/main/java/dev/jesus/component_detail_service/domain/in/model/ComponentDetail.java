package dev.jesus.component_detail_service.domain.in.model;

import dev.jesus.component_detail_service.domain.in.enums.StatusType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComponentDetail {
    private String id;
    private int heritageCode;
    private String year;
    private String serialNumber;
    private String internalCode;
    private String componentId;
    private String observation;
    private String imageUri;
    private StatusType componentStatus;
    private Boolean status;
}
