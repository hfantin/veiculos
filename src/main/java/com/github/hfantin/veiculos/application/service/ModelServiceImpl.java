package com.github.hfantin.veiculos.application.service;

import com.github.hfantin.veiculos.domain.model.Model;
import com.github.hfantin.veiculos.domain.repository.ModelRepository;
import com.github.hfantin.veiculos.domain.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public Model createModel(Model model) {
        model.validate();

        if (modelRepository.existsByBrandIdAndName(model.getBrandId(), model.getName())) {
            throw new IllegalArgumentException("Model with name '" + model.getName() + "' already exists for this brand");
        }

        return modelRepository.save(model);
    }

    @Override
    public Model updateModel(Integer id, Model model) {
        model.validate();

        return modelRepository.findById(id)
                .map(existingModel -> {
                    if (!existingModel.getName().equals(model.getName()) &&
                            modelRepository.existsByBrandIdAndName(model.getBrandId(), model.getName())) {
                        throw new IllegalArgumentException("Model with name '" + model.getName() + "' already exists for this brand");
                    }

                    existingModel.setBrandId(model.getBrandId());
                    existingModel.setName(model.getName());
                    return modelRepository.save(existingModel);
                })
                .orElseThrow(() -> new IllegalArgumentException("Model not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Model> getModelById(Integer id) {
        return modelRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Model> getModelByBrandIdAndName(Integer brandId, String name) {
        return modelRepository.findByBrandIdAndName(brandId, name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Model> getModelsByBrandId(Integer brandId) {
        return modelRepository.findByBrandId(brandId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Model> getAllModelsOrderedByName() {
        return modelRepository.findAllOrderedByName();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean modelExists(Integer brandId, String name) {
        return modelRepository.existsByBrandIdAndName(brandId, name);
    }

    @Override
    public void deleteModel(Integer id) {
        if (!modelRepository.existsById(id)) {
            throw new IllegalArgumentException("Model not found with id: " + id);
        }
        modelRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long countModels() {
        return modelRepository.count();
    }
}