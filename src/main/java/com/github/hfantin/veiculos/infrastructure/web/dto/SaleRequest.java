package com.github.hfantin.veiculos.infrastructure.web.dto;

import com.github.hfantin.veiculos.domain.model.enums.SaleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequest {
    private Integer customerId;
    private LocalDateTime saleDate;
    private BigDecimal totalAmount;
    private String paymentMethod;
    private String transactionId;
    private SaleStatus status;
}