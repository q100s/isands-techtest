package ru.isands.appliancescatalog.dto.mapper;

import org.springframework.stereotype.Component;
import ru.isands.appliancescatalog.dto.CreateModelTypeDto;
import ru.isands.appliancescatalog.dto.ModelTypeDto;
import ru.isands.appliancescatalog.entity.ModelType;

import java.util.Optional;

@Component
public class ModelTypeMapper {
    public static ModelTypeDto mapIntoModelTypeDto(ModelType entity) {
        ModelTypeDto dto = new ModelTypeDto();
        dto.setId(entity.getId());
        Optional.ofNullable(entity.getModelTypeName()).ifPresent(dto::setModelTypeName);
        if (entity.getDeviceType() != null) {
            dto.setDeviceTypeId(entity.getDeviceType().getId());
        } else {
            dto.setDeviceTypeId(null);
        }
        return dto;
    }

    public static ModelType mapIntoModelTypeEntity(CreateModelTypeDto dto) {
        ModelType entity = new ModelType();
        Optional.ofNullable(dto.getModelTypeName()).ifPresent(entity::setModelTypeName);
        return entity;
    }
}