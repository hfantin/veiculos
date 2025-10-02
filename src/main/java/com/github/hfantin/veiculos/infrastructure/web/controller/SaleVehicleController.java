package com.github.hfantin.veiculos.infrastructure.web.controller;

import com.github.hfantin.veiculos.domain.model.SaleVehicle;
import com.github.hfantin.veiculos.domain.service.SaleVehicleService;
import com.github.hfantin.veiculos.infrastructure.web.dto.SaleVehicleRequest;
import com.github.hfantin.veiculos.infrastructure.web.dto.SaleVehicleResponse;
import com.github.hfantin.veiculos.infrastructure.web.mapper.SaleVehicleWebMapper;
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
@RequestMapping("/api/sale-vehicles")
@AllArgsConstructor
@Tag(name = "Veículos Vendidos", description = "API para gerenciamento de veículos vendidos")
public class SaleVehicleController {

    private final SaleVehicleService saleVehicleService;

    @PostMapping
    @Operation(summary = "Adicionar veículo à venda", description = "Associa um veículo a uma venda específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Veículo adicionado à venda com sucesso",
                    content = @Content(schema = @Schema(implementation = SaleVehicleResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "409", description = "Veículo já está vendido em outra venda")
    })
    public ResponseEntity<SaleVehicleResponse> create(@RequestBody SaleVehicleRequest saleVehicleRequest) {
        try {
            SaleVehicle saleVehicle = SaleVehicleWebMapper.toEntity(saleVehicleRequest);
            SaleVehicle savedSaleVehicle = saleVehicleService.save(saleVehicle);
            SaleVehicleResponse response = SaleVehicleWebMapper.toResponse(savedSaleVehicle);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("Error creating sale vehicle: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar veículo vendido por ID", description = "Retorna um veículo vendido específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo vendido encontrado",
                    content = @Content(schema = @Schema(implementation = SaleVehicleResponse.class))),
            @ApiResponse(responseCode = "404", description = "Veículo vendido não encontrado")
    })
    public ResponseEntity<SaleVehicleResponse> findById(
            @Parameter(description = "ID do veículo vendido", example = "1")
            @PathVariable Integer id) {
        Optional<SaleVehicle> saleVehicle = saleVehicleService.findById(id);
        return saleVehicle.map(sv -> ResponseEntity.ok(SaleVehicleWebMapper.toResponse(sv)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sale/{saleId}")
    @Operation(summary = "Buscar veículos por venda", description = "Retorna todos os veículos de uma venda específica")
    @ApiResponse(responseCode = "200", description = "Lista de veículos vendidos retornada com sucesso")
    public ResponseEntity<List<SaleVehicleResponse>> findBySaleId(
            @Parameter(description = "ID da venda", example = "1")
            @PathVariable Integer saleId) {
        List<SaleVehicle> saleVehicles = saleVehicleService.findBySaleId(saleId);
        List<SaleVehicleResponse> responses = saleVehicles.stream()
                .map(SaleVehicleWebMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/vehicle/{vehicleId}")
    @Operation(summary = "Buscar venda por veículo", description = "Retorna a venda associada a um veículo específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venda do veículo encontrada",
                    content = @Content(schema = @Schema(implementation = SaleVehicleResponse.class))),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado em nenhuma venda")
    })
    public ResponseEntity<SaleVehicleResponse> findByVehicleId(
            @Parameter(description = "ID do veículo", example = "1")
            @PathVariable Integer vehicleId) {
        Optional<SaleVehicle> saleVehicle = saleVehicleService.findByVehicleId(vehicleId);
        return saleVehicle.map(sv -> ResponseEntity.ok(SaleVehicleWebMapper.toResponse(sv)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Listar todos os veículos vendidos", description = "Retorna uma lista com todos os veículos vendidos")
    @ApiResponse(responseCode = "200", description = "Lista de veículos vendidos retornada com sucesso")
    public ResponseEntity<List<SaleVehicleResponse>> findAll() {
        List<SaleVehicle> saleVehicles = saleVehicleService.findAll();
        List<SaleVehicleResponse> responses = saleVehicles.stream()
                .map(SaleVehicleWebMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar veículo vendido", description = "Atualiza os dados de um veículo vendido existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veículo vendido atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = SaleVehicleResponse.class))),
            @ApiResponse(responseCode = "404", description = "Veículo vendido não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<SaleVehicleResponse> update(
            @Parameter(description = "ID do veículo vendido a ser atualizado", example = "1")
            @PathVariable Integer id,
            @RequestBody SaleVehicleRequest saleVehicleRequest) {
        try {
            SaleVehicle saleVehicle = SaleVehicleWebMapper.toEntity(saleVehicleRequest);
            saleVehicle.setId(id);
            SaleVehicle updatedSaleVehicle = saleVehicleService.update(saleVehicle);
            SaleVehicleResponse response = SaleVehicleWebMapper.toResponse(updatedSaleVehicle);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error updating sale vehicle: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir veículo vendido", description = "Remove um veículo vendido do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Veículo vendido excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo vendido não encontrado")
    })
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ID do veículo vendido a ser excluído", example = "1")
            @PathVariable Integer id) {
        try {
            saleVehicleService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting sale vehicle: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
