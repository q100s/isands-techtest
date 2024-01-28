package ru.isands.appliancescatalog.service.implementation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.isands.appliancescatalog.dto.ModelDto;
import ru.isands.appliancescatalog.entity.DeviceType;
import ru.isands.appliancescatalog.entity.Model;
import ru.isands.appliancescatalog.repository.ModelRepository;
import ru.isands.appliancescatalog.service.DeviceTypeService;
import ru.isands.appliancescatalog.service.ModelTypeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ModelServiceImplTest {
    static List<Model> models = new ArrayList<>();
    @Mock
    private ModelTypeService modelTypeService;

    @Mock
    private DeviceTypeService deviceTypeService;

    @Mock
    private ModelRepository repository;

    @InjectMocks
    private ModelServiceImpl service;

    @BeforeAll
    static void init() {
        Model model1 = new Model();
        model1.setId(1);
        model1.setModelName("CModel");
        model1.setPriceInKopecks(10_000);
        model1.setColor("Black");
        DeviceType deviceType1 = new DeviceType();
        deviceType1.setDeviceTypeName("CDeviceType");
        deviceType1.setId(1);
        model1.setDeviceType(deviceType1);
        Map<String, String> model1Specs = new HashMap<>();
        model1Specs.put("SpecNameC", "SpecValueC");
        model1.setModelSpecs(model1Specs);

        Model model2 = new Model();
        model2.setId(2);
        model2.setModelName("AModel");
        model2.setPriceInKopecks(100_000);
        model2.setColor("White");
        model1.getDeviceType().setDeviceTypeName("ADeviceType");
        DeviceType deviceType2 = new DeviceType();
        deviceType2.setDeviceTypeName("ADeviceType");
        deviceType1.setId(2);
        model1.setDeviceType(deviceType2);
        Map<String, String> model2Specs = new HashMap<>();
        model2Specs.put("SpecNameA", "SpecValueA");
        model2.setModelSpecs(model2Specs);

        Model model3 = new Model();
        model3.setId(3);
        model3.setModelName("BModel");
        model3.setPriceInKopecks(50_000);
        model3.setColor("Black");
        model1.getDeviceType().setDeviceTypeName("BDeviceType");
        DeviceType deviceType3 = new DeviceType();
        deviceType3.setDeviceTypeName("BDeviceType");
        deviceType1.setId(3);
        model1.setDeviceType(deviceType3);
        Map<String, String> model3Specs = new HashMap<>();
        model3Specs.put("SpecNameB", "SpecValueB");
        model3.setModelSpecs(model3Specs);

        models.add(model1);
        models.add(model2);
        models.add(model3);
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(models);
        List<ModelDto> result = service.getAll();

        assertEquals(3, result.size());
        assertEquals("CModel", result.get(0).getModelName());
        assertEquals("AModel", result.get(1).getModelName());
        assertEquals("BModel", result.get(2).getModelName());
    }

    @Test
    void testGetAllSortedByModelName() {
        when(repository.findAll()).thenReturn(models);
        List<ModelDto> result = service.getAllSortedByModelName();

        assertEquals(3, result.size());
        assertEquals("AModel", result.get(0).getModelName());
        assertEquals(2, result.get(0).getId());
        assertEquals(100_000, result.get(0).getPriceInKopecks());
        assertEquals("BModel", result.get(1).getModelName());
        assertEquals(3, result.get(1).getId());
        assertEquals(50_000, result.get(1).getPriceInKopecks());
        assertEquals("CModel", result.get(2).getModelName());
        assertEquals(1, result.get(2).getId());
        assertEquals(10_000, result.get(2).getPriceInKopecks());
    }

    @Test
    void testGetAllSortedByPrice() {
        when(repository.findAll()).thenReturn(models);
        List<ModelDto> result = service.getAllSortedByPrice();

        assertEquals(3, result.size());
        assertEquals("CModel", result.get(0).getModelName());
        assertEquals(1, result.get(0).getId());
        assertEquals(10_000, result.get(0).getPriceInKopecks());
        assertEquals("BModel", result.get(1).getModelName());
        assertEquals(3, result.get(1).getId());
        assertEquals(50_000, result.get(1).getPriceInKopecks());
        assertEquals("AModel", result.get(2).getModelName());
        assertEquals(2, result.get(2).getId());
        assertEquals(100_000, result.get(2).getPriceInKopecks());
    }

    @Test
    void testGetAllByModelName() {
        List<Model> expectedModels = setExpectedModels();
        when(repository.findByModelName("BModel")).thenReturn(expectedModels);
        List<ModelDto> result = service.getAllByModelName("BModel");

        assertEquals(1, result.size());
        assertEquals("BModel", result.get(0).getModelName());
    }

    @Test
    void testFilterByDeviceTypeName() {
        List<Model> expectedModels = setExpectedModels();
        when(repository.findByDeviceTypeNameIgnoreCase("BDeviceType")).thenReturn(expectedModels);
        List<ModelDto> result = service.filterByDeviceTypeName("BDeviceType");

        assertEquals(1, result.size());
        assertEquals("BModel", result.get(0).getModelName());
    }

    @Test
    void testFilterByDeviceTypeId() {
        List<Model> expectedModels = setExpectedModels();
        when((repository.findByDeviceTypeId(2))).thenReturn(expectedModels);
        List<ModelDto> result = service.filterByDeviceTypeId(2);

        assertEquals(1, result.size());
        assertEquals("BModel", result.get(0).getModelName());
    }

    @Test
    void testFilterByColor() {
        List<Model> expectedModels = setExpectedModels();
        expectedModels.add(models.get(0));
        when(repository.findByColorIgnoreCase("black")).thenReturn(expectedModels);
        List<ModelDto> result = service.filterByColor("black");

        assertEquals(2, result.size());
        assertEquals("CModel", result.get(1).getModelName());
        assertEquals("BModel", result.get(0).getModelName());
    }

    @Test
    void testFilterByPriceRange() {
        List<Model> expectedModels = setExpectedModels();
        expectedModels.add(models.get(1));
        when(repository.findByPriceRange(50_000, 100_000)).thenReturn(expectedModels);
        List<ModelDto> result = service.filterByPriceRange(50_000, 100_000);

        assertEquals(2, result.size());
        assertEquals("AModel", result.get(1).getModelName());
        assertEquals("BModel", result.get(0).getModelName());
    }

    @Test
    void testFilterByDeviceTypeIdAndSpecs() {
        List<Model> expectedModels = setExpectedModels();
        when(repository.findByDeviceTypeIdAndModelSpecs(3, "SpecNameB", "SpecValueB"))
                .thenReturn(expectedModels);
        List<ModelDto> result = service.filterByDeviceTypeIdAndSpecs(3,
                "SpecNameB", "SpecValueB");

        assertEquals(1, result.size());
        assertEquals("BModel", result.get(0).getModelName());
    }

    private static List<Model> setExpectedModels() {
        List<Model> expectedModels = new ArrayList<>();
        expectedModels.add(models.get(2));
        return expectedModels;
    }
}