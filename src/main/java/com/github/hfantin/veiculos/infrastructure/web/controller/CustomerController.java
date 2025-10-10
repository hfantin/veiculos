package com.github.hfantin.veiculos.infrastructure.web.controller;

import com.github.hfantin.veiculos.domain.model.Customer;
import com.github.hfantin.veiculos.domain.service.CustomerService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
@Tag(name = "1 - Clientes", description = "API para gerenciamento de clientes")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    @Operation(summary = "Buscar cliente logado", description = "Retorna o cliente logado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = @Content(schema = @Schema(implementation = CustomerResponse.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<CustomerResponse> findById(@AuthenticationPrincipal OidcUser oidcUser) {
        Map<String, Object> claims = oidcUser.getClaims();
        String email = (String) claims.get("email");
        Optional<Customer> customer = customerService.findByEmail(email);
        return customer.map(c -> ResponseEntity.ok(CustomerWebMapper.toResponse(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    @Operation(summary = "Atualizar cliente", description = "Atualiza os dados do cliente logado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = CustomerResponse.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<CustomerResponse> update(
            @AuthenticationPrincipal OidcUser oidcUser,
            @RequestBody CustomerUpdateRequest customerRequest) {
        Map<String, Object> claims = oidcUser.getClaims();
        String email = (String) claims.get("email");
        Customer customer = CustomerWebMapper.toEntity(customerRequest);
        customer.setEmail(email);
        Customer updatedCustomer = customerService.updateCustomer(customer);
        CustomerResponse response = CustomerWebMapper.toResponse(updatedCustomer);
        return ResponseEntity.ok(response);

    }

}