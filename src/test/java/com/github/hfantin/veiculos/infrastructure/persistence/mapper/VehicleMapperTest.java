package com.github.hfantin.veiculos.infrastructure.persistence.mapper;

import com.github.hfantin.veiculos.domain.model.Vehicle;
import com.github.hfantin.veiculos.domain.model.enums.VehicleStatus;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.ModelEntity;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.VehicleEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class VehicleMapperTest {

    private final VehicleMapper mapper = new VehicleMapper();

    @Test
    void shouldMapVehicleToEntity() {
        // Given
        Vehicle vehicle = Vehicle.builder()
                .id(1)
                .modelId(1)
                .year(2023)
                .color("Preto")
                .price(new BigDecimal("50000.00"))
                .status(VehicleStatus.AVAILABLE)
                .createdAt(LocalDateTime.of(2024, 1, 1, 10, 0))
                .updatedAt(LocalDateTime.of(2024, 1, 2, 10, 0))
                .soldAt(null)
                .build();

        // When
        VehicleEntity entity = mapper.toEntity(vehicle);

        // Then
        assertNotNull(entity);
        assertEquals(1, entity.getId());
        assertEquals(2023, entity.getYear());
        assertEquals("Preto", entity.getColor());
        assertEquals(new BigDecimal("50000.00"), entity.getPrice());
        assertEquals(VehicleStatus.AVAILABLE, entity.getStatus());
        assertEquals(LocalDateTime.of(2024, 1, 1, 10, 0), entity.getCreatedAt());
        assertEquals(LocalDateTime.of(2024, 1, 2, 10, 0), entity.getUpdatedAt());
        assertNull(entity.getSoldAt());
    }

    @Test
    void shouldMapEntityToVehicle() {
        // Given
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setId(1);

        VehicleEntity entity = new VehicleEntity();
        entity.setId(1);
        entity.setYear(2023);
        entity.setColor("Preto");
        entity.setPrice(new BigDecimal("50000.00"));
        entity.setStatus(VehicleStatus.AVAILABLE);
        entity.setModel(modelEntity);
        entity.setCreatedAt(LocalDateTime.of(2024, 1, 1, 10, 0));
        entity.setUpdatedAt(LocalDateTime.of(2024, 1, 2, 10, 0));
        entity.setSoldAt(null);

        // When
        Vehicle vehicle = mapper.toDomain(entity);

        // Then
        assertNotNull(vehicle);
        assertEquals(1, vehicle.getId());
        assertEquals(2023, vehicle.getYear());
        assertEquals("Preto", vehicle.getColor());
        assertEquals(new BigDecimal("50000.00"), vehicle.getPrice());
        assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
        assertEquals(1, vehicle.getModelId()); // Deve extrair modelId do ModelEntity
        assertEquals(LocalDateTime.of(2024, 1, 1, 10, 0), vehicle.getCreatedAt());
        assertEquals(LocalDateTime.of(2024, 1, 2, 10, 0), vehicle.getUpdatedAt());
        assertNull(vehicle.getSoldAt());
    }

    @Test
    void shouldMapEntityToVehicleWhenModelIsNull() {
        // Given
        VehicleEntity entity = new VehicleEntity();
        entity.setId(1);
        entity.setYear(2023);
        entity.setColor("Preto");
        entity.setModel(null);
        entity.setCreatedAt(LocalDateTime.now());

        // When
        Vehicle vehicle = mapper.toDomain(entity);

        // Then
        assertNotNull(vehicle);
        assertEquals(1, vehicle.getId());
        assertNull(vehicle.getModelId()); // modelId deve ser null quando model é null
    }

    @Test
    void shouldReturnNullWhenVehicleIsNull() {
        // When
        VehicleEntity entity = mapper.toEntity(null);

        // Then
        assertNull(entity);
    }

    @Test
    void shouldReturnNullWhenEntityIsNull() {
        // When
        Vehicle vehicle = mapper.toDomain(null);

        // Then
        assertNull(vehicle);
    }
}