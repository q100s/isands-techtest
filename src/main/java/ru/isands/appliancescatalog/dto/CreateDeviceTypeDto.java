package ru.isands.appliancescatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDeviceTypeDto {
    private String deviceTypeName;
}