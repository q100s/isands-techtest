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
public class ModelType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String modelTypeName;
    @ManyToOne
    @JoinColumn(name = "deviceType_id")
    private DeviceType deviceType;
    @OneToMany(mappedBy = "modelType")
    private List<Model> models;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelType modelType = (ModelType) o;
        return Objects.equals(id, modelType.id) && Objects.equals(modelTypeName, modelType.modelTypeName)
                && Objects.equals(deviceType, modelType.deviceType) && Objects.equals(models, modelType.models);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelTypeName, deviceType, models);
    }
}