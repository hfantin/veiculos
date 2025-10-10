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
    Optional<Vehicle> getVehicleByIdWithDetails(Integer id);
    List<Vehicle> getAllVehicles();
    List<Vehicle> getAllVehiclesWithDetails();
    List<Vehicle> getVehiclesByStatus(VehicleStatus status, boolean withDetails);
    List<Vehicle> getVehiclesByModelId(Integer modelId);
    List<Vehicle> getVehiclesByModelIdWithDetails(Integer modelId);
    List<Vehicle> getVehiclesByBrandId(Integer brandId);
    List<Vehicle> getVehiclesByBrandIdWithDetails(Integer brandId);
    List<Vehicle> getVehiclesByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    List<Vehicle> getVehiclesByPriceRangeWithDetails(BigDecimal minPrice, BigDecimal maxPrice);
    List<Vehicle> getVehiclesByYearRange(Integer startYear, Integer endYear);
    List<Vehicle> getVehiclesByYearRangeWithDetails(Integer startYear, Integer endYear);
    void deleteVehicle(Integer id);
    Optional<Vehicle> findById(Integer id);
    Vehicle update(Vehicle vehicle);
}
