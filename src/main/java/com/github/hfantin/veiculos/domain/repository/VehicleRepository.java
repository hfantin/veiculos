package com.github.hfantin.veiculos.domain.repository;

import com.github.hfantin.veiculos.domain.model.Vehicle;
import com.github.hfantin.veiculos.domain.model.enums.VehicleStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface VehicleRepository {
    Vehicle save(Vehicle vehicle);
    Optional<Vehicle> findById(Integer id);
    List<Vehicle> findAll();
    List<Vehicle> findByStatus(VehicleStatus status);
    List<Vehicle> findByModelId(Integer modelId);
    List<Vehicle> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    List<Vehicle> findByYearRange(Integer startYear, Integer endYear);
    boolean existsById(Integer id);
    void deleteById(Integer id);
    long count();
}