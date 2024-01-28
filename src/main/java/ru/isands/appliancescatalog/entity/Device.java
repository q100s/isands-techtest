package ru.isands.appliancescatalog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer serialNumber;
    private Integer priceInKopecks;
    private String producingCountry;
    private String producingCompany;
    private String color;
    private String dimensions;
    private boolean isOnlineOrdered;
    private boolean isInstallmentPaid;
    private boolean isInStock;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return isOnlineOrdered == device.isOnlineOrdered && isInstallmentPaid == device.isInstallmentPaid
                && isInStock == device.isInStock && Objects.equals(id, device.id)
                && Objects.equals(serialNumber, device.serialNumber)
                && Objects.equals(priceInKopecks, device.priceInKopecks)
                && Objects.equals(producingCountry, device.producingCountry)
                && Objects.equals(producingCompany, device.producingCompany)
                && Objects.equals(color, device.color) && Objects.equals(dimensions, device.dimensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serialNumber, priceInKopecks, producingCountry, producingCompany, color, dimensions,
                isOnlineOrdered, isInstallmentPaid, isInStock);
    }
}