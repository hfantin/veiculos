package com.github.hfantin.veiculos.domain.repository;

import com.github.hfantin.veiculos.domain.model.Model;

import java.util.List;
import java.util.Optional;

public interface ModelRepository {
    Model save(Model model);
    Optional<Model> findById(Integer id);
    List<Model> findByBrandId(Integer brandId);
    List<Model> findAllWithBrand();
    List<Model> findAllOrderedByName();
    boolean existsByBrandIdAndName(Integer brandId, String name);
    boolean existsById(Integer id);
    void deleteById(Integer id);
    long count();
}
