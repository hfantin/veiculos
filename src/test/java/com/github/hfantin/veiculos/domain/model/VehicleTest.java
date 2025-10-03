package com.github.hfantin.veiculos.domain.model;

import com.github.hfantin.veiculos.domain.model.enums.VehicleStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    @DisplayName("Deve criar um veículo com status AVAILABLE por padrão")
    void shouldCreateVehicleWithAvailableStatusByDefault() {
        // Given
        Integer modelId = 1;
        Integer year = 2023;
        String color = "Preto";
        BigDecimal price = new BigDecimal("75000.00");

        // When
        Vehicle vehicle = new Vehicle(modelId, year, color, price);

        // Then
        assertNotNull(vehicle);
        assertEquals(modelId, vehicle.getModelId());
        assertEquals(year, vehicle.getYear());
        assertEquals(color, vehicle.getColor());
        assertEquals(price, vehicle.getPrice());
        assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
        assertNotNull(vehicle.getCreatedAt());
        assertNotNull(vehicle.getUpdatedAt());
        assertNull(vehicle.getSoldAt());
    }

    @Test
    @DisplayName("Deve criar veículo usando builder pattern")
    void shouldCreateVehicleUsingBuilder() {
        // Given
        LocalDateTime soldAt = LocalDateTime.now();

        // When
        Vehicle vehicle = Vehicle.builder()
                .id(1)
                .modelId(1)
                .year(2023)
                .color("Branco")
                .price(new BigDecimal("80000.00"))
                .status(VehicleStatus.SOLD)
                .createdAt(LocalDateTime.now().minusDays(1))
                .updatedAt(LocalDateTime.now())
                .soldAt(soldAt)
                .build();

        // Then
        assertNotNull(vehicle);
        assertEquals(1, vehicle.getId());
        assertEquals("Branco", vehicle.getColor());
        assertEquals(VehicleStatus.SOLD, vehicle.getStatus());
        assertEquals(soldAt, vehicle.getSoldAt());
    }

    @ParameterizedTest
    @EnumSource(VehicleStatus.class)
    @DisplayName("Deve aceitar todos os status de veículo")
    void shouldAcceptAllVehicleStatus(VehicleStatus status) {
        // Given & When
        Vehicle vehicle = Vehicle.builder()
                .modelId(1)
                .year(2023)
                .color("Azul")
                .price(new BigDecimal("60000.00"))
                .status(status)
                .build();

        // Then
        assertEquals(status, vehicle.getStatus());
    }
}