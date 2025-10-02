package com.github.hfantin.veiculos.domain.service;

import com.github.hfantin.veiculos.domain.model.Sale;
import com.github.hfantin.veiculos.domain.model.SaleVehicle;

import java.util.List;
import java.util.Optional;

public interface SaleVehicleService {
    SaleVehicle save(SaleVehicle saleVehicle);
    Optional<SaleVehicle> findById(Integer id);
    List<SaleVehicle> findAll();
    List<SaleVehicle> findBySaleId(Integer saleId);
    Optional<SaleVehicle> findByVehicleId(Integer vehicleId);
    SaleVehicle update(SaleVehicle saleVehicle);
    void deleteById(Integer id);
    boolean existsByVehicleId(Integer vehicleId);
}
