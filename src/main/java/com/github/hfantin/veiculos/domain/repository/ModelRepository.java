package com.github.hfantin.veiculos.domain.repository;

import com.github.hfantin.veiculos.domain.model.Model;

import java.util.List;
import java.util.Optional;

public interface ModelRepository {
    Model save(Model model);
    Optional<Model> findById(Integer id);
    List<Model> findByBrandId(Integer brandId);
    List<Model> findAll();
    List<Model> findAllOrderedByName();
    void deleteById(Integer id);
    boolean existsByBrandIdAndName(Integer brandId, String name);
    boolean existsById(Integer id);
    long count();
}
