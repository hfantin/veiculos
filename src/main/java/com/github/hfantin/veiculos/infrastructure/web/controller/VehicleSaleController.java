package com.github.hfantin.veiculos.infrastructure.web.controller;

import com.github.hfantin.veiculos.domain.exception.BusinessException;
import com.github.hfantin.veiculos.domain.model.Customer;
import com.github.hfantin.veiculos.domain.model.Sale;
import com.github.hfantin.veiculos.domain.service.CustomerService;
import com.github.hfantin.veiculos.domain.service.VehicleSaleService;
import com.github.hfantin.veiculos.infrastructure.web.dto.CompleteSaleRequest;
import com.github.hfantin.veiculos.infrastructure.web.dto.InitiateSaleRequest;
import com.github.hfantin.veiculos.infrastructure.web.dto.InitiateSaleResponse;
import com.github.hfantin.veiculos.infrastructure.web.mapper.VehicleSaleWebMapper;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/vehicle-sales")
@AllArgsConstructor
@Tag(name = "Vendas de Veículos", description = "API para gerenciamento do processo de venda de veículos")
public class VehicleSaleController {

    private final VehicleSaleService vehicleSaleService;

    private final CustomerService customerService;

    @PostMapping("/initiate")
    @Operation(summary = "Iniciar venda de veículo", description = "Inicia o processo de venda de um veículo para o usuário logado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venda iniciada com sucesso",
                    content = @Content(schema = @Schema(implementation = InitiateSaleResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Veículo ou cliente não encontrado"),
            @ApiResponse(responseCode = "409", description = "Veículo não está disponível para venda")
    })
    public ResponseEntity<InitiateSaleResponse> initiateSale(@RequestBody InitiateSaleRequest request) {

        // Obter o ID do cliente logado a partir do contexto de segurança
        Integer customerId = getCurrentCustomerId();

        Sale sale = vehicleSaleService.initiateVehicleSale(request.getVehicleId(), customerId);
        InitiateSaleResponse response = VehicleSaleWebMapper.toInitiateResponse(sale, request.getVehicleId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PostMapping("/{saleId}/complete")
    @Operation(summary = "Completar venda", description = "Confirma o pagamento e completa a venda (simulação de callback do gateway de pagamento)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venda completada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada")
    })
    public ResponseEntity<Void> completeSale(
            @Parameter(description = "ID da venda", example = "1")
            @PathVariable Integer saleId,
            @RequestBody CompleteSaleRequest request) {

        vehicleSaleService.completeSale(saleId, request.getTransactionId());
        return ResponseEntity.ok().build();

    }

    @PostMapping("/{saleId}/cancel")
    @Operation(summary = "Cancelar venda", description = "Cancela uma venda pendente e libera o veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venda cancelada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada")
    })
    public ResponseEntity<Void> cancelSale(
            @Parameter(description = "ID da venda", example = "1")
            @PathVariable Integer saleId) {

        vehicleSaleService.cancelSale(saleId);
        return ResponseEntity.ok().build();

    }

    /**
     * Método auxiliar para obter o ID do cliente logado
     * A partir do contexto de segurança do Spring
     */
    private Integer getCurrentCustomerId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("Usuário não autenticado");
        }

        String authId = authentication.getName();
        log.info("Current authenticated user: {}", authId);
        // Aqui você precisaria buscar o customer pelo authId (username)
        // Por enquanto, vamos simular retornando um ID fixo
        // Em produção, você buscaria do CustomerService usando o username (authId)

        // Exemplo de implementação real:
        Customer customer = customerService.findByAuthId(authId)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado para o usuário logado"));

        log.info("Current customer: {}", customer);
        return customer.getId();
    }
}
