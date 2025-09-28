package com.github.hfantin.veiculos.infrastructure.web.mapper;

import com.github.hfantin.veiculos.domain.model.Customer;
import com.github.hfantin.veiculos.domain.model.enums.CustomerType;
import com.github.hfantin.veiculos.infrastructure.web.dto.CustomerRequest;
import com.github.hfantin.veiculos.infrastructure.web.dto.CustomerResponse;

public class CustomerWebMapper {

    public static Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .authId(request.getAuthId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .type(request.getType() != null ? request.getType() : CustomerType.BUYER)
                .build();
    }

    public static CustomerResponse toResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .authId(customer.getAuthId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .type(customer.getType())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .build();
    }
}
