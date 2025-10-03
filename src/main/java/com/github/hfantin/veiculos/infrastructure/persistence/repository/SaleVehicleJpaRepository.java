package com.github.hfantin.veiculos.infrastructure.persistence.repository;

import com.github.hfantin.veiculos.infrastructure.persistence.entity.SaleVehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleVehicleJpaRepository extends JpaRepository<SaleVehicleEntity, Integer> {
    List<SaleVehicleEntity> findBySaleId(Integer saleId);
    Optional<SaleVehicleEntity> findByVehicleId(Integer vehicleId);
    boolean existsById(Integer id);
    boolean existsByVehicleId(Integer vehicleId);
}
