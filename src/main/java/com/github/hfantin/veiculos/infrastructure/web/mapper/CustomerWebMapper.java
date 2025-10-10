package com.github.hfantin.veiculos.infrastructure.web.mapper;

import com.github.hfantin.veiculos.domain.model.Customer;
import com.github.hfantin.veiculos.infrastructure.web.dto.CustomerResponse;
import com.github.hfantin.veiculos.infrastructure.web.dto.CustomerUpdateRequest;

public class CustomerWebMapper {

    public static Customer toEntity(CustomerUpdateRequest request) {
        return Customer.builder()
                .phone(request.getPhone())
                .address(request.getAddress())
                .cpf(request.getCpf())
                .build();
    }

    public static CustomerResponse toResponse(Customer customer) {
        return CustomerResponse.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .cpf(customer.getCpf())
                .build();
    }
}
