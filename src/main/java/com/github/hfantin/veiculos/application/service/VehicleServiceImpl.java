package com.github.hfantin.veiculos.application.service;

import com.github.hfantin.veiculos.domain.model.Vehicle;
import com.github.hfantin.veiculos.domain.model.enums.VehicleStatus;
import com.github.hfantin.veiculos.domain.repository.VehicleRepository;
import com.github.hfantin.veiculos.domain.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        vehicle.validate();
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Integer id, Vehicle vehicle) {
        vehicle.validate();

        return vehicleRepository.findById(id)
                .map(existingVehicle -> {
                    existingVehicle.updateVehicle(
                            vehicle.getYear(),
                            vehicle.getColor(),
                            vehicle.getPrice()
                    );
                    return vehicleRepository.save(existingVehicle);
                })
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vehicle> getVehicleById(Integer id) {
        return vehicleRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vehicle> getVehicleByIdWithDetails(Integer id) {
        return vehicleRepository.findByIdWithDetails(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> getAllVehiclesWithDetails() {
        return vehicleRepository.findAllWithDetails();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> getVehiclesByStatus(VehicleStatus status) {
        return vehicleRepository.findByStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> getVehiclesByStatusWithDetails(VehicleStatus status) {
        return vehicleRepository.findByStatusWithDetails(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> getVehiclesByModelId(Integer modelId) {
        return vehicleRepository.findByModelId(modelId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> getVehiclesByModelIdWithDetails(Integer modelId) {
        return vehicleRepository.findByModelIdWithDetails(modelId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> getVehiclesByBrandId(Integer brandId) {
        return vehicleRepository.findByBrandId(brandId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> getVehiclesByBrandIdWithDetails(Integer brandId) {
        return vehicleRepository.findByBrandIdWithDetails(brandId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> getVehiclesByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return vehicleRepository.findByPriceRange(minPrice, maxPrice);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> getVehiclesByPriceRangeWithDetails(BigDecimal minPrice, BigDecimal maxPrice) {
        return vehicleRepository.findByPriceRangeWithDetails(minPrice, maxPrice);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> getVehiclesByYearRange(Integer startYear, Integer endYear) {
        return vehicleRepository.findByYearRange(startYear, endYear);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> getVehiclesByYearRangeWithDetails(Integer startYear, Integer endYear) {
        return vehicleRepository.findByYearRangeWithDetails(startYear, endYear);
    }

    @Override
    public Vehicle purchaseVehicle(Integer id, Integer customerId) {
        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    vehicle.markAsSold();
                    return vehicleRepository.save(vehicle);
                })
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with id: " + id));
    }

    @Override
    public Vehicle reserveVehicle(Integer id) {
        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    vehicle.reserve();
                    return vehicleRepository.save(vehicle);
                })
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with id: " + id));
    }

    @Override
    public Vehicle makeVehicleAvailable(Integer id) {
        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    vehicle.makeAvailable();
                    return vehicleRepository.save(vehicle);
                })
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with id: " + id));
    }

    @Override
    public void deleteVehicle(Integer id) {
        if (!vehicleRepository.existsById(id)) {
            throw new IllegalArgumentException("Vehicle not found with id: " + id);
        }
        vehicleRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long countVehicles() {
        return vehicleRepository.count();
    }
}
