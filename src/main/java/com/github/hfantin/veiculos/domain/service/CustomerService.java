package com.github.hfantin.veiculos.domain.service;

import com.github.hfantin.veiculos.domain.model.Customer;
import com.github.hfantin.veiculos.domain.model.enums.CustomerType;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer createOrUpdateFromOAuth(String authId, String email, String firstName, String lastName);
    Customer updateCustomer(Customer customer);
    Optional<Customer> findById(Integer id);
    Optional<Customer> findByAuthId(String authId);
    Optional<Customer> findByEmail(String email);
    List<Customer> findAll();
    void deleteById(Integer id);
    boolean existsByAuthId(String authId);
    boolean existsByEmail(String email);

}
