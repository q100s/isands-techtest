package ru.isands.appliancescatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.isands.appliancescatalog.dto.CreateModelDto;
import ru.isands.appliancescatalog.dto.ModelDto;
import ru.isands.appliancescatalog.service.implementation.ModelServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@WebMvcTest(ModelController.class)
public class ModelControllerTest {
    static List<ModelDto> models = new ArrayList<>();
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ModelServiceImpl service;

    @Test
    void testGetAllSortedByName() throws Exception {
        when(service.getAllSortedByModelName()).thenReturn(models);

        mockMvc.perform(MockMvcRequestBuilders.get("/model/sorted/by-model-name"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        verify(service, times(1)).getAllSortedByModelName();
    }

    @Test
    void testGetAllSortedByPrice() throws Exception {
        List<ModelDto> models = new ArrayList<>();
        when(service.getAllSortedByPrice()).thenReturn(models);

        mockMvc.perform(MockMvcRequestBuilders.get("/model/sorted/by-price"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
        verify(service, times(1)).getAllSortedByPrice();
    }

    @Test
    void testGetAllFilteredByModelName() throws Exception {
        when(service.getAllByModelName("TestModel")).thenReturn(models);

        mockMvc.perform(MockMvcRequestBuilders.get("/model/filtered/by-model-name")
                        .param("modelName", "TestModel"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
        verify(service, times(1)).getAllByModelName(eq("TestModel"));
    }

    @Test
    void testGetAllFilteredByDeviceTypeName() throws Exception {
        when(service.filterByDeviceTypeName("TestDeviceType")).thenReturn(models);

        mockMvc.perform(MockMvcRequestBuilders.get("/model/filtered/by-device-type-name")
                        .param("deviceTypeName", "TestDeviceType"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
        verify(service, times(1)).filterByDeviceTypeName(eq("TestDeviceType"));
    }

    @Test
    void testGetAllFilteredByDeviceTypeId() throws Exception {
        when(service.filterByDeviceTypeId(1)).thenReturn(models);

        mockMvc.perform(MockMvcRequestBuilders.get("/model/filtered/by-device-type-1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
        verify(service, times(1)).filterByDeviceTypeId(eq(1));
    }

    @Test
    void testGetAllFilteredByColor() throws Exception {
        when(service.filterByColor("TestColor")).thenReturn(models);

        mockMvc.perform(MockMvcRequestBuilders.get("/model/filtered/by-color")
                        .param("color", "TestColor"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
        verify(service, times(1)).filterByColor(eq("TestColor"));
    }

    @Test
    void testGetAllFilteredByPriceRange() throws Exception {
        when(service.filterByPriceRange(100, 500)).thenReturn(models);

        mockMvc.perform(MockMvcRequestBuilders.get("/model/filtered/by-price-range")
                        .param("minPrice", "100")
                        .param("maxPrice", "500"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
        verify(service, times(1)).filterByPriceRange(eq(100), eq(500));
    }

    @Test
    void testGetAllFilteredByDeviceTypeIdAndSpecs() throws Exception {
        when(service.filterByDeviceTypeIdAndSpecs(1, "SpecName", "SpecValue"))
                .thenReturn(models);

        mockMvc.perform(MockMvcRequestBuilders.get("/model/filtered/by-device-type-1-and-specs")
                        .param("specName", "TestSpec")
                        .param("specValue", "TestValue"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
        verify(service, times(1)).filterByDeviceTypeIdAndSpecs(eq(1),
                eq("TestSpec"), eq("TestValue"));
    }

    @Test
    void testAddNewModel() throws Exception {
        CreateModelDto newModelDto = new CreateModelDto();
        ModelDto createdModel = new ModelDto();

        when(service.createNewModelByModelTypeId(eq(1), any(CreateModelDto.class))).thenReturn(createdModel);

        mockMvc.perform(MockMvcRequestBuilders.post("/model/by-model-type/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newModelDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(createdModel.getId()));
        verify(service, times(1)).createNewModelByModelTypeId(eq(1),
                any(CreateModelDto.class));
    }
}