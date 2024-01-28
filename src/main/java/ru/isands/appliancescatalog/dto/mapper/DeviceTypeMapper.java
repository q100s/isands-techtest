package ru.isands.appliancescatalog.dto.mapper;

import org.springframework.stereotype.Component;
import ru.isands.appliancescatalog.dto.CreateDeviceTypeDto;
import ru.isands.appliancescatalog.dto.DeviceTypeDto;
import ru.isands.appliancescatalog.entity.DeviceType;

import java.util.Optional;

@Component
public class DeviceTypeMapper {
    public static DeviceTypeDto mapIntoDeviceTypeDto(DeviceType entity) {
        DeviceTypeDto dto = new DeviceTypeDto();
        dto.setId(entity.getId());
        Optional.ofNullable(entity.getDeviceTypeName()).ifPresent(dto::setDeviceTypeName);
        return dto;
    }

    public static DeviceType mapIntoDeviceTypeEntity(CreateDeviceTypeDto dto) {
        DeviceType entity = new DeviceType();
        Optional.ofNullable(dto.getDeviceTypeName()).ifPresent(entity::setDeviceTypeName);
        return entity;
    }
}