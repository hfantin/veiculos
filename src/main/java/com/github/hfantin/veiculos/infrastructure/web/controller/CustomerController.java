package com.github.hfantin.veiculos.infrastructure.web.controller;

import com.github.hfantin.veiculos.domain.model.Customer;
import com.github.hfantin.veiculos.domain.service.CustomerService;
import com.github.hfantin.veiculos.infrastructure.web.dto.CustomerRequest;
import com.github.hfantin.veiculos.infrastructure.web.dto.CustomerResponse;
import com.github.hfantin.veiculos.infrastructure.web.mapper.CustomerWebMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest customerRequest) {
        try {
            Customer customer = CustomerWebMapper.toEntity(customerRequest);
            Customer savedCustomer = customerService.createCustomer(customer);
            CustomerResponse response = CustomerWebMapper.toResponse(savedCustomer);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("Error creating customer: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable Integer id) {
        Optional<Customer> customer = customerService.findById(id);
        return customer.map(c -> ResponseEntity.ok(CustomerWebMapper.toResponse(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/auth/{authId}")
    public ResponseEntity<CustomerResponse> findByAuthId(@PathVariable String authId) {
        Optional<Customer> customer = customerService.findByAuthId(authId);
        return customer.map(c -> ResponseEntity.ok(CustomerWebMapper.toResponse(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerResponse> findByEmail(@PathVariable String email) {
        Optional<Customer> customer = customerService.findByEmail(email);
        return customer.map(c -> ResponseEntity.ok(CustomerWebMapper.toResponse(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        List<Customer> customers = customerService.findAll();
        List<CustomerResponse> responses = customers.stream()
                .map(CustomerWebMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable Integer id, @RequestBody CustomerRequest customerRequest) {
        try {
            Customer customer = CustomerWebMapper.toEntity(customerRequest);
            customer.setId(id);
            Customer updatedCustomer = customerService.updateCustomer(customer);
            CustomerResponse response = CustomerWebMapper.toResponse(updatedCustomer);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error updating customer: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        try {
            customerService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting customer: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
