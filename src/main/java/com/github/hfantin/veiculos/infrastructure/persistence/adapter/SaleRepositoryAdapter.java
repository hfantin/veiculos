package com.github.hfantin.veiculos.infrastructure.persistence.adapter;

import com.github.hfantin.veiculos.domain.model.Sale;
import com.github.hfantin.veiculos.domain.repository.SaleRepository;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.SaleEntity;

import com.github.hfantin.veiculos.infrastructure.persistence.mapper.SaleEntityMapper;
import com.github.hfantin.veiculos.infrastructure.persistence.repository.SaleJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class SaleRepositoryAdapter implements SaleRepository {

    private final SaleJpaRepository saleJpaRepository;
    private final SaleEntityMapper saleEntityMapper;

    @Override
    public Sale save(Sale sale) {
        SaleEntity entity = saleEntityMapper.toEntity(sale);
        SaleEntity savedEntity = saleJpaRepository.save(entity);
        return saleEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Sale> findById(Integer id) {
        return saleJpaRepository.findById(id)
                .map(saleEntityMapper::toDomain);
    }

    @Override
    public List<Sale> findAll() {
        return saleJpaRepository.findAll().stream()
                .map(saleEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Sale> findByCustomerId(Integer customerId) {
        return saleJpaRepository.findByCustomerId(customerId).stream()
                .map(saleEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        saleJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return saleJpaRepository.existsById(id);
    }
}