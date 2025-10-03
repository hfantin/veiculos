package com.github.hfantin.veiculos.infrastructure.persistence.mapper;

import com.github.hfantin.veiculos.domain.model.Sale;
import com.github.hfantin.veiculos.domain.model.enums.SaleStatus;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.CustomerEntity;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.SaleEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SaleEntityMapperTest {

    private final SaleEntityMapper mapper = new SaleEntityMapper();

    @Test
    void shouldMapSaleToEntity() {
        // Given
        Sale sale = Sale.builder()
                .id(1)
                .customerId(1)
                .saleDate(LocalDateTime.of(2024, 1, 1, 10, 0))
                .totalAmount(new BigDecimal("50000.00"))
                .paymentMethod("CREDIT_CARD")
                .transactionId("txn_123456")
                .status(SaleStatus.COMPLETED)
                .createdAt(LocalDateTime.of(2024, 1, 1, 9, 0))
                .build();

        // When
        SaleEntity entity = mapper.toEntity(sale);

        // Then
        assertNotNull(entity);
        assertEquals(1, entity.getId());
        assertEquals(LocalDateTime.of(2024, 1, 1, 10, 0), entity.getSaleDate());
        assertEquals(new BigDecimal("50000.00"), entity.getTotalAmount());
        assertEquals("CREDIT_CARD", entity.getPaymentMethod());
        assertEquals("txn_123456", entity.getTransactionId());
        assertEquals(SaleStatus.COMPLETED, entity.getStatus());
        assertEquals(LocalDateTime.of(2024, 1, 1, 9, 0), entity.getCreatedAt());
    }

    @Test
    void shouldMapEntityToSale() {
        // Given
        SaleEntity entity = new SaleEntity();
        entity.setId(1);
        entity.setCustomer(new CustomerEntity());
        entity.setSaleDate(LocalDateTime.of(2024, 1, 1, 10, 0));
        entity.setTotalAmount(new BigDecimal("50000.00"));
        entity.setPaymentMethod("CREDIT_CARD");
        entity.setTransactionId("txn_123456");
        entity.setStatus(SaleStatus.COMPLETED);
        entity.setCreatedAt(LocalDateTime.of(2024, 1, 1, 9, 0));

        // When
        Sale sale = mapper.toDomain(entity);

        // Then
        assertNotNull(sale);
        assertEquals(1, sale.getId());
        assertEquals(LocalDateTime.of(2024, 1, 1, 10, 0), sale.getSaleDate());
        assertEquals(new BigDecimal("50000.00"), sale.getTotalAmount());
        assertEquals("CREDIT_CARD", sale.getPaymentMethod());
        assertEquals("txn_123456", sale.getTransactionId());
        assertEquals(SaleStatus.COMPLETED, sale.getStatus());
        assertEquals(LocalDateTime.of(2024, 1, 1, 9, 0), sale.getCreatedAt());
    }

    @Test
    void shouldReturnNullWhenSaleIsNull() {
        // When
        SaleEntity entity = mapper.toEntity(null);

        // Then
        assertNull(entity);
    }

    @Test
    void shouldReturnNullWhenEntityIsNull() {
        // When
        Sale sale = mapper.toDomain(null);

        // Then
        assertNull(sale);
    }
}