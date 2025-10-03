package com.github.hfantin.veiculos.infrastructure.web.controller;

import com.github.hfantin.veiculos.domain.model.Sale;
import com.github.hfantin.veiculos.domain.service.SaleService;
import com.github.hfantin.veiculos.infrastructure.web.dto.SaleRequest;
import com.github.hfantin.veiculos.infrastructure.web.dto.SaleResponse;
import com.github.hfantin.veiculos.infrastructure.web.mapper.SaleWebMapper;
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
@RequestMapping("/api/sales")
@AllArgsConstructor
@Tag(name = "Vendas", description = "API para gerenciamento de vendas")
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    @Operation(summary = "Criar uma nova venda", description = "Cadastra uma nova venda no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venda criada com sucesso",
                    content = @Content(schema = @Schema(implementation = SaleResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<SaleResponse> create(@RequestBody SaleRequest saleRequest) {
        try {
            Sale sale = SaleWebMapper.toEntity(saleRequest);
            Sale savedSale = saleService.save(sale);
            SaleResponse response = SaleWebMapper.toResponse(savedSale);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("Error creating sale: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar venda por ID", description = "Retorna uma venda específica pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venda encontrada",
                    content = @Content(schema = @Schema(implementation = SaleResponse.class))),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada")
    })
    public ResponseEntity<SaleResponse> findById(
            @Parameter(description = "ID da venda", example = "1")
            @PathVariable Integer id) {
        Optional<Sale> sale = saleService.findById(id);
        return sale.map(s -> ResponseEntity.ok(SaleWebMapper.toResponse(s)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Buscar vendas por cliente", description = "Retorna todas as vendas de um cliente específico")
    @ApiResponse(responseCode = "200", description = "Lista de vendas retornada com sucesso")
    public ResponseEntity<List<SaleResponse>> findByCustomerId(
            @Parameter(description = "ID do cliente", example = "1")
            @PathVariable Integer customerId) {
        List<Sale> sales = saleService.findByCustomerId(customerId);
        List<SaleResponse> responses = sales.stream()
                .map(SaleWebMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping
    @Operation(summary = "Listar todas as vendas", description = "Retorna uma lista com todas as vendas cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de vendas retornada com sucesso")
    public ResponseEntity<List<SaleResponse>> findAll() {
        List<Sale> sales = saleService.findAll();
        List<SaleResponse> responses = sales.stream()
                .map(SaleWebMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar venda", description = "Atualiza os dados de uma venda existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venda atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = SaleResponse.class))),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<SaleResponse> update(
            @Parameter(description = "ID da venda a ser atualizada", example = "1")
            @PathVariable Integer id,
            @RequestBody SaleRequest saleRequest) {
        try {
            Sale sale = SaleWebMapper.toEntity(saleRequest);
            sale.setId(id);
            Sale updatedSale = saleService.update(sale);
            SaleResponse response = SaleWebMapper.toResponse(updatedSale);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error updating sale: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir venda", description = "Remove uma venda do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Venda excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada")
    })
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ID da venda a ser excluída", example = "1")
            @PathVariable Integer id) {
        try {
            saleService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting sale: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
