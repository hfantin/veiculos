package com.github.hfantin.veiculos.domain.model;

import com.github.hfantin.veiculos.domain.model.enums.CustomerType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    @DisplayName("Deve criar um cliente com dados básicos")
    void shouldCreateCustomerWithBasicData() {
        // Given
        String authId = "google-oauth2|123456789";
        String firstName = "João";
        String lastName = "Silva";
        String email = "joao.silva@email.com";

        // When
        Customer customer = new Customer(authId, firstName, lastName, email);

        // Then
        assertNotNull(customer);
        assertEquals(authId, customer.getAuthId());
        assertEquals(firstName, customer.getFirstName());
        assertEquals(lastName, customer.getLastName());
        assertEquals(email, customer.getEmail());
        assertEquals(CustomerType.BUYER, customer.getType());
        assertNotNull(customer.getCreatedAt());
        assertNotNull(customer.getUpdatedAt());
    }

    @Test
    @DisplayName("Deve retornar o nome completo do cliente")
    void shouldReturnFullName() {
        // Given
        Customer customer = Customer.builder()
                .firstName("Maria")
                .lastName("Santos")
                .build();

        // When
        String fullName = customer.getFullName();

        // Then
        assertEquals("Maria Santos", fullName);
    }

    @Test
    @DisplayName("Deve atualizar o perfil do cliente")
    void shouldUpdateCustomerProfile() {
        // Given
        Customer customer = new Customer("auth123", "João", "Silva", "joao@email.com");
        LocalDateTime originalUpdatedAt = customer.getUpdatedAt();

        // When
        customer.updateProfile("João Pedro", "Silva Souza", "11999999999", "Rua ABC, 123");

        // Then
        assertEquals("João Pedro", customer.getFirstName());
        assertEquals("Silva Souza", customer.getLastName());
        assertEquals("11999999999", customer.getPhone());
        assertEquals("Rua ABC, 123", customer.getAddress());
        assertTrue(customer.getUpdatedAt().isAfter(originalUpdatedAt));
    }

    @Test
    @DisplayName("Deve atualizar o tipo do cliente")
    void shouldUpdateCustomerType() {
        // Given
        Customer customer = new Customer("auth123", "João", "Silva", "joao@email.com");
        LocalDateTime originalUpdatedAt = customer.getUpdatedAt();

        // When
        customer.updateType(CustomerType.SELLER);

        // Then
        assertEquals(CustomerType.SELLER, customer.getType());
        assertTrue(customer.getUpdatedAt().isAfter(originalUpdatedAt));
    }

    @Test
    @DisplayName("Deve criar cliente usando builder pattern")
    void shouldCreateCustomerUsingBuilder() {
        // Given & When
        Customer customer = Customer.builder()
                .id(1)
                .authId("auth123")
                .firstName("Carlos")
                .lastName("Oliveira")
                .email("carlos@email.com")
                .phone("11988887777")
                .address("Av. Principal, 456")
                .type(CustomerType.BOTH)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // Then
        assertNotNull(customer);
        assertEquals(1, customer.getId());
        assertEquals("Carlos", customer.getFirstName());
        assertEquals(CustomerType.BOTH, customer.getType());
    }
}