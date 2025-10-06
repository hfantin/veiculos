package com.github.hfantin.veiculos.infrastructure.persistence.mapper;

import com.github.hfantin.veiculos.domain.model.Customer;
import com.github.hfantin.veiculos.domain.model.enums.CustomerType;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.CustomerEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    private final CustomerMapper mapper = new CustomerMapper();

    @Test
    void shouldMapCustomerToEntity() {
        // Given
        Customer customer = Customer.builder()
                .id(1)
                .authId("google-oauth2|123456789")
                .firstName("João")
                .lastName("Silva")
                .email("joao.silva@email.com")
                .phone("11999999999")
                .address("Rua ABC, 123")
                .type(CustomerType.BUYER)
                .createdAt(LocalDateTime.of(2024, 1, 1, 10, 0))
                .updatedAt(LocalDateTime.of(2024, 1, 2, 10, 0))
                .build();

        // When
        CustomerEntity entity = mapper.toEntity(customer);

        // Then
        assertNotNull(entity);
        assertEquals(1, entity.getId());
        assertEquals("google-oauth2|123456789", entity.getAuthId());
        assertEquals("João", entity.getFirstName());
        assertEquals("Silva", entity.getLastName());
        assertEquals("joao.silva@email.com", entity.getEmail());
        assertEquals("11999999999", entity.getPhone());
        assertEquals("Rua ABC, 123", entity.getAddress());
        assertEquals(CustomerType.BUYER, entity.getType());
        assertEquals(LocalDateTime.of(2024, 1, 1, 10, 0), entity.getCreatedAt());
        assertEquals(LocalDateTime.of(2024, 1, 2, 10, 0), entity.getUpdatedAt());
    }

    @Test
    void shouldMapEntityToCustomer() {
        // Given
        CustomerEntity entity = new CustomerEntity();
        entity.setId(1);
        entity.setAuthId("google-oauth2|123456789");
        entity.setFirstName("João");
        entity.setLastName("Silva");
        entity.setEmail("joao.silva@email.com");
        entity.setPhone("11999999999");
        entity.setAddress("Rua ABC, 123");
        entity.setType(CustomerType.BUYER);
        entity.setCreatedAt(LocalDateTime.of(2024, 1, 1, 10, 0));
        entity.setUpdatedAt(LocalDateTime.of(2024, 1, 2, 10, 0));

        // When
        Customer customer = mapper.toDomain(entity);

        // Then
        assertNotNull(customer);
        assertEquals(1, customer.getId());
        assertEquals("google-oauth2|123456789", customer.getAuthId());
        assertEquals("João", customer.getFirstName());
        assertEquals("Silva", customer.getLastName());
        assertEquals("joao.silva@email.com", customer.getEmail());
        assertEquals("11999999999", customer.getPhone());
        assertEquals("Rua ABC, 123", customer.getAddress());
        assertEquals(CustomerType.BUYER, customer.getType());
        assertEquals(LocalDateTime.of(2024, 1, 1, 10, 0), customer.getCreatedAt());
        assertEquals(LocalDateTime.of(2024, 1, 2, 10, 0), customer.getUpdatedAt());
    }

    @Test
    void shouldReturnNullWhenCustomerIsNull() {
        // When
        CustomerEntity entity = mapper.toEntity(null);

        // Then
        assertNull(entity);
    }

    @Test
    void shouldReturnNullWhenEntityIsNull() {
        // When
        Customer customer = mapper.toDomain(null);

        // Then
        assertNull(customer);
    }
}