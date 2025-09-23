package com.github.hfantin.veiculos.infrastructure.persistence.adapter;

import com.github.hfantin.veiculos.domain.model.Vehicle;
import com.github.hfantin.veiculos.domain.model.enums.VehicleStatus;
import com.github.hfantin.veiculos.domain.repository.VehicleRepository;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.VehicleEntity;
import com.github.hfantin.veiculos.infrastructure.persistence.mapper.VehicleMapper;
import com.github.hfantin.veiculos.infrastructure.persistence.repository.VehicleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VehicleRepositoryAdapter implements VehicleRepository {

    private final VehicleJpaRepository vehicleJpaRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    public Vehicle save(Vehicle vehicle) {
        VehicleEntity entity = vehicleMapper.toEntity(vehicle);
        VehicleEntity savedEntity = vehicleJpaRepository.save(entity);
        return vehicleMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Vehicle> findById(Integer id) {
        return vehicleJpaRepository.findById(id)
                .map(vehicleMapper::toDomain);
    }

    @Override
    public Optional<Vehicle> findByIdWithDetails(Integer id) {
        return vehicleJpaRepository.findByIdWithDetails(id)
                .map(vehicleMapper::toDomainWithDetails);
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleJpaRepository.findAll().stream()
                .map(vehicleMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> findAllWithDetails() {
        return vehicleJpaRepository.findAllWithDetails().stream()
                .map(vehicleMapper::toDomainWithDetails)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> findByStatus(VehicleStatus status) {
        return vehicleJpaRepository.findByStatus(status).stream()
                .map(vehicleMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> findByStatusWithDetails(VehicleStatus status) {
        return vehicleJpaRepository.findByStatusWithDetails(status).stream()
                .map(vehicleMapper::toDomainWithDetails)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> findByModelId(Integer modelId) {
        return vehicleJpaRepository.findByModelId(modelId).stream()
                .map(vehicleMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> findByModelIdWithDetails(Integer modelId) {
        return vehicleJpaRepository.findByModelIdWithDetails(modelId).stream()
                .map(vehicleMapper::toDomainWithDetails)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return vehicleJpaRepository.findByPriceRange(minPrice, maxPrice).stream()
                .map(vehicleMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> findByPriceRangeWithDetails(BigDecimal minPrice, BigDecimal maxPrice) {
        return vehicleJpaRepository.findByPriceRangeWithDetails(minPrice, maxPrice).stream()
                .map(vehicleMapper::toDomainWithDetails)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> findByYearRange(Integer startYear, Integer endYear) {
        return vehicleJpaRepository.findByYearRange(startYear, endYear).stream()
                .map(vehicleMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> findByYearRangeWithDetails(Integer startYear, Integer endYear) {
        return vehicleJpaRepository.findByYearRangeWithDetails(startYear, endYear).stream()
                .map(vehicleMapper::toDomainWithDetails)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> findByBrandId(Integer brandId) {
        return vehicleJpaRepository.findByBrandId(brandId).stream()
                .map(vehicleMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> findByBrandIdWithDetails(Integer brandId) {
        return vehicleJpaRepository.findByBrandIdWithDetails(brandId).stream()
                .map(vehicleMapper::toDomainWithDetails)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Integer id) {
        return vehicleJpaRepository.existsById(id);
    }

    @Override
    public void deleteById(Integer id) {
        vehicleJpaRepository.deleteById(id);
    }

    @Override
    public long count() {
        return vehicleJpaRepository.count();
    }
}
