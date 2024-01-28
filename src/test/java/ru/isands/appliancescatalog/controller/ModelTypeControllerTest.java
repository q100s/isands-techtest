package ru.isands.appliancescatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.isands.appliancescatalog.dto.CreateModelTypeDto;
import ru.isands.appliancescatalog.dto.ModelTypeDto;
import ru.isands.appliancescatalog.service.implementation.ModelTypeServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(ModelTypeController.class)
public class ModelTypeControllerTest {
    static ModelTypeDto modelTypeDto = new ModelTypeDto();
    static CreateModelTypeDto createModelTypeDto = new CreateModelTypeDto();
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ModelTypeServiceImpl service;

    @BeforeAll
    static void init() {
        modelTypeDto.setId(1);
        modelTypeDto.setModelTypeName("TestModelType");
        createModelTypeDto.setModelTypeName("TestModelType");
    }

    @Test
    void testAddNewModelType() throws Exception {
        when(service.createNewModelTypeByDeviceTypeId(any(Integer.class), any(CreateModelTypeDto.class)))
                .thenReturn(modelTypeDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/model-type/by-device-type/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createModelTypeDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(modelTypeDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.modelTypeName")
                        .value(modelTypeDto.getModelTypeName()));
        verify(service, times(1)).createNewModelTypeByDeviceTypeId(eq(1),
                any(CreateModelTypeDto.class));
    }
}