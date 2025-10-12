package com.github.hfantin.veiculos.infrastructure.persistence.repository;

import com.github.hfantin.veiculos.infrastructure.persistence.entity.SaleVehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleVehicleJpaRepository extends JpaRepository<SaleVehicleEntity, Integer> {
    List<SaleVehicleEntity> findBySaleId(Integer saleId);
    Optional<SaleVehicleEntity> findByVehicleId(Integer vehicleId);
    boolean existsById(Integer id);

    @Query("SELECT s FROM SaleVehicleEntity s JOIN FETCH s.sale t JOIN FETCH t.id WHERE t.status <> 'CANCELLED' AND s.vehicle.id = :vehicleId")
    Optional<SaleVehicleEntity> existsNotCancelledByVehicleId(Integer vehicleId);

    boolean existsByVehicleId(Integer vehicleId);
}
