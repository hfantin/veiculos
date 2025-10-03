package com.github.hfantin.veiculos.domain.service;

import com.github.hfantin.veiculos.domain.model.Customer;

import java.util.Optional;

public interface CustomerService {
    Customer createOrUpdateFromOAuth(String authId, String email, String firstName, String lastName);
    Customer updateCustomer(Customer customer);
    Optional<Customer> findById(Integer id);
    Optional<Customer> findByAuthId(String authId);
    Optional<Customer> findByEmail(String email);
}
