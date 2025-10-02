package com.github.hfantin.veiculos.infrastructure.web.mapper;

import com.github.hfantin.veiculos.domain.model.Sale;
import com.github.hfantin.veiculos.domain.model.enums.SaleStatus;
import com.github.hfantin.veiculos.infrastructure.web.dto.SaleRequest;
import com.github.hfantin.veiculos.infrastructure.web.dto.SaleResponse;

public class SaleWebMapper {

    public static Sale toEntity(SaleRequest request) {
        return Sale.builder()
                .customerId(request.getCustomerId())
                .saleDate(request.getSaleDate())
                .totalAmount(request.getTotalAmount())
                .paymentMethod(request.getPaymentMethod())
                .transactionId(request.getTransactionId())
                .status(request.getStatus() != null ? request.getStatus() : SaleStatus.PENDING)
                .build();
    }

    public static SaleResponse toResponse(Sale sale) {
        return SaleResponse.builder()
                .id(sale.getId())
                .customerId(sale.getCustomerId())
                .saleDate(sale.getSaleDate())
                .totalAmount(sale.getTotalAmount())
                .paymentMethod(sale.getPaymentMethod())
                .transactionId(sale.getTransactionId())
                .status(sale.getStatus())
                .createdAt(sale.getCreatedAt())
                .build();
    }
}
