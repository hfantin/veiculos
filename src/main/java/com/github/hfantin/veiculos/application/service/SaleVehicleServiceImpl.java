package com.github.hfantin.veiculos.application.service;

import com.github.hfantin.veiculos.domain.model.SaleVehicle;
import com.github.hfantin.veiculos.domain.repository.SaleVehicleRepository;
import com.github.hfantin.veiculos.domain.service.SaleVehicleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class SaleVehicleServiceImpl implements SaleVehicleService {

    private final SaleVehicleRepository saleVehicleRepository;

    @Override
    public SaleVehicle save(SaleVehicle saleVehicle) {
        log.info("Saving sale vehicle for sale ID: {} and vehicle ID: {}",
                saleVehicle.getSaleId(), saleVehicle.getVehicleId());

        if (saleVehicleRepository.existsByVehicleId(saleVehicle.getVehicleId())) {
            throw new IllegalArgumentException("Vehicle with ID " + saleVehicle.getVehicleId() + " is already sold");
        }

        saleVehicle.setCreatedAt(LocalDateTime.now());
        return saleVehicleRepository.save(saleVehicle);
    }

    @Override
    public Optional<SaleVehicle> findById(Integer id) {
        return saleVehicleRepository.findById(id);
    }

    @Override
    public List<SaleVehicle> findAll() {
        return saleVehicleRepository.findAll();
    }

    @Override
    public List<SaleVehicle> findBySaleId(Integer saleId) {
        return saleVehicleRepository.findBySaleId(saleId);
    }

    @Override
    public Optional<SaleVehicle> findByVehicleId(Integer vehicleId) {
        return saleVehicleRepository.findByVehicleId(vehicleId);
    }

    @Override
    public SaleVehicle update(SaleVehicle saleVehicle) {
        log.info("Updating sale vehicle with ID: {}", saleVehicle.getId());
        SaleVehicle existingSaleVehicle = saleVehicleRepository.findById(saleVehicle.getId())
                .orElseThrow(() -> new IllegalArgumentException("SaleVehicle not found with ID: " + saleVehicle.getId()));

        existingSaleVehicle.setSaleId(saleVehicle.getSaleId());
        existingSaleVehicle.setVehicleId(saleVehicle.getVehicleId());
        existingSaleVehicle.setSalePrice(saleVehicle.getSalePrice());

        return saleVehicleRepository.save(existingSaleVehicle);
    }

    @Override
    public void deleteById(Integer id) {
        if (!saleVehicleRepository.existsById(id)) {
            throw new IllegalArgumentException("SaleVehicle not found with ID: " + id);
        }
        saleVehicleRepository.deleteById(id);
        log.info("SaleVehicle deleted with ID: {}", id);
    }

    @Override
    public boolean existsByVehicleId(Integer vehicleId) {
        return saleVehicleRepository.existsByVehicleId(vehicleId);
    }
}
