package com.github.hfantin.veiculos.domain.service;

import com.github.hfantin.veiculos.domain.model.Model;

import java.util.List;
import java.util.Optional;

public interface ModelService {
    Optional<Model> getModelById(Integer id);
    List<Model> getModelsByBrandId(Integer brandId);
    List<Model> getAllModels();
    List<Model> getAllModelsOrderedByName();
    Model createModel(Model model);
    Model updateModel(Integer id, Model model);
    void deleteModel(Integer id);
}