package ru.isands.appliancescatalog.service;

import ru.isands.appliancescatalog.dto.CreateModelDto;
import ru.isands.appliancescatalog.dto.ModelDto;

import java.util.List;

public interface ModelService {
    List<ModelDto> getAll();

    List<ModelDto> getAllSortedByModelName();

    List<ModelDto> getAllSortedByPrice();

    List<ModelDto> getAllByModelName(String modelName);

    List<ModelDto> filterByDeviceTypeName(String deviceTypeName);

    List<ModelDto> filterByDeviceTypeId(Integer deviceTypeId);

    List<ModelDto> filterByColor(String color);

    List<ModelDto> filterByPriceRange(Integer minPrice, Integer maxPrice);

    List<ModelDto> filterByDeviceTypeIdAndSpecs(Integer deviceTypeId, String specName, String specValue);

    ModelDto createNewModelByModelTypeId(Integer modelTypeId, CreateModelDto newProperties);
}