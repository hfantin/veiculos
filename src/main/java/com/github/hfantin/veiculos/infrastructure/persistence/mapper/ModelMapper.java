package com.github.hfantin.veiculos.infrastructure.persistence.mapper;

import com.github.hfantin.veiculos.domain.model.Model;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.BrandEntity;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.ModelEntity;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    private final BrandMapper brandMapper;

    public ModelMapper(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    public ModelEntity toEntity(Model model) {
        if (model == null) {
            return null;
        }

        ModelEntity entity = new ModelEntity();
        entity.setId(model.getId());

        // Criar uma BrandEntity mínima apenas com o ID para evitar consultas desnecessárias
        if (model.getBrandId() != null) {
            BrandEntity brandEntity = new BrandEntity();
            brandEntity.setId(model.getBrandId());
            entity.setBrand(brandEntity);
        }

        entity.setName(model.getName());
        entity.setCreatedAt(model.getCreatedAt());
        return entity;
    }

    public Model toDomain(ModelEntity entity) {
        if (entity == null) {
            return null;
        }

        Model model = new Model();
        model.setId(entity.getId());
        model.setBrandId(entity.getBrand() != null ? entity.getBrand().getId() : null);
        model.setName(entity.getName());
        model.setCreatedAt(entity.getCreatedAt());

        return model;
    }

    // Método para carregamento eager quando necessário
    public Model toDomainWithBrand(ModelEntity entity) {
        if (entity == null) {
            return null;
        }

        Model model = toDomain(entity);
        if (entity.getBrand() != null) {
            model.setBrandName(entity.getBrand().getName());
        }
        return model;
    }
}
