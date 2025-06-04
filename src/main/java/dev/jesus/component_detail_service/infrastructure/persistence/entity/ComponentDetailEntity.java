package dev.jesus.component_detail_service.infrastructure.persistence.entity;

import dev.jesus.component_detail_service.domain.in.enums.StatusType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;

@Document(collection = "component_detail")
@Getter
@Setter
public class ComponentDetailEntity {
    @Id
    private String id;
    private int heritageCode;
    private Year yearHeritageCode;
    private Year year;
    private String serialNumber;
    private String internalCode;
    private String componentId;
    private String observation;
    private List<String> imageUri;
    private StatusType componentStatus;
    private LocalDateTime creationTime;
    private LocalDateTime updateTime;
    private Boolean status;
}
