package com.github.hfantin.veiculos.infrastructure.persistence.adapter;

import com.github.hfantin.veiculos.domain.model.Brand;
import com.github.hfantin.veiculos.domain.repository.BrandRepository;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.BrandEntity;
import com.github.hfantin.veiculos.infrastructure.persistence.mapper.BrandMapper;
import com.github.hfantin.veiculos.infrastructure.persistence.repository.BrandJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BrandRepositoryAdapter implements BrandRepository {

    private final BrandJpaRepository brandJpaRepository;
    private final BrandMapper brandMapper;

    public BrandRepositoryAdapter(BrandJpaRepository brandJpaRepository, BrandMapper brandMapper) {
        this.brandJpaRepository = brandJpaRepository;
        this.brandMapper = brandMapper;
    }

    @Override
    public Brand save(Brand brand) {
        BrandEntity entity = brandMapper.toEntity(brand);
        BrandEntity savedEntity = brandJpaRepository.save(entity);
        return brandMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Brand> findById(Integer id) {
        return brandJpaRepository.findById(id)
                .map(brandMapper::toDomain);
    }

    @Override
    public Optional<Brand> findByName(String name) {
        return brandJpaRepository.findByName(name)
                .map(brandMapper::toDomain);
    }

    @Override
    public List<Brand> findAll() {
        return brandJpaRepository.findAll().stream()
                .map(brandMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Brand> findAllOrderedByName() {
        return brandJpaRepository.findAllOrderedByName().stream()
                .map(brandMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByName(String name) {
        return brandJpaRepository.existsByName(name);
    }

    @Override
    public boolean existsById(Integer id) {
        return brandJpaRepository.existsById(id);
    }

    @Override
    public void deleteById(Integer id) {
        brandJpaRepository.deleteById(id);
    }

    @Override
    public long count() {
        return brandJpaRepository.count();
    }
}