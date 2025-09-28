package com.github.hfantin.veiculos.application.service;

import com.github.hfantin.veiculos.domain.model.Customer;
import com.github.hfantin.veiculos.domain.model.enums.CustomerType;
import com.github.hfantin.veiculos.domain.repository.CustomerRepository;
import com.github.hfantin.veiculos.domain.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        customer.validate();

        // Verificar se já existe customer com este authId
        if (customerRepository.findByAuthId(customer.getAuthId()).isPresent()) {
            throw new IllegalArgumentException("Customer with authId '" + customer.getAuthId() + "' already exists");
        }

        // Verificar se já existe customer com este email
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Customer with email '" + customer.getEmail() + "' already exists");
        }

        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        return customerRepository.save(customer);
    }

    @Override
    public Customer createOrUpdateFromOAuth(String authId, String email, String firstName, String lastName) {
        log.info("Creating or updating customer from OAuth - authId: {}, email: {}", authId, email);

        // Primeiro tenta encontrar pelo authId
        Optional<Customer> existingByAuthId = customerRepository.findByAuthId(authId);
        if (existingByAuthId.isPresent()) {
            Customer customer = existingByAuthId.get();
            customer.setEmail(email);
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setUpdatedAt(LocalDateTime.now());
            return customerRepository.save(customer);
        }

        // Se não encontrou pelo authId, tenta pelo email
        Optional<Customer> existingByEmail = customerRepository.findByEmail(email);
        if (existingByEmail.isPresent()) {
            Customer customer = existingByEmail.get();
            customer.setAuthId(authId); // Atualiza o authId
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setUpdatedAt(LocalDateTime.now());
            return customerRepository.save(customer);
        }

        // Se não existe, cria novo
        Customer newCustomer = Customer.builder()
                .authId(authId)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .type(CustomerType.BUYER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return customerRepository.save(newCustomer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        customer.validateForUpdate();

        log.info("Updating customer with ID: {}", customer.getId());

        Customer existingCustomer = customerRepository.findById(customer.getId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + customer.getId()));

        // Atualizar apenas os campos permitidos
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setType(customer.getType());
        existingCustomer.setUpdatedAt(LocalDateTime.now());

        return customerRepository.save(existingCustomer);
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> findByAuthId(String authId) {
        return customerRepository.findByAuthId(authId);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }


    @Override
    public void deleteById(Integer id) {
        if (!customerRepository.existsById(id)) {
            throw new IllegalArgumentException("Customer not found with ID: " + id);
        }
        customerRepository.deleteById(id);
        log.info("Customer deleted with ID: {}", id);
    }

    @Override
    public boolean existsByAuthId(String authId) {
        return customerRepository.findByAuthId(authId).isPresent();
    }

    @Override
    public boolean existsByEmail(String email) {
        return customerRepository.findByEmail(email).isPresent();
    }



}
