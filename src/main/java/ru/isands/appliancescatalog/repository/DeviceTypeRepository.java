package ru.isands.appliancescatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isands.appliancescatalog.entity.DeviceType;

@Repository
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Integer> {
}