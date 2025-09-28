package com.github.hfantin.veiculos.infrastructure.web.controller;

import com.github.hfantin.veiculos.domain.model.Customer;
import com.github.hfantin.veiculos.domain.service.CustomerService;
import com.github.hfantin.veiculos.infrastructure.web.dto.CustomerRequest;
import com.github.hfantin.veiculos.infrastructure.web.dto.CustomerResponse;
import com.github.hfantin.veiculos.infrastructure.web.dto.CustomerUpdateRequest;
import com.github.hfantin.veiculos.infrastructure.web.mapper.CustomerWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Clientes", description = "API para gerenciamento de clientes")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Criar um novo cliente", description = "Cadastra um novo cliente no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso",
                    content = @Content(schema = @Schema(implementation = CustomerResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "409", description = "Cliente já existe com o authId ou email fornecido")
    })
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
    @Operation(summary = "Buscar cliente por ID", description = "Retorna um cliente específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = @Content(schema = @Schema(implementation = CustomerResponse.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<CustomerResponse> findById(
            @Parameter(description = "ID do cliente", example = "1")
            @PathVariable Integer id) {
        Optional<Customer> customer = customerService.findById(id);
        return customer.map(c -> ResponseEntity.ok(CustomerWebMapper.toResponse(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/auth/{authId}")
    @Operation(summary = "Buscar cliente por Auth ID", description = "Retorna um cliente específico pelo seu Auth ID (ID do provedor de autenticação)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = @Content(schema = @Schema(implementation = CustomerResponse.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<CustomerResponse> findByAuthId(
            @Parameter(description = "Auth ID do cliente (ID do provedor de autenticação)", example = "google-oauth2|107110815602563500624")
            @PathVariable String authId) {
        Optional<Customer> customer = customerService.findByAuthId(authId);
        return customer.map(c -> ResponseEntity.ok(CustomerWebMapper.toResponse(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Buscar cliente por email", description = "Retorna um cliente específico pelo seu email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = @Content(schema = @Schema(implementation = CustomerResponse.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<CustomerResponse> findByEmail(
            @Parameter(description = "Email do cliente", example = "cliente@example.com")
            @PathVariable String email) {
        Optional<Customer> customer = customerService.findByEmail(email);
        return customer.map(c -> ResponseEntity.ok(CustomerWebMapper.toResponse(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Listar todos os clientes", description = "Retorna uma lista com todos os clientes cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso")
    public ResponseEntity<List<CustomerResponse>> findAll() {
        List<Customer> customers = customerService.findAll();
        List<CustomerResponse> responses = customers.stream()
                .map(CustomerWebMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente", description = "Atualiza os dados de um cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = CustomerResponse.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<CustomerResponse> update(
            @Parameter(description = "ID do cliente a ser atualizado", example = "1")
            @PathVariable Integer id,
            @RequestBody CustomerUpdateRequest customerRequest) {
        Customer customer = CustomerWebMapper.toEntity(customerRequest);
        customer.setId(id);
        Customer updatedCustomer = customerService.updateCustomer(customer);
        CustomerResponse response = CustomerWebMapper.toResponse(updatedCustomer);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir cliente", description = "Remove um cliente do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ID do cliente a ser excluído", example = "1")
            @PathVariable Integer id) {
        try {
            customerService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting customer: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}