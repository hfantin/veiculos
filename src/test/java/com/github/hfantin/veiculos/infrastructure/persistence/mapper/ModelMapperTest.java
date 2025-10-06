package com.github.hfantin.veiculos.infrastructure.persistence.mapper;

import com.github.hfantin.veiculos.domain.model.Model;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.BrandEntity;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.ModelEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ModelMapperTest {

    private final ModelMapper mapper = new ModelMapper(new BrandMapper());

    @Test
    void shouldMapModelToEntity() {
        // Given
        Model model = new Model(1, 1, "Toyota", "Corolla", LocalDateTime.of(2024, 1, 1, 10, 0));

        // When
        ModelEntity entity = mapper.toEntity(model);

        // Then
        assertNotNull(entity);
        assertEquals(1, entity.getId());
        assertEquals("Corolla", entity.getName());
        assertEquals(LocalDateTime.of(2024, 1, 1, 10, 0), entity.getCreatedAt());
    }

    @Test
    void shouldMapEntityToModel() {
        // Given
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(1);
        brandEntity.setName("Toyota");

        ModelEntity entity = new ModelEntity();
        entity.setId(1);
        entity.setName("Corolla");
        entity.setBrand(brandEntity);
        entity.setCreatedAt(LocalDateTime.of(2024, 1, 1, 10, 0));

        // When
        Model model = mapper.toDomain(entity);

        // Then
        assertNotNull(model);
        assertEquals(1, model.getId());
        assertEquals("Corolla", model.getName());
        assertEquals(1, model.getBrandId()); // Deve extrair brandId do BrandEntity
        assertEquals(LocalDateTime.of(2024, 1, 1, 10, 0), model.getCreatedAt());
    }

    @Test
    void shouldMapEntityToModelWhenBrandIsNull() {
        // Given
        ModelEntity entity = new ModelEntity();
        entity.setId(1);
        entity.setName("Corolla");
        entity.setBrand(null);
        entity.setCreatedAt(LocalDateTime.now());

        // When
        Model model = mapper.toDomain(entity);

        // Then
        assertNotNull(model);
        assertEquals(1, model.getId());
        assertEquals("Corolla", model.getName());
        assertNull(model.getBrandId()); // brandId deve ser null quando brand é null
    }

    @Test
    void shouldReturnNullWhenModelIsNull() {
        // When
        ModelEntity entity = mapper.toEntity(null);

        // Then
        assertNull(entity);
    }

    @Test
    void shouldReturnNullWhenEntityIsNull() {
        // When
        Model model = mapper.toDomain(null);

        // Then
        assertNull(model);
    }
}