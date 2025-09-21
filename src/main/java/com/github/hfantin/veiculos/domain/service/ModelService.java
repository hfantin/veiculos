package com.github.hfantin.veiculos.domain.service;

import com.github.hfantin.veiculos.domain.model.Model;

import java.util.List;
import java.util.Optional;

public interface ModelService {
    Model createModel(Model model);
    Model updateModel(Integer id, Model model);
    Optional<Model> getModelById(Integer id);
    List<Model> getModelsByBrandId(Integer brandId);
    List<Model> getAllModels();
    boolean modelExists(Integer brandId, String name);
    void deleteModel(Integer id);
    long countModels();
}