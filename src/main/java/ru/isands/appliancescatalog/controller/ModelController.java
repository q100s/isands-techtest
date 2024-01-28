package ru.isands.appliancescatalog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.isands.appliancescatalog.dto.CreateModelDto;
import ru.isands.appliancescatalog.dto.ModelDto;
import ru.isands.appliancescatalog.service.implementation.ModelServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/model")
@RequiredArgsConstructor
public class ModelController {
    private final ModelServiceImpl service;

    @Operation(
            summary = "Get all models sorted by model name",
            description = "Endpoint to retrieve all models sorted by model name",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Models retrieved successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ModelDto[].class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request or invalid data"
                    )
            }
    )
    @GetMapping("/sorted/by-model-name")
    public List<ModelDto> getAllSortedByName() {
        return service.getAllSortedByModelName();
    }

    @Operation(
            summary = "Get all models sorted by price",
            description = "Endpoint to retrieve all models sorted by price",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Models retrieved successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ModelDto[].class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request or invalid data"
                    )
            }
    )
    @GetMapping("/sorted/by-price")
    public List<ModelDto> getAllSortedByPrice() {
        return service.getAllSortedByPrice();
    }

    @Operation(
            summary = "Get all models filtered by model name",
            description = "Endpoint to retrieve all models filtered by model name",
            parameters = {
                    @Parameter(
                            name = "modelName",
                            description = "Model name for filtering",
                            required = true,
                            example = "UHD500",
                            schema = @Schema(type = "string")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Models retrieved successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ModelDto[].class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request or invalid data"
                    )
            }
    )
    @GetMapping("/filtered/by-model-name")
    public List<ModelDto> getAllFilteredByModelName(@RequestParam("modelName") String modelName) {
        return service.getAllByModelName(modelName);
    }


    @Operation(
            summary = "Get models filtered by device type name",
            description = "Endpoint to retrieve models filtered by device type name",
            parameters = {
                    @Parameter(
                            name = "deviceTypeName",
                            description = "Device type name for filtering",
                            required = true,
                            example = "TVset",
                            schema = @Schema(type = "string")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Models retrieved successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ModelDto[].class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request or invalid data"
                    )
            }
    )
    @GetMapping("/filtered/by-device-type-name")
    public List<ModelDto> getAllFilteredByDeviceTypeName(@RequestParam("deviceTypeName") String deviceTypeName) {
        return service.filterByDeviceTypeName(deviceTypeName);
    }

    @Operation(
            summary = "Get models filtered by device type ID",
            description = "Endpoint to retrieve models filtered by device type ID",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Identifier for the device type",
                            required = true,
                            example = "1",
                            schema = @Schema(type = "integer")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Models retrieved successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ModelDto[].class))
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
    @GetMapping("/filtered/by-device-type-{id}")
    public List<ModelDto> getAllFilteredByDeviceTypeId(@PathVariable Integer id) {
        return service.filterByDeviceTypeId(id);
    }

    @Operation(
            summary = "Get models filtered by color",
            description = "Endpoint to retrieve models filtered by color",
            parameters = {
                    @Parameter(
                            name = "color",
                            description = "Color for filtering",
                            required = true,
                            example = "black",
                            schema = @Schema(type = "string")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Models retrieved successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ModelDto[].class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request or invalid data"
                    )
            }
    )
    @GetMapping("/filtered/by-color")
    public List<ModelDto> getAllFilteredByColor(@RequestParam("color") String color) {
        return service.filterByColor(color);
    }

    @Operation(
            summary = "Get models filtered by price range",
            description = "Endpoint to retrieve models filtered by price range",
            parameters = {
                    @Parameter(
                            name = "minPrice",
                            description = "Minimum price for filtering",
                            required = true,
                            example = "10000",
                            schema = @Schema(type = "integer")
                    ),
                    @Parameter(
                            name = "maxPrice",
                            description = "Maximum price for filtering",
                            required = true,
                            example = "100000",
                            schema = @Schema(type = "integer")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Models retrieved successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ModelDto[].class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request or invalid data"
                    )
            }
    )
    @GetMapping("/filtered/by-price-range")
    public List<ModelDto> getAllFilteredByPriceRange(@RequestParam("minPrice") Integer minPrice,
                                                     @RequestParam("maxPrice") Integer maxPrice) {
        return service.filterByPriceRange(minPrice, maxPrice);
    }

    @Operation(
            summary = "Get models filtered by device type ID and models specification",
            description = "Endpoint to retrieve models filtered by device type ID and models specific criteria",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Identifier for the device type",
                            required = true,
                            example = "1",
                            schema = @Schema(type = "integer")
                    ),
                    @Parameter(
                            name = "specName",
                            description = "Name of the specification",
                            required = true,
                            example = "3D",
                            schema = @Schema(type = "string")
                    ),
                    @Parameter(
                            name = "specValue",
                            description = "Value of the specification",
                            required = true,
                            example = "No",
                            schema = @Schema(type = "string")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Models retrieved successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ModelDto[].class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request or invalid data"
                    )
            }
    )
    @GetMapping("filtered/by-device-type-{id}-and-specs")
    public List<ModelDto> getAllFilteredByDeviceTypeIdAndSpecs(@PathVariable Integer id,
                                                               @RequestParam String specName,
                                                               @RequestParam String specValue) {
        return service.filterByDeviceTypeIdAndSpecs(id, specName, specValue);
    }

    @Operation(
            summary = "Add a new model by model type ID",
            description = "Endpoint to add a new model associated with a model type",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Identifier for the model type",
                            required = true,
                            example = "1",
                            schema = @Schema(type = "integer")
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "New model details",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CreateModelDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "New model added successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ModelDto.class)
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
    @PostMapping("/by-model-type/{id}")
    public ModelDto addNewModel(@PathVariable Integer id, @RequestBody CreateModelDto newProperties) {
        return service.createNewModelByModelTypeId(id, newProperties);
    }
}