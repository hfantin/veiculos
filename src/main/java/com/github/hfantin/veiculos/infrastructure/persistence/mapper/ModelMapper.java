package com.github.hfantin.veiculos.infrastructure.persistence.mapper;

import com.github.hfantin.veiculos.domain.model.Model;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.ModelEntity;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    public ModelEntity toEntity(Model model) {
        if (model == null) {
            return null;
        }

        ModelEntity entity = new ModelEntity();
        entity.setId(model.getId());
        entity.setBrandId(model.getBrandId());
        entity.setName(model.getName());
        entity.setCreatedAt(model.getCreatedAt());
        return entity;
    }

    public Model toDomain(ModelEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Model(
                entity.getId(),
                entity.getBrandId(),
                entity.getName(),
                entity.getCreatedAt()
        );
    }
}
