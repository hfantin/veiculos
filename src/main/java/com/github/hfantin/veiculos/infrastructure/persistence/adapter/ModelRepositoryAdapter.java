package com.github.hfantin.veiculos.infrastructure.persistence.adapter;

import com.github.hfantin.veiculos.domain.model.Model;
import com.github.hfantin.veiculos.domain.repository.ModelRepository;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.ModelEntity;
import com.github.hfantin.veiculos.infrastructure.persistence.mapper.ModelMapper;
import com.github.hfantin.veiculos.infrastructure.persistence.repository.ModelJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ModelRepositoryAdapter implements ModelRepository {

    private final ModelJpaRepository modelJpaRepository;
    private final ModelMapper modelMapper;

    @Override
    public Model save(Model model) {
        ModelEntity entity = modelMapper.toEntity(model);
        ModelEntity savedEntity = modelJpaRepository.save(entity);
        return modelMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Model> findById(Integer id) {
        return modelJpaRepository.findById(id)
                .map(modelMapper::toDomain);
    }

    @Override
    public Optional<Model> findByBrandIdAndName(Integer brandId, String name) {
        return modelJpaRepository.findByBrandIdAndName(brandId, name)
                .map(modelMapper::toDomain);
    }

    @Override
    public List<Model> findByBrandId(Integer brandId) {
        return modelJpaRepository.findByBrandId(brandId).stream()
                .map(modelMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Model> findAll() {
        return modelJpaRepository.findAll().stream()
                .map(modelMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Model> findAllOrderedByName() {
        return modelJpaRepository.findAllOrderedByName().stream()
                .map(modelMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByBrandIdAndName(Integer brandId, String name) {
        return modelJpaRepository.existsByBrandIdAndName(brandId, name);
    }

    @Override
    public boolean existsById(Integer id) {
        return modelJpaRepository.existsById(id);
    }

    @Override
    public void deleteById(Integer id) {
        modelJpaRepository.deleteById(id);
    }

    @Override
    public long count() {
        return modelJpaRepository.count();
    }
}
