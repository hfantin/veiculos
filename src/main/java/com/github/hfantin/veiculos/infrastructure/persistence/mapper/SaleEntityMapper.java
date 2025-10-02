package com.github.hfantin.veiculos.infrastructure.persistence.mapper;

import com.github.hfantin.veiculos.domain.model.Sale;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.CustomerEntity;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.SaleEntity;
import org.springframework.stereotype.Component;

@Component
public class SaleEntityMapper {

    public SaleEntity toEntity(Sale sale) {
        if (sale == null) {
            return null;
        }

        SaleEntity entity = new SaleEntity();
        entity.setId(sale.getId());
        if(sale.getCustomerId() != null) {
            entity.setCustomer(CustomerEntity.builder().id(sale.getCustomerId()).build());
        }
        entity.setSaleDate(sale.getSaleDate());
        entity.setTotalAmount(sale.getTotalAmount());
        entity.setPaymentMethod(sale.getPaymentMethod());
        entity.setTransactionId(sale.getTransactionId());
        entity.setStatus(sale.getStatus());
        entity.setCreatedAt(sale.getCreatedAt());

        return entity;
    }

    public Sale toDomain(SaleEntity entity) {
        if (entity == null) {
            return null;
        }

        return Sale.builder()
                .id(entity.getId())
                .customerId(entity.getCustomer().getId())
                .saleDate(entity.getSaleDate())
                .totalAmount(entity.getTotalAmount())
                .paymentMethod(entity.getPaymentMethod())
                .transactionId(entity.getTransactionId())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
