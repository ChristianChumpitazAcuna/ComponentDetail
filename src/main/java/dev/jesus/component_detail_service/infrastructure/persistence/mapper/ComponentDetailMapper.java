package dev.jesus.component_detail_service.infrastructure.persistence.mapper;

import dev.jesus.component_detail_service.domain.in.model.ComponentDetail;
import dev.jesus.component_detail_service.infrastructure.persistence.entity.ComponentDetailEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComponentDetailMapper {
    ComponentDetailEntity domainToEntity(ComponentDetail domain);

    ComponentDetail entityToDomain(ComponentDetailEntity entity);
}
