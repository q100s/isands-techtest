package ru.isands.appliancescatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateModelDto {
    private Integer serialNumber;
    private Integer priceInKopecks;
    private String producingCountry;
    private String producingCompany;
    private String color;
    private String dimensions;
    private boolean isOnlineOrdered;
    private boolean isInstallmentPaid;
    private boolean isInStock;
    private String modelName;
    private Map<String, String> deviceSpecs;
    private Map<String, String> typeSpecs;
    private Map<String, String> modelSpecs;
}