package com.github.hfantin.veiculos.application.service;

import com.github.hfantin.veiculos.domain.model.Vehicle;
import com.github.hfantin.veiculos.domain.model.enums.VehicleStatus;
import com.github.hfantin.veiculos.domain.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehicleServiceImplTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @Test
    @DisplayName("Deve salvar um veículo com sucesso")
    void shouldSaveVehicleSuccessfully() {
        // Given
        Vehicle vehicle = new Vehicle(1, 2023, "Preto", new BigDecimal("50000.00"));
        Vehicle savedVehicle = Vehicle.builder()
                .id(1)
                .modelId(1)
                .year(2023)
                .color("Preto")
                .price(new BigDecimal("50000.00"))
                .status(VehicleStatus.AVAILABLE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(savedVehicle);

        // When
        Vehicle result = vehicleService.createVehicle(vehicle);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(VehicleStatus.AVAILABLE, result.getStatus());
        verify(vehicleRepository).save(vehicle);
    }

    @Test
    @DisplayName("Deve encontrar veículo por ID")
    void shouldFindVehicleById() {
        // Given
        Integer vehicleId = 1;
        Vehicle vehicle = Vehicle.builder()
                .id(vehicleId)
                .modelId(1)
                .year(2023)
                .color("Preto")
                .price(new BigDecimal("50000.00"))
                .status(VehicleStatus.AVAILABLE)
                .build();

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));

        // When
        Optional<Vehicle> result = vehicleService.findById(vehicleId);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Preto", result.get().getColor());
        verify(vehicleRepository).findById(vehicleId);
    }

    @Test
    @DisplayName("Deve encontrar veículos por status")
    void shouldFindVehiclesByStatus() {
        // Given
        VehicleStatus status = VehicleStatus.AVAILABLE;
        List<Vehicle> vehicles = Arrays.asList(
                Vehicle.builder().id(1).status(status).build(),
                Vehicle.builder().id(2).status(status).build()
        );
        when(vehicleRepository.findByStatus(status)).thenReturn(vehicles);

        // When
        List<Vehicle> result = vehicleService.getVehiclesByStatus(status);

        // Then
        assertEquals(2, result.size());
        verify(vehicleRepository).findByStatus(status);
    }

    @Test
    @DisplayName("Deve atualizar um veículo")
    void shouldUpdateVehicle() {
        // Given
        Integer vehicleId = 1;
        Vehicle existingVehicle = Vehicle.builder()
                .id(vehicleId)
                .modelId(1)
                .year(2023)
                .color("Preto")
                .price(new BigDecimal("50000.00"))
                .status(VehicleStatus.AVAILABLE)
                .createdAt(LocalDateTime.now().minusDays(1))
                .updatedAt(LocalDateTime.now().minusDays(1))
                .build();

        Vehicle updatedVehicle = Vehicle.builder()
                .id(vehicleId)
                .modelId(1)
                .year(2023)
                .color("Branco") // Cor alterada
                .price(new BigDecimal("55000.00")) // Preço alterado
                .status(VehicleStatus.RESERVED) // Status alterado
                .build();

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(updatedVehicle);

        // When
        Vehicle result = vehicleService.update(updatedVehicle);

        // Then
        assertNotNull(result);
        assertEquals("Branco", result.getColor());
        assertEquals(VehicleStatus.RESERVED, result.getStatus());
        verify(vehicleRepository).findById(vehicleId);
        verify(vehicleRepository).save(any(Vehicle.class));
    }
}
