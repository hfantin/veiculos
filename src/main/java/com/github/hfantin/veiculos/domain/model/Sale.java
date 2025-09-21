package com.github.hfantin.veiculos.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Sale {
    public enum Status {
        PENDING, COMPLETED, CANCELLED
    }

    private Long id;
    private Long customerId;
    private LocalDateTime saleDate;
    private BigDecimal totalAmount;
    private String paymentMethod;
    private String transactionId;
    private Status status;
    private LocalDateTime createdAt;

    public Sale(Long customerId, BigDecimal totalAmount) {
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.status = Status.PENDING;
        this.saleDate = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }
}
