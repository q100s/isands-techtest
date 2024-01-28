package ru.isands.appliancescatalog.service;

import ru.isands.appliancescatalog.dto.CreateDeviceTypeDto;
import ru.isands.appliancescatalog.dto.DeviceTypeDto;
import ru.isands.appliancescatalog.entity.DeviceType;

public interface DeviceTypeService {
    DeviceType getById(int id);

    DeviceTypeDto createNewDeviceType(CreateDeviceTypeDto newDeviceType);

    DeviceTypeDto updateDeviceType(Integer deviceTypeId, DeviceType newProperties);
}