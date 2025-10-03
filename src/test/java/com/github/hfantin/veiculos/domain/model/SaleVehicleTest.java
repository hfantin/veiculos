package com.github.hfantin.veiculos.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SaleVehicleTest {

    @Test
    @DisplayName("Deve criar um SaleVehicle com dados básicos")
    void shouldCreateSaleVehicleWithBasicData() {
        // Given
        Integer saleId = 1;
        Integer vehicleId = 1;
        BigDecimal salePrice = new BigDecimal("45000.00");

        // When
        SaleVehicle saleVehicle = new SaleVehicle(saleId, vehicleId, salePrice);

        // Then
        assertNotNull(saleVehicle);
        assertEquals(saleId, saleVehicle.getSaleId());
        assertEquals(vehicleId, saleVehicle.getVehicleId());
        assertEquals(salePrice, saleVehicle.getSalePrice());
        assertNotNull(saleVehicle.getCreatedAt());
    }

    @Test
    @DisplayName("Deve criar SaleVehicle usando builder pattern")
    void shouldCreateSaleVehicleUsingBuilder() {
        // Given
        LocalDateTime createdAt = LocalDateTime.now();

        // When
        SaleVehicle saleVehicle = SaleVehicle.builder()
                .id(1)
                .saleId(1)
                .vehicleId(1)
                .salePrice(new BigDecimal("50000.00"))
                .createdAt(createdAt)
                .build();

        // Then
        assertNotNull(saleVehicle);
        assertEquals(1, saleVehicle.getId());
        assertEquals(new BigDecimal("50000.00"), saleVehicle.getSalePrice());
        assertEquals(createdAt, saleVehicle.getCreatedAt());
    }
}