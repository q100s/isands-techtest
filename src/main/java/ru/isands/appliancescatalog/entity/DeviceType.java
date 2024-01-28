package ru.isands.appliancescatalog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeviceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String deviceTypeName;
    @OneToMany(mappedBy = "deviceType")
    private List<ModelType> modelTypes;
    @OneToMany(mappedBy = "deviceType")
    private List<Model> models;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceType that = (DeviceType) o;
        return Objects.equals(id, that.id) && Objects.equals(deviceTypeName, that.deviceTypeName)
                && Objects.equals(modelTypes, that.modelTypes) && Objects.equals(models, that.models);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deviceTypeName, modelTypes, models);
    }
}