package ru.isands.appliancescatalog.dto.mapper;

import org.junit.jupiter.api.Test;
import ru.isands.appliancescatalog.dto.CreateDeviceTypeDto;
import ru.isands.appliancescatalog.dto.DeviceTypeDto;
import ru.isands.appliancescatalog.entity.DeviceType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DeviceTypeMapperTest {
    @Test
    void testMapIntoDeviceTypeDto() {
        final DeviceType entity = createEntity();

        DeviceTypeDto dto = DeviceTypeMapper.mapIntoDeviceTypeDto(entity);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getDeviceTypeName(), dto.getDeviceTypeName());
    }

    @Test
    void testMapIntoDeviceTypeEntity() {
        final CreateDeviceTypeDto dto = createDto();

        DeviceType entity = DeviceTypeMapper.mapIntoDeviceTypeEntity(dto);
        assertNull(entity.getId());
        assertEquals(dto.getDeviceTypeName(), entity.getDeviceTypeName());
    }

    private static DeviceType createEntity() {
        DeviceType entity = new DeviceType();
        entity.setId(1);
        entity.setDeviceTypeName("TestDeviceType");
        return entity;
    }

    private static CreateDeviceTypeDto createDto() {
        CreateDeviceTypeDto dto = new CreateDeviceTypeDto();
        dto.setDeviceTypeName("TestDeviceType");
        return dto;
    }
}