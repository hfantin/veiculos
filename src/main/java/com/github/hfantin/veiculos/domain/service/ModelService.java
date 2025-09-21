package com.github.hfantin.veiculos.domain.service;

import com.github.hfantin.veiculos.domain.model.Model;

import java.util.List;
import java.util.Optional;

public interface ModelService {
    Model createModel(Model model);
    Model updateModel(Integer id, Model model);
    Optional<Model> getModelById(Integer id);
    Optional<Model> getModelByIdWithBrand(Integer id); // Novo método
    Optional<Model> getModelByBrandIdAndName(Integer brandId, String name);
    List<Model> getModelsByBrandId(Integer brandId);
    List<Model> getModelsByBrandIdWithBrand(Integer brandId); // Novo método
    List<Model> getAllModels();
    List<Model> getAllModelsWithBrand(); // Novo método
    List<Model> getAllModelsOrderedByName();
    List<Model> getAllModelsOrderedByNameWithBrand(); // Novo método
    boolean modelExists(Integer brandId, String name);
    void deleteModel(Integer id);
    long countModels();
}