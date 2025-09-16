package com.github.hfantin.veiculos.domain.repository;

import com.github.hfantin.veiculos.domain.entity.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandRepository {
    Brand save(Brand brand);
    Optional<Brand> findById(Long id);
    Optional<Brand> findByName(String name);
    List<Brand> findAll();
    List<Brand> findAllOrderedByName();
    boolean existsByName(String name);
    boolean existsById(Long id);
    void deleteById(Long id);
    long count();
}
