package com.github.hfantin.veiculos.domain.repository;


import com.github.hfantin.veiculos.domain.model.SaleVehicle;

import java.util.List;
import java.util.Optional;

public interface SaleVehicleRepository {
    SaleVehicle save(SaleVehicle saleVehicle);
    Optional<SaleVehicle> findById(Long id);
    Optional<SaleVehicle> findByVehicleId(Long vehicleId);
    List<SaleVehicle> findBySaleId(Long saleId);
    List<SaleVehicle> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    boolean existsByVehicleId(Long vehicleId);
}
