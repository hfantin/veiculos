package com.github.hfantin.veiculos.infrastructure.persistence.mapper;

import com.github.hfantin.veiculos.domain.entity.Brand;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.BrandEntity;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    public BrandEntity toEntity(Brand brand) {
        if (brand == null) {
            return null;
        }

        BrandEntity entity = new BrandEntity();
        entity.setId(brand.getId());
        entity.setName(brand.getName());
        entity.setCreatedAt(brand.getCreatedAt());
        return entity;
    }

    public Brand toDomain(BrandEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Brand(
                entity.getId(),
                entity.getName(),
                entity.getCreatedAt()
        );
    }
}