package com.github.hfantin.veiculos.domain.repository;

import com.github.hfantin.veiculos.domain.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {
    Vehicle save(Vehicle vehicle);
    Optional<Vehicle> findById(Long id);
    List<Vehicle> findByModelId(Long modelId);
    List<Vehicle> findByStatus(String status);
    List<Vehicle> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}