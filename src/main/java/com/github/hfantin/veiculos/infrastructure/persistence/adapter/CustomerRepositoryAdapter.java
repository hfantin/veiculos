package com.github.hfantin.veiculos.infrastructure.persistence.adapter;

import com.github.hfantin.veiculos.domain.model.Customer;
import com.github.hfantin.veiculos.domain.model.enums.CustomerType;
import com.github.hfantin.veiculos.domain.repository.CustomerRepository;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.CustomerEntity;
import com.github.hfantin.veiculos.infrastructure.persistence.mapper.CustomerMapper;
import com.github.hfantin.veiculos.infrastructure.persistence.repository.CustomerJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = customerMapper.toEntity(customer);
        CustomerEntity savedEntity = customerJpaRepository.save(entity);
        return customerMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return customerJpaRepository.findById(id)
                .map(customerMapper::toDomain);
    }

    @Override
    public Optional<Customer> findByAuthId(String authId) {
        return customerJpaRepository.findByAuthId(authId)
                .map(customerMapper::toDomain);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerJpaRepository.findByEmail(email)
                .map(customerMapper::toDomain);
    }

    @Override
    public List<Customer> findAll() {
        return customerJpaRepository.findAll().stream()
                .map(customerMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        customerJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return customerJpaRepository.existsById(id);
    }

    @Override
    public boolean existsByAuthId(String authId) {
        return customerJpaRepository.existsByAuthId(authId);
    }

    @Override
    public boolean existsByEmail(String email) {
        return customerJpaRepository.existsByEmail(email);
    }

    @Override
    public List<Customer> findByType(CustomerType type) {
        return customerJpaRepository.findByType(type).stream()
                .map(customerMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return customerJpaRepository.count();
    }
}
