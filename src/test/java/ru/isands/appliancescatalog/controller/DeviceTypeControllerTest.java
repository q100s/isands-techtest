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
import ru.isands.appliancescatalog.dto.CreateDeviceTypeDto;
import ru.isands.appliancescatalog.dto.DeviceTypeDto;
import ru.isands.appliancescatalog.service.DeviceTypeService;

import static org.mockito.Mockito.*;

@WebMvcTest(DeviceTypeController.class)
public class DeviceTypeControllerTest {
    static DeviceTypeDto deviceTypeDto = new DeviceTypeDto();
    static CreateDeviceTypeDto createDeviceTypeDto = new CreateDeviceTypeDto();
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeviceTypeService service;

    @BeforeAll
    static void init() {
        DeviceTypeDto deviceTypeDto = new DeviceTypeDto();
        deviceTypeDto.setId(1);
        deviceTypeDto.setDeviceTypeName("TestDeviceType");
        createDeviceTypeDto.setDeviceTypeName("TestDeviceType");
    }

    @Test
    void testAddNewDeviceType() throws Exception {
        when(service.createNewDeviceType(any(CreateDeviceTypeDto.class))).thenReturn(deviceTypeDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/deviceType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDeviceTypeDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(deviceTypeDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deviceTypeName")
                        .value(deviceTypeDto.getDeviceTypeName()));

        verify(service, times(1)).createNewDeviceType(any(CreateDeviceTypeDto.class));
    }
}
