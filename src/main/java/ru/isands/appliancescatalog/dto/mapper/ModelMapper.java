package ru.isands.appliancescatalog.dto.mapper;

import org.springframework.stereotype.Component;
import ru.isands.appliancescatalog.dto.CreateModelDto;
import ru.isands.appliancescatalog.dto.ModelDto;
import ru.isands.appliancescatalog.entity.Model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ModelMapper {
    public static ModelDto mapIntoModelDto(Model entity) {
        ModelDto dto = new ModelDto();
        dto.setId(entity.getId());
        Optional.ofNullable(entity.getSerialNumber()).ifPresent(dto::setSerialNumber);
        Optional.ofNullable(entity.getPriceInKopecks()).ifPresent(dto::setPriceInKopecks);
        Optional.ofNullable(entity.getProducingCountry()).ifPresent(dto::setProducingCountry);
        Optional.ofNullable(entity.getProducingCompany()).ifPresent(dto::setProducingCompany);
        Optional.ofNullable(entity.getColor()).ifPresent(dto::setColor);
        Optional.ofNullable(entity.getDimensions()).ifPresent(dto::setDimensions);
        dto.setOnlineOrdered(entity.isOnlineOrdered());
        dto.setInstallmentPaid(entity.isInstallmentPaid());
        dto.setInStock(entity.isInStock());
        Optional.ofNullable(entity.getDeviceSpecs()).ifPresent(dto::setDeviceSpecs);
        Optional.ofNullable(entity.getTypeSpecs()).ifPresent(dto::setTypeSpecs);
        Optional.ofNullable(entity.getModelName()).ifPresent(dto::setModelName);
        Optional.ofNullable(entity.getModelSpecs()).ifPresent(dto::setModelSpecs);
        if (entity.getDeviceType() != null) {
            Optional.ofNullable(entity.getDeviceType().getId()).ifPresent(dto::setDeviceTypeId);
        }
        if (entity.getModelType() != null) {
            Optional.ofNullable(entity.getModelType().getId()).ifPresent(dto::setModelTypeId);
        }
        return dto;
    }

    public static Model mapIntoModelEntity(CreateModelDto dto) {
        Model entity = new Model();
        Optional.ofNullable(dto.getSerialNumber()).ifPresent(entity::setSerialNumber);
        Optional.ofNullable(dto.getPriceInKopecks()).ifPresent(entity::setPriceInKopecks);
        Optional.ofNullable(dto.getProducingCountry()).ifPresent(entity::setProducingCountry);
        Optional.ofNullable(dto.getProducingCompany()).ifPresent(entity::setProducingCompany);
        Optional.ofNullable(dto.getColor()).ifPresent(entity::setColor);
        Optional.ofNullable(dto.getDimensions()).ifPresent(entity::setDimensions);
        entity.setOnlineOrdered(dto.isOnlineOrdered());
        entity.setInstallmentPaid(dto.isInstallmentPaid());
        entity.setInStock(dto.isInStock());
        Optional.ofNullable(dto.getModelName()).ifPresent(entity::setModelName);
        Optional.ofNullable(dto.getModelName()).ifPresent(entity::setModelName);
        Optional.ofNullable(dto.getDeviceSpecs()).ifPresent(entity::setDeviceSpecs);
        Optional.ofNullable(dto.getTypeSpecs()).ifPresent(entity::setTypeSpecs);
        Optional.ofNullable(dto.getModelSpecs()).ifPresent(entity::setModelSpecs);
        return entity;
    }

    public static List<ModelDto> mapIntoDto(List<Model> results) {
        return results.stream()
                .map(ModelMapper::mapIntoModelDto)
                .collect(Collectors.toList());
    }
}
