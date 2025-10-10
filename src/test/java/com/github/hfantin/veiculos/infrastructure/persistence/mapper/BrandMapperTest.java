package com.github.hfantin.veiculos.infrastructure.persistence.mapper;

import com.github.hfantin.veiculos.domain.model.Brand;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.BrandEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BrandMapperTest {

    private final BrandMapper mapper = new BrandMapper();

    @Test
    void shouldMapBrandToEntity() {
        // Given
        Brand brand = new Brand(1, "Toyota", LocalDateTime.of(2024, 1, 1, 10, 0));

        // When
        BrandEntity entity = mapper.toEntity(brand);

        // Then
        assertNotNull(entity);
        assertEquals(1, entity.getId());
        assertEquals("Toyota", entity.getName());
        assertEquals(LocalDateTime.of(2024, 1, 1, 10, 0), entity.getCreatedAt());
    }

    @Test
    void shouldMapEntityToBrand() {
        // Given
        BrandEntity entity = new BrandEntity();
        entity.setId(1);
        entity.setName("Toyota");
        entity.setCreatedAt(LocalDateTime.of(2024, 1, 1, 10, 0));

        // When
        Brand brand = mapper.toDomain(entity);

        // Then
        assertNotNull(brand);
        assertEquals(1, brand.getId());
        assertEquals("Toyota", brand.getName());
        assertEquals(LocalDateTime.of(2024, 1, 1, 10, 0), brand.getCreatedAt());
    }

    @Test
    void shouldReturnNullWhenBrandIsNull() {
        // When
        BrandEntity entity = mapper.toEntity(null);

        // Then
        assertNull(entity);
    }

    @Test
    void shouldReturnNullWhenEntityIsNull() {
        // When
        Brand brand = mapper.toDomain(null);

        // Then
        assertNull(brand);
    }
}