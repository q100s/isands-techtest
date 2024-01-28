package ru.isands.appliancescatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.isands.appliancescatalog.entity.Model;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
    @Query("SELECT m FROM Model m WHERE m.modelName = :modelName")
    List<Model> findByModelName(@Param("modelName") String modelName);

    @Query("SELECT m FROM Model m WHERE LOWER(m.deviceType.deviceTypeName) = LOWER(:deviceTypeName)")
    List<Model> findByDeviceTypeNameIgnoreCase(@Param("deviceTypeName") String deviceTypeName);

    @Query("SELECT m FROM Model m WHERE m.deviceType.id = :deviceTypeId")
    List<Model> findByDeviceTypeId(@Param("deviceTypeId") Integer deviceTypeId);

    @Query("SELECT m FROM Model m WHERE m.priceInKopecks BETWEEN :minPrice AND :maxPrice")
    List<Model> findByPriceRange(@Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice);

    @Query("SELECT m FROM Model m WHERE LOWER(m.color) = LOWER(:color)")
    List<Model> findByColorIgnoreCase(@Param("color") String color);

    @Query(value = "SELECT * FROM model INNER JOIN model_model_specs ON model.id = model_model_specs.model_id " +
            "WHERE device_type_id = :deviceTypeId AND " +
            "(:specName is null OR model_model_specs.spec_name = :specName AND model_model_specs.spec_value = :specValue)",
            nativeQuery = true)
    List<Model> findByDeviceTypeIdAndModelSpecs(@Param("deviceTypeId") Integer deviceTypeId,
                                                @Param("specName") String specName,
                                                @Param("specValue") String specValue);
}