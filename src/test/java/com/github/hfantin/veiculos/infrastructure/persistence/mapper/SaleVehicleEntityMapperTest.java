package com.github.hfantin.veiculos.infrastructure.persistence.mapper;

import com.github.hfantin.veiculos.domain.model.SaleVehicle;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.SaleEntity;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.SaleVehicleEntity;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.VehicleEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SaleVehicleEntityMapperTest {

    private final SaleVehicleEntityMapper mapper = new SaleVehicleEntityMapper();

    @Test
    void shouldMapSaleVehicleToEntity() {
        // Given
        SaleVehicle saleVehicle = SaleVehicle.builder()
                .id(1)
                .saleId(1)
                .vehicleId(1)
                .salePrice(new BigDecimal("45000.00"))
                .createdAt(LocalDateTime.of(2024, 1, 1, 10, 0))
                .build();

        // When
        SaleVehicleEntity entity = mapper.toEntity(saleVehicle);

        // Then
        assertNotNull(entity);
        assertEquals(1, entity.getId());
        assertEquals(new BigDecimal("45000.00"), entity.getSalePrice());
        assertEquals(LocalDateTime.of(2024, 1, 1, 10, 0), entity.getCreatedAt());
    }

    @Test
    void shouldMapEntityToSaleVehicle() {
        // Given
        SaleVehicleEntity entity = new SaleVehicleEntity();
        entity.setId(1);
        SaleEntity sale = new SaleEntity();
        sale.setId(1);
        entity.setSale(sale);
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setId(1);
        entity.setVehicle(vehicle);
        entity.setSalePrice(new BigDecimal("45000.00"));
        entity.setCreatedAt(LocalDateTime.of(2024, 1, 1, 10, 0));

        // When
        SaleVehicle saleVehicle = mapper.toDomain(entity);

        // Then
        assertNotNull(saleVehicle);
        assertEquals(1, saleVehicle.getId());
        assertEquals(1, saleVehicle.getSaleId());
        assertEquals(1, saleVehicle.getVehicleId());
        assertEquals(new BigDecimal("45000.00"), saleVehicle.getSalePrice());
        assertEquals(LocalDateTime.of(2024, 1, 1, 10, 0), saleVehicle.getCreatedAt());
    }

    @Test
    void shouldReturnNullWhenSaleVehicleIsNull() {
        // When
        SaleVehicleEntity entity = mapper.toEntity(null);

        // Then
        assertNull(entity);
    }

    @Test
    void shouldReturnNullWhenEntityIsNull() {
        // When
        SaleVehicle saleVehicle = mapper.toDomain(null);

        // Then
        assertNull(saleVehicle);
    }
}