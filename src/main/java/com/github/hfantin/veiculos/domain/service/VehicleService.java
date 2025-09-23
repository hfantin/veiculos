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
    Optional<Vehicle> getVehicleByIdWithDetails(Integer id); // Novo método
    List<Vehicle> getAllVehicles();
    List<Vehicle> getAllVehiclesWithDetails(); // Novo método
    List<Vehicle> getVehiclesByStatus(VehicleStatus status);
    List<Vehicle> getVehiclesByStatusWithDetails(VehicleStatus status); // Novo método
    List<Vehicle> getVehiclesByModelId(Integer modelId);
    List<Vehicle> getVehiclesByModelIdWithDetails(Integer modelId); // Novo método
    List<Vehicle> getVehiclesByBrandId(Integer brandId); // Novo método
    List<Vehicle> getVehiclesByBrandIdWithDetails(Integer brandId); // Novo método
    List<Vehicle> getVehiclesByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    List<Vehicle> getVehiclesByPriceRangeWithDetails(BigDecimal minPrice, BigDecimal maxPrice); // Novo método
    List<Vehicle> getVehiclesByYearRange(Integer startYear, Integer endYear);
    List<Vehicle> getVehiclesByYearRangeWithDetails(Integer startYear, Integer endYear); // Novo método
    Vehicle purchaseVehicle(Integer id, Integer customerId);
    Vehicle reserveVehicle(Integer id);
    Vehicle makeVehicleAvailable(Integer id);
    void deleteVehicle(Integer id);
    long countVehicles();
}
