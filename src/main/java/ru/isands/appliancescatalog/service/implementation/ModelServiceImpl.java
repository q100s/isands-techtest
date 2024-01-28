package ru.isands.appliancescatalog.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.isands.appliancescatalog.dto.CreateModelDto;
import ru.isands.appliancescatalog.dto.ModelDto;
import ru.isands.appliancescatalog.entity.DeviceType;
import ru.isands.appliancescatalog.entity.Model;
import ru.isands.appliancescatalog.entity.ModelType;
import ru.isands.appliancescatalog.repository.ModelRepository;
import ru.isands.appliancescatalog.service.DeviceTypeService;
import ru.isands.appliancescatalog.service.ModelService;
import ru.isands.appliancescatalog.service.ModelTypeService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.isands.appliancescatalog.dto.mapper.ModelMapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModelServiceImpl implements ModelService {
    private final ModelTypeService modelTypeService;
    private final DeviceTypeService deviceTypeService;
    private final ModelRepository repository;

    @Override
    public List<ModelDto> getAll() {
        return mapIntoDto(repository.findAll());
    }

    @Override
    public List<ModelDto> getAllSortedByModelName() {
        return getAll().stream()
                .sorted(Comparator.comparing(ModelDto::getModelName))
                .collect(Collectors.toList());
    }

    @Override
    public List<ModelDto> getAllSortedByPrice() {
        return getAll().stream()
                .sorted(Comparator.comparing(ModelDto::getPriceInKopecks))
                .collect(Collectors.toList());
    }

    public List<ModelDto> getAllByModelName(String modelName) {
        return mapIntoDto(repository.findByModelName(modelName));
    }


    @Override
    public List<ModelDto> filterByDeviceTypeName(String deviceTypeName) {
        return mapIntoDto(repository.findByDeviceTypeNameIgnoreCase(deviceTypeName));
    }

    @Override
    public List<ModelDto> filterByDeviceTypeId(Integer deviceTypeId) {
        return mapIntoDto(repository.findByDeviceTypeId(deviceTypeId));
    }

    @Override
    public List<ModelDto> filterByColor(String color) {
        return mapIntoDto(repository.findByColorIgnoreCase(color));
    }

    @Override
    public List<ModelDto> filterByPriceRange(Integer minPrice, Integer maxPrice) {
        return mapIntoDto(repository.findByPriceRange(minPrice, maxPrice));
    }

    @Override
    public List<ModelDto> filterByDeviceTypeIdAndSpecs(Integer deviceTypeId, String specName, String specValue) {
        return mapIntoDto(repository.findByDeviceTypeIdAndModelSpecs(deviceTypeId, specName, specValue));
    }

    @Override
    public ModelDto createNewModelByModelTypeId(Integer modelTypeId, CreateModelDto newProperties) {
        ModelType extendedModelType = modelTypeService.getById(modelTypeId);
        Model newModel = mapIntoModelEntity(newProperties);
        updateModelType(modelTypeId, newModel);
        updateDeviceType(extendedModelType, newModel);
        repository.save(newModel);
        return mapIntoModelDto(newModel);
    }

    private void updateModelType(Integer modelTypeId, Model newModel) {
        ModelType extendedModelType = modelTypeService.getById(modelTypeId);
        newModel.setModelType(extendedModelType);
        extendedModelType.getModels().add(newModel);
        modelTypeService.updateModelType(modelTypeId, extendedModelType);
    }

    private void updateDeviceType(ModelType extendedModelType, Model newModel) {
        if (extendedModelType.getDeviceType() != null) {
            DeviceType extendedDeviceType = deviceTypeService.getById(extendedModelType.getDeviceType().getId());
            newModel.setDeviceType(extendedDeviceType);
            deviceTypeService.updateDeviceType(extendedDeviceType.getId(), extendedDeviceType);
        }
    }
}