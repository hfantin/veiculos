package com.github.hfantin.veiculos.domain.repository;


import com.github.hfantin.veiculos.domain.model.SaleVehicle;
import com.github.hfantin.veiculos.domain.model.SaleVehicleBrandModelVehicleDetails;

import java.util.List;
import java.util.Optional;

public interface SaleVehicleRepository {
    SaleVehicle save(SaleVehicle saleVehicle);
    Optional<SaleVehicle> findById(Integer id);
    List<SaleVehicle> findAll();
    List<SaleVehicleBrandModelVehicleDetails> findAllByCustomerId(String customerId);
    List<SaleVehicle> findBySaleId(Integer saleId);
    Optional<SaleVehicle> findByVehicleId(Integer vehicleId);
    void deleteById(Integer id);
    boolean existsById(Integer id);
    boolean existsByVehicleId(Integer vehicleId);
}
