package com.github.hfantin.veiculos.domain.model;

import com.github.hfantin.veiculos.domain.model.enums.SaleStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {

    @Test
    @DisplayName("Deve criar uma venda com status COMPLETED por padrão")
    void shouldCreateSaleWithPendingStatusByDefault() {
        // Given
        Integer customerId = 1;
        BigDecimal totalAmount = new BigDecimal("50000.00");

        // When
        Sale sale = new Sale(customerId, totalAmount);

        // Then
        assertNotNull(sale);
        assertEquals(customerId, sale.getCustomerId());
        assertEquals(totalAmount, sale.getTotalAmount());
        assertEquals(SaleStatus.COMPLETED, sale.getStatus());
        assertNotNull(sale.getSaleDate());
        assertNotNull(sale.getCreatedAt());
    }

    @Test
    @DisplayName("Deve criar uma venda usando builder pattern")
    void shouldCreateSaleUsingBuilder() {
        // Given
        LocalDateTime saleDate = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now().minusHours(1);

        // When
        Sale sale = Sale.builder()
                .id(1)
                .customerId(1)
                .saleDate(saleDate)
                .totalAmount(new BigDecimal("75000.00"))
                .paymentMethod("CREDIT_CARD")
                .transactionId("txn_123456")
                .status(SaleStatus.COMPLETED)
                .createdAt(createdAt)
                .build();

        // Then
        assertNotNull(sale);
        assertEquals(1, sale.getId());
        assertEquals(new BigDecimal("75000.00"), sale.getTotalAmount());
        assertEquals(SaleStatus.COMPLETED, sale.getStatus());
        assertEquals("CREDIT_CARD", sale.getPaymentMethod());
        assertEquals(saleDate, sale.getSaleDate());
    }

    @ParameterizedTest
    @EnumSource(SaleStatus.class)
    @DisplayName("Deve aceitar todos os status de venda")
    void shouldAcceptAllSaleStatus(SaleStatus status) {
        // Given & When
        Sale sale = Sale.builder()
                .customerId(1)
                .totalAmount(new BigDecimal("100000.00"))
                .status(status)
                .build();

        // Then
        assertEquals(status, sale.getStatus());
    }
}