package dev.jesus.component_detail_service.util.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jesus.component_detail_service.domain.in.model.ComponentDetail;
import dev.jesus.component_detail_service.domain.out.model.ComponentProperties;
import dev.jesus.component_detail_service.infrastructure.persistence.entity.ComponentDetailEntity;
import io.r2dbc.postgresql.codec.Json;
import org.springframework.stereotype.Component;

@Component
public class CustomConverter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ComponentDetailEntity writeDomainToEntity(ComponentDetail componentDetail) {
        try {
            ComponentDetailEntity entity = new ComponentDetailEntity();
            entity.setId(componentDetail.getId());
            entity.setComponentType(componentDetail.getComponentType());
            entity.setComponentId(componentDetail.getComponentId());
            entity.setComponentAttributes(Json.of(objectMapper.writeValueAsString(
                    componentDetail.getComponentAttributes())));
            entity.setHeritageCode(componentDetail.getHeritageCode());
            entity.setYearHeritageCode(componentDetail.getYearHeritageCode());
            entity.setYear(componentDetail.getYear());
            entity.setSerialNumber(componentDetail.getSerialNumber());
            entity.setInternalCode(componentDetail.getInternalCode());
            entity.setObservation(componentDetail.getObservation());
            entity.setImageUri(componentDetail.getImageUri());
            entity.setStatusType(componentDetail.getStatusType());
            entity.setCreationTime(componentDetail.getCreationTime());
            entity.setUpdateTime(componentDetail.getUpdateTime());
            entity.setStatus(componentDetail.getStatus());
            return entity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ComponentDetail readEntityToDomain(ComponentDetailEntity entity) {
        try {
            ComponentDetail domain = new ComponentDetail();
            domain.setId(entity.getId());
            domain.setComponentType(entity.getComponentType());
            domain.setComponentId(entity.getComponentId());
            domain.setComponentAttributes(objectMapper.readValue(
                    entity.getComponentAttributes().asString(), ComponentProperties.class));
            domain.setHeritageCode(entity.getHeritageCode());
            domain.setYearHeritageCode(entity.getYearHeritageCode());
            domain.setYear(entity.getYear());
            domain.setSerialNumber(entity.getSerialNumber());
            domain.setInternalCode(entity.getInternalCode());
            domain.setObservation(entity.getObservation());
            domain.setImageUri(entity.getImageUri());
            domain.setStatusType(entity.getStatusType());
            domain.setCreationTime(entity.getCreationTime());
            domain.setUpdateTime(entity.getUpdateTime());
            domain.setStatus(entity.getStatus());
            return domain;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
