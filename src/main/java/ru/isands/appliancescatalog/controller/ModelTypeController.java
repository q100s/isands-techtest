package ru.isands.appliancescatalog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.isands.appliancescatalog.dto.CreateModelTypeDto;
import ru.isands.appliancescatalog.dto.ModelTypeDto;
import ru.isands.appliancescatalog.service.implementation.ModelTypeServiceImpl;

@RestController
@RequestMapping("/model-type")
@RequiredArgsConstructor
public class ModelTypeController {
    private final ModelTypeServiceImpl service;

    @Operation(
            summary = "Add a new model type by device type ID",
            description = "Endpoint to add a new model type associated with a device type",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Identifier for the device type",
                            required = true,
                            example = "1",
                            schema = @Schema(type = "integer")
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Name for a new model type",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CreateModelTypeDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "New model type added successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ModelTypeDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request or invalid data"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The device type has not found by identifier"
                    )
            }
    )
    @PostMapping("/by-device-type/{id}")
    public ModelTypeDto addNewModelType(@PathVariable Integer id,
                                        @RequestBody CreateModelTypeDto newProperties) {
        return service.createNewModelTypeByDeviceTypeId(id, newProperties);
    }
}