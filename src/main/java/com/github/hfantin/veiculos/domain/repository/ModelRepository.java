package com.github.hfantin.veiculos.domain.repository;

import com.github.hfantin.veiculos.domain.model.Model;

import java.util.List;
import java.util.Optional;

public interface ModelRepository {
    Model save(Model brand);
    Optional<Model> findById(Long id);
    Optional<Model> findByName(String name);
    List<Model> findAll();
    List<Model> findAllOrderedByName();
    boolean existsByName(String name);
    boolean existsById(Long id);
    boolean existsByBrandIdAndName(Long brandId, String name);
    void deleteById(Long id);
    long count();
}
