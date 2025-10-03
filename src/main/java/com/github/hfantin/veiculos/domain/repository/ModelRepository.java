package com.github.hfantin.veiculos.domain.repository;

import com.github.hfantin.veiculos.domain.model.Model;

import java.util.List;
import java.util.Optional;

public interface ModelRepository {
    List<Model> findAll();
    List<Model> findAllOrderedByName();
    Optional<Model> findById(Integer id);
    List<Model> findByBrandId(Integer brandId);
    Model save(Model model);
    void deleteById(Integer id);
    boolean existsByBrandIdAndName(Integer brandId, String name);
    boolean existsById(Integer id);
}
