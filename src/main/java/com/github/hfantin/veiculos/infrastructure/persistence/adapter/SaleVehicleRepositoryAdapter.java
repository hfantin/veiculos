package com.github.hfantin.veiculos.infrastructure.persistence.adapter;

import com.github.hfantin.veiculos.domain.model.SaleVehicle;
import com.github.hfantin.veiculos.domain.repository.SaleVehicleRepository;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.SaleVehicleEntity;
import com.github.hfantin.veiculos.infrastructure.persistence.repository.SaleVehicleJpaRepository;
import com.github.hfantin.veiculos.infrastructure.persistence.mapper.SaleVehicleEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class SaleVehicleRepositoryAdapter implements SaleVehicleRepository {

    private final SaleVehicleJpaRepository saleVehicleJpaRepository;
    private final SaleVehicleEntityMapper saleVehicleEntityMapper;

    @Override
    public SaleVehicle save(SaleVehicle saleVehicle) {
        SaleVehicleEntity entity = saleVehicleEntityMapper.toEntity(saleVehicle);
        SaleVehicleEntity savedEntity = saleVehicleJpaRepository.save(entity);
        return saleVehicleEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<SaleVehicle> findById(Integer id) {
        return saleVehicleJpaRepository.findById(id)
                .map(saleVehicleEntityMapper::toDomain);
    }

    @Override
    public List<SaleVehicle> findAll() {
        return saleVehicleJpaRepository.findAll().stream()
                .map(saleVehicleEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<SaleVehicle> findBySaleId(Integer saleId) {
        return saleVehicleJpaRepository.findBySaleId(saleId).stream()
                .map(saleVehicleEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SaleVehicle> findByVehicleId(Integer vehicleId) {
        return saleVehicleJpaRepository.findByVehicleId(vehicleId)
                .map(saleVehicleEntityMapper::toDomain);
    }

    @Override
    public void deleteById(Integer id) {
        saleVehicleJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return saleVehicleJpaRepository.existsById(id);
    }

    @Override
    public boolean existsByVehicleId(Integer vehicleId) {
        return saleVehicleJpaRepository.existsByVehicleId(vehicleId);
    }
}
