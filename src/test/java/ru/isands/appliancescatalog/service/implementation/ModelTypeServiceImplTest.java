package ru.isands.appliancescatalog.service.implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.isands.appliancescatalog.dto.CreateModelTypeDto;
import ru.isands.appliancescatalog.dto.ModelTypeDto;
import ru.isands.appliancescatalog.entity.DeviceType;
import ru.isands.appliancescatalog.entity.ModelType;
import ru.isands.appliancescatalog.exception.ModelTypeNotFoundException;
import ru.isands.appliancescatalog.repository.ModelTypeRepository;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ModelTypeServiceImplTest {
    @Mock
    private ModelTypeRepository repository;

    @Mock
    private DeviceTypeServiceImpl deviceTypeService;

    @InjectMocks
    private ModelTypeServiceImpl service;

    @Test
    void testGetByIdPositive() {
        ModelType existingModelType = getModelType();
        when(repository.findById(2)).thenReturn(Optional.of(existingModelType));
        ModelType result = service.getById(2);

        assertEquals(2, result.getId());
        assertEquals("TestModelType", result.getModelTypeName());
        verify(repository, times(1)).findById(2);
    }

    @Test
    void testGetByIdNegative() {
        when(repository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ModelTypeNotFoundException.class, () -> service.getById(1));
        verify(repository, times(1)).findById(1);
    }

    @Test
    void testCreateNewModelTypeByDeviceTypeId() {
        DeviceType extendedDeviceType = getDeviceType();
        CreateModelTypeDto newProperties = getCreateModelTypeDto();
        when(deviceTypeService.getById(1)).thenReturn(extendedDeviceType);
        ModelType newModelType = getModelType();
        when(repository.save(any(ModelType.class))).thenReturn(newModelType);

        ModelTypeDto result = service.createNewModelTypeByDeviceTypeId(1, newProperties);
        assertEquals(2, result.getId());
        assertEquals("TestModelType", result.getModelTypeName());
        verify(deviceTypeService, times(1)).getById(1);
        verify(deviceTypeService, times(1)).updateDeviceType(eq(1), any(DeviceType.class));
        verify(repository, times(1)).save(any(ModelType.class));
    }

    @Test
    void testUpdateModelTypePositive() {
        ModelType existingModelType = getModelType();
        when(repository.findById(2)).thenReturn(Optional.of(existingModelType));
        when(repository.save(any(ModelType.class))).thenReturn(existingModelType);
        ModelType newProperties = new ModelType();
        newProperties.setModelTypeName("UpdatedModelType");
        ModelType result = service.updateModelType(2, newProperties);

        assertEquals(2, result.getId());
        assertEquals("UpdatedModelType", result.getModelTypeName());
        verify(repository, times(1)).findById(2);
        verify(repository, times(1)).save(existingModelType);
    }

    @Test
    void testUpdateModelTypeNegative() {
        ModelType newProperties = getModelType();

        when(repository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ModelTypeNotFoundException.class, () -> service.updateModelType(1, newProperties));
        verify(repository, times(1)).findById(1);
        verify(repository, never()).save(any(ModelType.class));
    }

    private static ModelType getModelType() {
        ModelType existingModelType = new ModelType();
        existingModelType.setId(2);
        existingModelType.setModelTypeName("TestModelType");
        return existingModelType;
    }

    private static CreateModelTypeDto getCreateModelTypeDto() {
        CreateModelTypeDto newProperties = new CreateModelTypeDto();
        newProperties.setModelTypeName("TestModelType");
        return newProperties;
    }

    private static DeviceType getDeviceType() {
        DeviceType extendedDeviceType = new DeviceType();
        extendedDeviceType.setId(1);
        extendedDeviceType.setDeviceTypeName("TestDeviceType");
        extendedDeviceType.setModelTypes(new ArrayList<>());
        return extendedDeviceType;
    }
}