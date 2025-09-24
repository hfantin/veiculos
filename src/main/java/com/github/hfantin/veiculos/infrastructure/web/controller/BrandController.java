package com.github.hfantin.veiculos.infrastructure.web.controller;

import com.github.hfantin.veiculos.domain.service.BrandService;
import com.github.hfantin.veiculos.infrastructure.web.dto.BrandRequest;
import com.github.hfantin.veiculos.infrastructure.web.dto.BrandResponse;
import com.github.hfantin.veiculos.infrastructure.web.mapper.BrandWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/brands")
@Tag(name = "Brands", description = "API para gerenciamento de marcas de veículos")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class BrandController {

    private final BrandService brandService;
    private final BrandWebMapper brandWebMapper;

    public BrandController(BrandService brandService, BrandWebMapper brandWebMapper) {
        this.brandService = brandService;
        this.brandWebMapper = brandWebMapper;
    }

    @PostMapping
    @Operation(summary = "Criar uma nova marca", description = "Cria uma nova marca de veículo no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Marca criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou marca já existe"),
            @ApiResponse(responseCode = "404", description = "Marca não encontrada")
    })
    public ResponseEntity<BrandResponse> createBrand(@Valid @RequestBody BrandRequest request) {
        var brand = brandWebMapper.toDomain(request);
        var createdBrand = brandService.createBrand(brand);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(brandWebMapper.toResponse(createdBrand));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar marca por ID", description = "Retorna os detalhes de uma marca específica pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Marca encontrada"),
            @ApiResponse(responseCode = "404", description = "Marca não encontrada")
    })
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable Integer id) {
        return brandService.getBrandById(id)
                .map(brand -> ResponseEntity.ok(brandWebMapper.toResponse(brand)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Buscar marca por nome", description = "Retorna os detalhes de uma marca específica pelo seu nome")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Marca encontrada"),
            @ApiResponse(responseCode = "404", description = "Marca não encontrada")
    })
    public ResponseEntity<BrandResponse> getBrandByName(@PathVariable String name) {
        return brandService.getBrandByName(name)
                .map(brand -> ResponseEntity.ok(brandWebMapper.toResponse(brand)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Listar todos as marcas", description = "Retorna uma lista de todas as marcas cadastrados")
    public ResponseEntity<List<BrandResponse>> getAllBrands(
            @RequestParam(defaultValue = "false") boolean ordered) {

        List<BrandResponse> brands;
        if (ordered) {
            brands = brandService.getAllBrandsOrderedByName().stream()
                    .map(brandWebMapper::toResponse)
                    .collect(Collectors.toList());
        } else {
            brands = brandService.getAllBrands().stream()
                    .map(brandWebMapper::toResponse)
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(brands);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar uma marca", description = "Atualiza os dados de uma marca existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Marca atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Marca não encontrado")
    })
    public ResponseEntity<BrandResponse> updateBrand(
            @PathVariable Integer id,
            @Valid @RequestBody BrandRequest request) {

        var brand = brandWebMapper.toDomain(request);
        var updatedBrand = brandService.updateBrand(id, brand);
        return ResponseEntity.ok(brandWebMapper.toResponse(updatedBrand));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma marca", description = "Remove uma marca do sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Marca deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Marca não encontrado")
    })
    public ResponseEntity<Void> deleteBrand(@PathVariable Integer id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    @Operation(summary = "Contar total de marcas", description = "Retorna o número total de marcas cadastradas")
    public ResponseEntity<Long> countBrands() {
        return ResponseEntity.ok(brandService.countBrands());
    }

    @GetMapping("/exists/{name}")
    @Operation(summary = "Verificar existência de uma marca", description = "Verifica se uma marca com o nome especificado já existe")
    public ResponseEntity<Boolean> brandExists(@PathVariable String name) {
        return ResponseEntity.ok(brandService.brandExists(name));
    }
}
