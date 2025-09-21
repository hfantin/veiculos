package com.github.hfantin.veiculos.domain.service;

import com.github.hfantin.veiculos.domain.model.Vehicle;
import com.github.hfantin.veiculos.domain.model.enums.VehicleStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface VehicleService {
    Vehicle createVehicle(Vehicle vehicle);
    Vehicle updateVehicle(Integer id, Vehicle vehicle);
    Optional<Vehicle> getVehicleById(Integer id);
    List<Vehicle> getAllVehicles();
    List<Vehicle> getVehiclesByStatus(VehicleStatus status);
    List<Vehicle> getVehiclesByModelId(Integer modelId);
    List<Vehicle> getVehiclesByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    List<Vehicle> getVehiclesByYearRange(Integer startYear, Integer endYear);
    Vehicle purchaseVehicle(Integer id, Integer customerId);
    Vehicle reserveVehicle(Integer id);
    Vehicle makeVehicleAvailable(Integer id);
    void deleteVehicle(Integer id);
    long countVehicles();
}