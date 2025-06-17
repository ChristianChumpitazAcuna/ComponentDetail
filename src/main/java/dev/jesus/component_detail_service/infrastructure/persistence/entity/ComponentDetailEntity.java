package dev.jesus.component_detail_service.infrastructure.persistence.entity;

import dev.jesus.component_detail_service.domain.in.enums.ComponentType;
import dev.jesus.component_detail_service.domain.in.enums.StatusType;
import io.r2dbc.postgresql.codec.Json;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Table(value = "component_detail")
public class ComponentDetailEntity {
    @Id
    private Long id;
    private ComponentType componentType;
    private String componentId;
    private Json componentAttributes;
    private String heritageCode;
    private Integer yearHeritageCode;
    private Integer year;
    private String serialNumber;
    private String internalCode;
    private String observation;
    private List<String> imageUri;
    private StatusType statusType;
    private LocalDateTime creationTime;
    private LocalDateTime updateTime;
    private Boolean status;
}
