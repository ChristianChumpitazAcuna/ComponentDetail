package dev.jesus.component_detail_service.infrastructure.persistence.entity;

import dev.jesus.component_detail_service.domain.in.enums.StatusType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "component_detail")
@Getter
@Setter
public class ComponentDetailEntity {
    @Id
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
