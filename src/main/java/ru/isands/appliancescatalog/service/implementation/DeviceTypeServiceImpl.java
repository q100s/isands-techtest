package ru.isands.appliancescatalog.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.isands.appliancescatalog.dto.CreateDeviceTypeDto;
import ru.isands.appliancescatalog.dto.DeviceTypeDto;
import ru.isands.appliancescatalog.entity.DeviceType;
import ru.isands.appliancescatalog.exception.DeviceTypeNotFoundException;
import ru.isands.appliancescatalog.repository.DeviceTypeRepository;
import ru.isands.appliancescatalog.service.DeviceTypeService;

import java.util.Optional;

import static ru.isands.appliancescatalog.dto.mapper.DeviceTypeMapper.mapIntoDeviceTypeDto;
import static ru.isands.appliancescatalog.dto.mapper.DeviceTypeMapper.mapIntoDeviceTypeEntity;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceTypeServiceImpl implements DeviceTypeService {
    private final DeviceTypeRepository repository;

    @Override
    public DeviceType getById(int id) {
        return repository.findById(id).orElseThrow(DeviceTypeNotFoundException::new);
    }

    @Override
    public DeviceTypeDto createNewDeviceType(CreateDeviceTypeDto newDeviceTypeDto) {
        return mapIntoDeviceTypeDto(repository.save(mapIntoDeviceTypeEntity(newDeviceTypeDto)));
    }

    @Override
    public DeviceTypeDto updateDeviceType(Integer deviceTypeId, DeviceType newProperties) {
        DeviceType updatedDeviceType = repository.findById(deviceTypeId).orElseThrow(DeviceTypeNotFoundException::new);
        Optional.ofNullable(newProperties.getDeviceTypeName()).ifPresent(updatedDeviceType::setDeviceTypeName);
        Optional.ofNullable(newProperties.getModelTypes()).ifPresent(updatedDeviceType::setModelTypes);
        Optional.ofNullable(newProperties.getModels()).ifPresent(updatedDeviceType::setModels);
        repository.save(updatedDeviceType);
        return mapIntoDeviceTypeDto(updatedDeviceType);
    }
}
