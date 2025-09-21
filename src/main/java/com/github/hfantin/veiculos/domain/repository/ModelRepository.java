package com.github.hfantin.veiculos.domain.repository;

import com.github.hfantin.veiculos.domain.model.Model;

import java.util.List;
import java.util.Optional;

public interface ModelRepository {
    Model save(Model model);
    Optional<Model> findById(Integer id);
    Optional<Model> findByIdWithBrand(Integer id); // Novo método
    Optional<Model> findByBrandIdAndName(Integer brandId, String name);
    List<Model> findByBrandId(Integer brandId);
    List<Model> findByBrandIdWithBrand(Integer brandId); // Novo método
    List<Model> findAll();
    List<Model> findAllWithBrand(); // Novo método
    List<Model> findAllOrderedByName();
    List<Model> findAllOrderedByNameWithBrand(); // Novo método
    boolean existsByBrandIdAndName(Integer brandId, String name);
    boolean existsById(Integer id);
    void deleteById(Integer id);
    long count();
}
