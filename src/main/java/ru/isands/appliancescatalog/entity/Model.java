package ru.isands.appliancescatalog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Model extends Device {
    private String modelName;
    @ElementCollection
    @MapKeyColumn(name = "spec_name")
    @Column(name = "spec_value")
    private Map<String, String> deviceSpecs;
    @ElementCollection
    @MapKeyColumn(name = "spec_name")
    @Column(name = "spec_value")
    private Map<String, String> typeSpecs;
    @ElementCollection
    @MapKeyColumn(name = "spec_name")
    @Column(name = "spec_value")
    private Map<String, String> modelSpecs;
    @ManyToOne
    @JoinColumn(name = "deviceType_id")
    private DeviceType deviceType;
    @ManyToOne
    @JoinColumn(name = "modelType_id")
    private ModelType modelType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return Objects.equals(modelName, model.modelName) && Objects.equals(deviceSpecs, model.deviceSpecs)
                && Objects.equals(typeSpecs, model.typeSpecs) && Objects.equals(modelSpecs, model.modelSpecs)
                && Objects.equals(deviceType, model.deviceType) && Objects.equals(modelType, model.modelType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelName, deviceSpecs, typeSpecs, modelSpecs, deviceType, modelType);
    }
}