package com.github.hfantin.veiculos.domain.repository;

import com.github.hfantin.veiculos.domain.model.Vehicle;
import com.github.hfantin.veiculos.domain.model.enums.VehicleStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface VehicleRepository {
    Vehicle save(Vehicle vehicle);
    Optional<Vehicle> findById(Integer id);
    Optional<Vehicle> findByIdWithDetails(Integer id); // Novo método
    List<Vehicle> findAll();
    List<Vehicle> findAllWithDetails(); // Novo método
    List<Vehicle> findByStatus(VehicleStatus status);
    List<Vehicle> findByStatusWithDetails(VehicleStatus status); // Novo método
    List<Vehicle> findByModelId(Integer modelId);
    List<Vehicle> findByModelIdWithDetails(Integer modelId); // Novo método
    List<Vehicle> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    List<Vehicle> findByPriceRangeWithDetails(BigDecimal minPrice, BigDecimal maxPrice); // Novo método
    List<Vehicle> findByYearRange(Integer startYear, Integer endYear);
    List<Vehicle> findByYearRangeWithDetails(Integer startYear, Integer endYear); // Novo método
    List<Vehicle> findByBrandId(Integer brandId); // Novo método
    List<Vehicle> findByBrandIdWithDetails(Integer brandId); // Novo método
    boolean existsById(Integer id);
    void deleteById(Integer id);
    long count();
}