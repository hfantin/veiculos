package com.github.hfantin.veiculos.domain.repository;

import com.github.hfantin.veiculos.domain.model.Customer;
import com.github.hfantin.veiculos.domain.model.enums.CustomerType;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(Integer id);
    Optional<Customer> findByAuthId(String authId);
    Optional<Customer> findByEmail(String email);
    List<Customer> findAll();
    List<Customer> findByType(CustomerType type);
    boolean existsByAuthId(String authId);
    boolean existsByEmail(String email);
    boolean existsById(Integer id);
    void deleteById(Integer id);
    long count();
}
