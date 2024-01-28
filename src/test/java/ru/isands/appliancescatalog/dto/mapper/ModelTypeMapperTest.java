package ru.isands.appliancescatalog.dto.mapper;

import org.junit.jupiter.api.Test;
import ru.isands.appliancescatalog.dto.CreateModelTypeDto;
import ru.isands.appliancescatalog.dto.ModelTypeDto;
import ru.isands.appliancescatalog.entity.DeviceType;
import ru.isands.appliancescatalog.entity.ModelType;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTypeMapperTest {
    @Test
    void testMapIntoModelTypeDto() {
        ModelType entity = getModelTypeEntity();
        DeviceType deviceType = getDeviceTypeEntity();
        entity.setDeviceType(deviceType);
        ModelTypeDto dto = ModelTypeMapper.mapIntoModelTypeDto(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getModelTypeName(), dto.getModelTypeName());
        assertEquals(entity.getDeviceType().getId(), dto.getDeviceTypeId());
    }

    @Test
    void testMapIntoModelTypeEntity() {
        CreateModelTypeDto dto = new CreateModelTypeDto();
        dto.setModelTypeName("TestModelType");
        ModelType entity = ModelTypeMapper.mapIntoModelTypeEntity(dto);

        assertNull(entity.getId());
        assertNotNull(entity.getModelTypeName());
        assertEquals(dto.getModelTypeName(), entity.getModelTypeName());
    }

    @Test
    void testMapIntoModelTypeDtoWithNullDeviceType() {
        final ModelType entity = getModelTypeEntity();
        ModelTypeDto dto = ModelTypeMapper.mapIntoModelTypeDto(entity);

        assertNull(dto.getDeviceTypeId());
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getModelTypeName(), dto.getModelTypeName());
    }

    private static ModelType getModelTypeEntity() {
        ModelType entity = new ModelType();
        entity.setId(1);
        entity.setModelTypeName("TestModelType");
        return entity;
    }

    private static DeviceType getDeviceTypeEntity() {
        DeviceType deviceType = new DeviceType();
        deviceType.setId(2);
        return deviceType;
    }
}