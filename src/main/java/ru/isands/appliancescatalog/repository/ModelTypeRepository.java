package ru.isands.appliancescatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isands.appliancescatalog.entity.ModelType;

@Repository
public interface ModelTypeRepository extends JpaRepository<ModelType, Integer> {
}