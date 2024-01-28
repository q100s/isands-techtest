package ru.isands.appliancescatalog.dto.mapper;

import org.junit.jupiter.api.Test;
import ru.isands.appliancescatalog.dto.CreateModelDto;
import ru.isands.appliancescatalog.dto.ModelDto;
import ru.isands.appliancescatalog.entity.DeviceType;
import ru.isands.appliancescatalog.entity.Model;
import ru.isands.appliancescatalog.entity.ModelType;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelMapperTest {
    @Test
    void testMapIntoModelDto() {
        Model entity = getModelEntity();
        ModelDto dto = ModelMapper.mapIntoModelDto(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getSerialNumber(), dto.getSerialNumber());
        assertEquals(entity.getPriceInKopecks(), dto.getPriceInKopecks());
        assertEquals(entity.getProducingCountry(), dto.getProducingCountry());
        assertEquals(entity.getProducingCompany(), dto.getProducingCompany());
        assertEquals(entity.getColor(), dto.getColor());
        assertEquals(entity.getDimensions(), dto.getDimensions());
        assertEquals(entity.isOnlineOrdered(), dto.isOnlineOrdered());
        assertEquals(entity.isInstallmentPaid(), dto.isInstallmentPaid());
        assertEquals(entity.isInStock(), dto.isInStock());
        assertEquals(entity.getModelName(), dto.getModelName());
        assertEquals(entity.getDeviceType().getId(), dto.getDeviceTypeId());
        assertEquals(entity.getModelType().getId(), dto.getModelTypeId());
    }

    @Test
    void testMapIntoModelEntity() {
        CreateModelDto dto = getCreateModelDto();
        Model entity = ModelMapper.mapIntoModelEntity(dto);

        assertEquals(entity.getSerialNumber(), dto.getSerialNumber());
        assertEquals(entity.getPriceInKopecks(), dto.getPriceInKopecks());
        assertEquals(entity.getProducingCountry(), dto.getProducingCountry());
        assertEquals(entity.getProducingCompany(), dto.getProducingCompany());
        assertEquals(entity.getColor(), dto.getColor());
        assertEquals(entity.getDimensions(), dto.getDimensions());
        assertEquals(entity.isOnlineOrdered(), dto.isOnlineOrdered());
        assertEquals(entity.isInstallmentPaid(), dto.isInstallmentPaid());
        assertEquals(entity.isInStock(), dto.isInStock());
        assertEquals(entity.getModelName(), dto.getModelName());
        assertEquals(entity.getDeviceSpecs().size(), 1);
        assertEquals(entity.getDeviceSpecs(), dto.getDeviceSpecs());
        assertEquals(entity.getTypeSpecs().size(), 2);
        assertEquals(entity.getTypeSpecs(), dto.getTypeSpecs());
        assertEquals(entity.getModelSpecs().size(), 3);
        assertEquals(entity.getModelSpecs(), dto.getModelSpecs());
    }

    private static Model getModelEntity() {
        Model entity = new Model();
        entity.setId(1);
        entity.setSerialNumber(123);
        entity.setPriceInKopecks(100_000);
        entity.setProducingCountry("TestCountry");
        entity.setProducingCompany("TestCompany");
        entity.setColor("TestColor");
        entity.setDimensions("TestDimensions");
        entity.setOnlineOrdered(true);
        entity.setInstallmentPaid(false);
        entity.setInStock(true);
        entity.setModelName("TestName");

        DeviceType deviceType = new DeviceType();
        deviceType.setId(2);
        entity.setDeviceType(deviceType);

        ModelType modelType = new ModelType();
        modelType.setId(3);
        entity.setModelType(modelType);
        return entity;
    }

    private static CreateModelDto getCreateModelDto() {
        CreateModelDto dto = new CreateModelDto();
        Map<String, String> testDeviceSpecs = new HashMap<>();
        testDeviceSpecs.put("spec1", "value1");

        Map<String, String> testTypeSpecs = new HashMap<>();
        testTypeSpecs.put("spec2", "value2");
        testTypeSpecs.put("spec3", "value3");

        Map<String, String> testModelSpecs = new HashMap<>();
        testModelSpecs.put("spec4", "value4");
        testModelSpecs.put("spec5", "value5");
        testModelSpecs.put("spec6", "value6");

        dto.setSerialNumber(1);
        dto.setPriceInKopecks(10_000);
        dto.setProducingCountry("TestCountry");
        dto.setProducingCompany("TestCompany");
        dto.setColor("TestColor");
        dto.setDimensions("TestDimensions");
        dto.setOnlineOrdered(false);
        dto.setInstallmentPaid(true);
        dto.setInStock(true);
        dto.setModelName("TestName");
        dto.setDeviceSpecs(testDeviceSpecs);
        dto.setTypeSpecs(testTypeSpecs);
        dto.setModelSpecs(testModelSpecs);
        return dto;
    }
}