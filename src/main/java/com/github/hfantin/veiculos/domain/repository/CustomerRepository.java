package com.github.hfantin.veiculos.domain.repository;

import com.github.hfantin.veiculos.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
    Optional<Customer> findByAuthId(String authId);
    Optional<Customer> findByEmail(String email);
    List<Customer> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    boolean existsByAuthId(String authId);
    boolean existsByEmail(String email);
}
