package com.github.hfantin.veiculos.domain.model;

import com.github.hfantin.veiculos.domain.model.enums.SaleStatus;
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

    private Integer id;
    private Integer customerId;
    private LocalDateTime saleDate;
    private BigDecimal totalAmount;
    private String paymentMethod;
    private String transactionId;
    private SaleStatus status;
    private LocalDateTime createdAt;

    public Sale(Integer customerId, BigDecimal totalAmount) {
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.status = SaleStatus.COMPLETED;
        this.saleDate = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    public void validate() {
        if (customerId == null) {
            throw new IllegalArgumentException("Customer ID cannot be null");
        }
        if (totalAmount == null || totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Total amount must be greater than 0");
        }
        if (status == null) {
            throw new IllegalArgumentException("Sale status cannot be null");
        }
    }

    public void cancel() {
        this.status = SaleStatus.CANCELLED;
    }

    public void complete() {
        this.status = SaleStatus.COMPLETED;
    }

    public void pend() {
        this.status = SaleStatus.PENDING;
    }
}
