package com.github.hfantin.veiculos.infrastructure.web.controller;

import com.github.hfantin.veiculos.domain.model.enums.VehicleStatus;
import com.github.hfantin.veiculos.domain.service.VehicleService;
import com.github.hfantin.veiculos.infrastructure.web.dto.VehicleRequest;
import com.github.hfantin.veiculos.infrastructure.web.dto.VehicleResponse;
import com.github.hfantin.veiculos.infrastructure.web.mapper.VehicleWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vehicles")
@Tag(name = "Vehicles", description = "API para gerenciamento de veículos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class VehicleController {

    private final VehicleService vehicleService;
    private final VehicleWebMapper vehicleWebMapper;

    @PostMapping
    @Operation(summary = "Criar um novo veículo", description = "Cria um novo veículo no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Veículo criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Modelo não encontrado")
    })
    public ResponseEntity<VehicleResponse> createVehicle(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do veículo a ser criado",
                    required = true,
                    content = @Content(examples = @ExampleObject(value = """
                                {
                                    "modelId": 1,
                                    "year": 2023,
                                    "color": "Preto",
                                    "price": 45000.00
                                }
                            """))
            )
            @Valid @RequestBody VehicleRequest request) {

        var vehicle = vehicleWebMapper.toDomain(request);
        var createdVehicle = vehicleService.createVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vehicleWebMapper.toResponse(createdVehicle));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar veículo por ID", description = "Retorna os detalhes de um veículo específico pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículo encontrado"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    public ResponseEntity<VehicleResponse> getVehicleById(
            @Parameter(description = "ID do veículo", example = "1", required = true)
            @PathVariable Integer id) {

        return vehicleService.getVehicleById(id)
                .map(vehicle -> ResponseEntity.ok(vehicleWebMapper.toResponse(vehicle)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/with-details")
    @Operation(summary = "Buscar veículo por ID com detalhes", description = "Retorna os detalhes de um veículo incluindo modelo e marca")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículo encontrado"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    public ResponseEntity<VehicleResponse> getVehicleByIdWithDetails(
            @Parameter(description = "ID do veículo", example = "1", required = true)
            @PathVariable Integer id) {

        return vehicleService.getVehicleByIdWithDetails(id)
                .map(vehicle -> ResponseEntity.ok(vehicleWebMapper.toResponse(vehicle)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Listar todos os veículos", description = "Retorna uma lista de todos os veículos cadastrados")
    public ResponseEntity<List<VehicleResponse>> getAllVehicles(
            @Parameter(description = "Incluir detalhes de modelo e marca", example = "false")
            @RequestParam(defaultValue = "false") boolean withDetails) {

        List<VehicleResponse> vehicles;
        if (withDetails) {
            vehicles = vehicleService.getAllVehiclesWithDetails().stream()
                    .map(vehicleWebMapper::toResponse)
                    .collect(Collectors.toList());
        } else {
            vehicles = vehicleService.getAllVehicles().stream()
                    .map(vehicleWebMapper::toResponse)
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Listar veículos por status", description = "Retorna uma lista de veículos filtrados por status")
    public ResponseEntity<List<VehicleResponse>> getVehiclesByStatus(
            @Parameter(description = "Status do veículo", example = "AVAILABLE", required = true)
            @PathVariable VehicleStatus status,

            @Parameter(description = "Incluir detalhes de modelo e marca", example = "false")
            @RequestParam(defaultValue = "false") boolean withDetails) {

        List<VehicleResponse> vehicles;
        if (withDetails) {
            vehicles = vehicleService.getVehiclesByStatusWithDetails(status).stream()
                    .map(vehicleWebMapper::toResponse)
                    .collect(Collectors.toList());
        } else {
            vehicles = vehicleService.getVehiclesByStatus(status).stream()
                    .map(vehicleWebMapper::toResponse)
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/model/{modelId}")
    @Operation(summary = "Listar veículos por modelo", description = "Retorna uma lista de veículos de um modelo específico")
    public ResponseEntity<List<VehicleResponse>> getVehiclesByModelId(
            @Parameter(description = "ID do modelo", example = "1", required = true)
            @PathVariable Integer modelId,

            @Parameter(description = "Incluir detalhes de modelo e marca", example = "false")
            @RequestParam(defaultValue = "false") boolean withDetails) {

        List<VehicleResponse> vehicles;
        if (withDetails) {
            vehicles = vehicleService.getVehiclesByModelIdWithDetails(modelId).stream()
                    .map(vehicleWebMapper::toResponse)
                    .collect(Collectors.toList());
        } else {
            vehicles = vehicleService.getVehiclesByModelId(modelId).stream()
                    .map(vehicleWebMapper::toResponse)
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/brand/{brandId}")
    @Operation(summary = "Listar veículos por marca", description = "Retorna uma lista de veículos de uma marca específica")
    public ResponseEntity<List<VehicleResponse>> getVehiclesByBrandId(
            @Parameter(description = "ID da marca", example = "1", required = true)
            @PathVariable Integer brandId,

            @Parameter(description = "Incluir detalhes de modelo e marca", example = "false")
            @RequestParam(defaultValue = "false") boolean withDetails) {

        List<VehicleResponse> vehicles;
        if (withDetails) {
            vehicles = vehicleService.getVehiclesByBrandIdWithDetails(brandId).stream()
                    .map(vehicleWebMapper::toResponse)
                    .collect(Collectors.toList());
        } else {
            vehicles = vehicleService.getVehiclesByBrandId(brandId).stream()
                    .map(vehicleWebMapper::toResponse)
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/price-range")
    @Operation(summary = "Listar veículos por faixa de preço", description = "Retorna uma lista de veículos dentro de uma faixa de preço")
    public ResponseEntity<List<VehicleResponse>> getVehiclesByPriceRange(
            @Parameter(description = "Preço mínimo", example = "10000.00", required = true)
            @RequestParam BigDecimal minPrice,

            @Parameter(description = "Preço máximo", example = "50000.00", required = true)
            @RequestParam BigDecimal maxPrice,

            @Parameter(description = "Incluir detalhes de modelo e marca", example = "false")
            @RequestParam(defaultValue = "false") boolean withDetails) {

        List<VehicleResponse> vehicles;
        if (withDetails) {
            vehicles = vehicleService.getVehiclesByPriceRangeWithDetails(minPrice, maxPrice).stream()
                    .map(vehicleWebMapper::toResponse)
                    .collect(Collectors.toList());
        } else {
            vehicles = vehicleService.getVehiclesByPriceRange(minPrice, maxPrice).stream()
                    .map(vehicleWebMapper::toResponse)
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/year-range")
    @Operation(summary = "Listar veículos por faixa de ano", description = "Retorna uma lista de veículos dentro de uma faixa de ano")
    public ResponseEntity<List<VehicleResponse>> getVehiclesByYearRange(
            @Parameter(description = "Ano inicial", example = "2020", required = true)
            @RequestParam Integer startYear,

            @Parameter(description = "Ano final", example = "2024", required = true)
            @RequestParam Integer endYear,

            @Parameter(description = "Incluir detalhes de modelo e marca", example = "false")
            @RequestParam(defaultValue = "false") boolean withDetails) {

        List<VehicleResponse> vehicles;
        if (withDetails) {
            vehicles = vehicleService.getVehiclesByYearRangeWithDetails(startYear, endYear).stream()
                    .map(vehicleWebMapper::toResponse)
                    .collect(Collectors.toList());
        } else {
            vehicles = vehicleService.getVehiclesByYearRange(startYear, endYear).stream()
                    .map(vehicleWebMapper::toResponse)
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(vehicles);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um veículo", description = "Atualiza os dados de um veículo existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículo atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    public ResponseEntity<VehicleResponse> updateVehicle(
            @Parameter(description = "ID do veículo a ser atualizado", example = "1", required = true)
            @PathVariable Integer id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Novos dados do veículo",
                    required = true,
                    content = @Content(examples = @ExampleObject(value = """
                                {
                                    "modelId": 1,
                                    "year": 2024,
                                    "color": "Branco",
                                    "price": 48000.00
                                }
                            """))
            )
            @Valid @RequestBody VehicleRequest request) {

        var vehicle = vehicleWebMapper.toDomain(request);
        var updatedVehicle = vehicleService.updateVehicle(id, vehicle);
        return ResponseEntity.ok(vehicleWebMapper.toResponse(updatedVehicle));
    }

    @PostMapping("/{id}/purchase")
    @Operation(summary = "Comprar um veículo", description = "Marca um veículo como vendido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículo comprado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    public ResponseEntity<VehicleResponse> purchaseVehicle(
            @Parameter(description = "ID do veículo", example = "1", required = true)
            @PathVariable Integer id,

            @Parameter(description = "ID do cliente", example = "1", required = true)
            @RequestParam Integer customerId) {

        var purchasedVehicle = vehicleService.purchaseVehicle(id, customerId);
        return ResponseEntity.ok(vehicleWebMapper.toResponse(purchasedVehicle));
    }

    @PostMapping("/{id}/reserve")
    @Operation(summary = "Reservar um veículo", description = "Marca um veículo como reservado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículo reservado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    public ResponseEntity<VehicleResponse> reserveVehicle(
            @Parameter(description = "ID do veículo", example = "1", required = true)
            @PathVariable Integer id) {

        var reservedVehicle = vehicleService.reserveVehicle(id);
        return ResponseEntity.ok(vehicleWebMapper.toResponse(reservedVehicle));
    }

    @PostMapping("/{id}/available")
    @Operation(summary = "Disponibilizar um veículo", description = "Marca um veículo como disponível")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículo disponibilizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    public ResponseEntity<VehicleResponse> makeVehicleAvailable(
            @Parameter(description = "ID do veículo", example = "1", required = true)
            @PathVariable Integer id) {

        var availableVehicle = vehicleService.makeVehicleAvailable(id);
        return ResponseEntity.ok(vehicleWebMapper.toResponse(availableVehicle));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um veículo", description = "Remove um veículo do sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Veículo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    public ResponseEntity<Void> deleteVehicle(
            @Parameter(description = "ID do veículo a ser deletado", example = "1", required = true)
            @PathVariable Integer id) {

        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    @Operation(summary = "Contar total de veículos", description = "Retorna o número total de veículos cadastrados")
    public ResponseEntity<Long> countVehicles() {
        return ResponseEntity.ok(vehicleService.countVehicles());
    }
}
