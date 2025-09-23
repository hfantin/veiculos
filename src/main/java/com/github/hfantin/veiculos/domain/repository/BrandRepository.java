package com.github.hfantin.veiculos.domain.repository;

import com.github.hfantin.veiculos.domain.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandRepository {
    Brand save(Brand brand);
    Optional<Brand> findById(Integer id);
    Optional<Brand> findByName(String name);
    List<Brand> findAll();
    List<Brand> findAllOrderedByName();
    void deleteById(Integer id);
    boolean existsByName(String name);
    boolean existsById(Integer id);
    long count();
}
