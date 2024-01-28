package ru.isands.appliancescatalog.service.implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.isands.appliancescatalog.dto.CreateDeviceTypeDto;
import ru.isands.appliancescatalog.dto.DeviceTypeDto;
import ru.isands.appliancescatalog.entity.DeviceType;
import ru.isands.appliancescatalog.exception.DeviceTypeNotFoundException;
import ru.isands.appliancescatalog.repository.DeviceTypeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeviceTypeServiceImplTest {
    @Mock
    private DeviceTypeRepository repository;

    @InjectMocks
    private DeviceTypeServiceImpl service;

    @Test
    void testGetByIdPositive() {
        DeviceType deviceType = getDeviceType();
        when(repository.findById(1)).thenReturn(Optional.of(deviceType));
        DeviceType result = service.getById(1);

        assertEquals(1, result.getId());
        assertEquals("TestDeviceType", result.getDeviceTypeName());
        verify(repository, times(1)).findById(1);
    }

    @Test
    void testGetByIdNegative() {
        when(repository.findById(1)).thenReturn(Optional.empty());
        assertThrows(DeviceTypeNotFoundException.class, () -> service.getById(1));
        verify(repository, times(1)).findById(1);
    }

    @Test
    void testCreateNewDeviceType() {
        CreateDeviceTypeDto newDeviceTypeDto = getCreateDeviceTypeDto();
        DeviceType savedDeviceType = getDeviceType();
        when(repository.save(any(DeviceType.class))).thenReturn(savedDeviceType);
        DeviceTypeDto result = service.createNewDeviceType(newDeviceTypeDto);

        assertEquals(1, result.getId());
        assertEquals("TestDeviceType", result.getDeviceTypeName());
        verify(repository, times(1)).save(any(DeviceType.class));
    }

    @Test
    void testUpdateDeviceTypePositive() {
        DeviceType existingDeviceType = getDeviceType();
        when(repository.findById(1)).thenReturn(Optional.of(existingDeviceType));
        when(repository.save(any(DeviceType.class))).thenReturn(existingDeviceType);
        DeviceType newProperties = getUpdatedDeviceType();
        DeviceTypeDto result = service.updateDeviceType(1, newProperties);

        assertEquals(1, result.getId());
        assertEquals("UpdatedDeviceType", result.getDeviceTypeName());
        verify(repository, times(1)).findById(1);
        verify(repository, times(1)).save(existingDeviceType);
    }

    @Test
    void testUpdateDeviceTypeNegative() {
        DeviceType newProperties = getUpdatedDeviceType();
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(DeviceTypeNotFoundException.class, () -> service.updateDeviceType(1, newProperties));
        verify(repository, times(1)).findById(1);
        verify(repository, never()).save(any(DeviceType.class));
    }

    private static DeviceType getDeviceType() {
        DeviceType deviceType = new DeviceType();
        deviceType.setId(1);
        deviceType.setDeviceTypeName("TestDeviceType");
        return deviceType;
    }

    private static DeviceType getUpdatedDeviceType() {
        DeviceType newProperties = new DeviceType();
        newProperties.setDeviceTypeName("UpdatedDeviceType");
        return newProperties;
    }

    private static CreateDeviceTypeDto getCreateDeviceTypeDto() {
        CreateDeviceTypeDto newDeviceTypeDto = new CreateDeviceTypeDto();
        newDeviceTypeDto.setDeviceTypeName("TestDeviceType");
        return newDeviceTypeDto;
    }
}