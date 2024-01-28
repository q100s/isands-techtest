package ru.isands.appliancescatalog.service;

import ru.isands.appliancescatalog.dto.CreateModelTypeDto;
import ru.isands.appliancescatalog.dto.ModelTypeDto;
import ru.isands.appliancescatalog.entity.ModelType;

public interface ModelTypeService {
    ModelType getById(Integer id);

    ModelTypeDto createNewModelTypeByDeviceTypeId(Integer deviceTypeId, CreateModelTypeDto newModelType);

    ModelType updateModelType(Integer modelTypeId, ModelType newProperties);
}