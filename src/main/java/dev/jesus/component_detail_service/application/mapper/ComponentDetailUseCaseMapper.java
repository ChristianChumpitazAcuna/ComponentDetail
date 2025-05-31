package dev.jesus.component_detail_service.application.mapper;

import dev.jesus.component_detail_service.domain.in.model.ComponentDetail;
import dev.jesus.component_detail_service.domain.in.model.dto.ComponentDetailRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComponentDetailUseCaseMapper {
    ComponentDetail dtoToEntity(ComponentDetailRequestDTO dto);
}
