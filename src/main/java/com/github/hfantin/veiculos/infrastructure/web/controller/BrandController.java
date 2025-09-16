package com.github.hfantin.veiculos.infrastructure.web.controller;

import com.github.hfantin.veiculos.domain.service.BrandService;
import com.github.hfantin.veiculos.infrastructure.web.dto.BrandRequest;
import com.github.hfantin.veiculos.infrastructure.web.dto.BrandResponse;
import com.github.hfantin.veiculos.infrastructure.web.mapper.BrandWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;
    private final BrandWebMapper brandWebMapper;

    public BrandController(BrandService brandService, BrandWebMapper brandWebMapper) {
        this.brandService = brandService;
        this.brandWebMapper = brandWebMapper;
    }

    @PostMapping
    public ResponseEntity<BrandResponse> createBrand(@Valid @RequestBody BrandRequest request) {
        var brand = brandWebMapper.toDomain(request);
        var createdBrand = brandService.createBrand(brand);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(brandWebMapper.toResponse(createdBrand));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable Long id) {
        return brandService.getBrandById(id)
                .map(brand -> ResponseEntity.ok(brandWebMapper.toResponse(brand)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<BrandResponse> getBrandByName(@PathVariable String name) {
        return brandService.getBrandByName(name)
                .map(brand -> ResponseEntity.ok(brandWebMapper.toResponse(brand)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
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
    public ResponseEntity<BrandResponse> updateBrand(
            @PathVariable Long id,
            @Valid @RequestBody BrandRequest request) {

        var brand = brandWebMapper.toDomain(request);
        var updatedBrand = brandService.updateBrand(id, brand);
        return ResponseEntity.ok(brandWebMapper.toResponse(updatedBrand));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countBrands() {
        return ResponseEntity.ok(brandService.countBrands());
    }

    @GetMapping("/exists/{name}")
    public ResponseEntity<Boolean> brandExists(@PathVariable String name) {
        return ResponseEntity.ok(brandService.brandExists(name));
    }
}
