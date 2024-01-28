package ru.isands.appliancescatalog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.isands.appliancescatalog.dto.CreateDeviceTypeDto;
import ru.isands.appliancescatalog.dto.DeviceTypeDto;
import ru.isands.appliancescatalog.service.DeviceTypeService;

@RestController
@RequestMapping("/deviceType")
@RequiredArgsConstructor
public class DeviceTypeController {
    private final DeviceTypeService service;

    @Operation(
            summary = "Add a new device type",
            description = "Endpoint to add a new device type",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The name for a new device type",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CreateDeviceTypeDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "New device type added successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = DeviceTypeDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request or invalid data"
                    )
            }
    )
    @PostMapping
    public DeviceTypeDto addNewDeviceType(@RequestBody CreateDeviceTypeDto newDeviceType) {
        return service.createNewDeviceType(newDeviceType);
    }
}