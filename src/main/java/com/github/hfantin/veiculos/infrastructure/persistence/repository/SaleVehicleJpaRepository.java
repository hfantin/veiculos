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

    @Query("SELECT s FROM SaleVehicleEntity s JOIN FETCH s.sale t WHERE s.vehicle.id = :vehicleId and t.status <> 'CANCELLED'")
    Optional<SaleVehicleEntity> existsNotCancelledByVehicleId(Integer vehicleId);

    @Query("SELECT s FROM SaleVehicleEntity s JOIN FETCH s.sale t JOIN FETCH t.customer c WHERE c.authId = :customerId")
    List<SaleVehicleEntity> findAllByCustomeId(String customerId);

    boolean existsByVehicleId(Integer vehicleId);
}
