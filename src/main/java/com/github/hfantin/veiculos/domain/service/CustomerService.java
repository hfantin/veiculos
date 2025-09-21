package com.github.hfantin.veiculos.domain.service;

import com.github.hfantin.veiculos.domain.model.Customer;
import com.github.hfantin.veiculos.domain.model.enums.CustomerType;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Integer id, Customer customer);
    Optional<Customer> getCustomerById(Integer id);
    Optional<Customer> getCustomerByAuthId(String authId);
    Optional<Customer> getCustomerByEmail(String email);
    List<Customer> getAllCustomers();
    List<Customer> getCustomersByType(CustomerType type);
    boolean customerExistsByAuthId(String authId);
    boolean customerExistsByEmail(String email);
    void deleteCustomer(Integer id);
    long countCustomers();
}
