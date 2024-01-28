package ru.isands.appliancescatalog.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.isands.appliancescatalog.dto.CreateModelTypeDto;
import ru.isands.appliancescatalog.dto.ModelTypeDto;
import ru.isands.appliancescatalog.entity.DeviceType;
import ru.isands.appliancescatalog.entity.ModelType;
import ru.isands.appliancescatalog.exception.ModelTypeNotFoundException;
import ru.isands.appliancescatalog.repository.ModelTypeRepository;
import ru.isands.appliancescatalog.service.ModelTypeService;

import java.util.Optional;

import static ru.isands.appliancescatalog.dto.mapper.ModelTypeMapper.mapIntoModelTypeDto;
import static ru.isands.appliancescatalog.dto.mapper.ModelTypeMapper.mapIntoModelTypeEntity;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModelTypeServiceImpl implements ModelTypeService {
    private final ModelTypeRepository repository;
    private final DeviceTypeServiceImpl deviceTypeService;

    @Override
    public ModelType getById(Integer id) {
        return repository.findById(id).orElseThrow(ModelTypeNotFoundException::new);
    }

    @Override
    public ModelType updateModelType(Integer modelTypeId, ModelType newProperties) {
        ModelType editedModelType = repository.findById(modelTypeId).orElseThrow(ModelTypeNotFoundException::new);
        Optional.ofNullable(newProperties.getModelTypeName()).ifPresent(editedModelType::setModelTypeName);
        Optional.ofNullable(newProperties.getDeviceType()).ifPresent(editedModelType::setDeviceType);
        Optional.ofNullable(newProperties.getModels()).ifPresent(editedModelType::setModels);
        return repository.save(editedModelType);
    }

    @Override
    public ModelTypeDto createNewModelTypeByDeviceTypeId(Integer deviceTypeId, CreateModelTypeDto newProperties) {
        DeviceType extendedDeviceType = deviceTypeService.getById(deviceTypeId);
        ModelType newModelType = mapIntoModelTypeEntity(newProperties);
        newModelType.setDeviceType(extendedDeviceType);
        updateDeviceType(deviceTypeId, newModelType);
        return mapIntoModelTypeDto(repository.save(newModelType));
    }

    private void updateDeviceType(Integer deviceTypeId, ModelType newModelType) {
        DeviceType extendedDeviceType = deviceTypeService.getById(deviceTypeId);
        extendedDeviceType.getModelTypes().add(newModelType);
        deviceTypeService.updateDeviceType(deviceTypeId, extendedDeviceType);
    }
}