package com.github.hfantin.veiculos.domain.service;

import com.github.hfantin.veiculos.domain.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    Brand createBrand(Brand brand);
    Brand updateBrand(Long id, Brand brand);
    Optional<Brand> getBrandById(Long id);
    Optional<Brand> getBrandByName(String name);
    List<Brand> getAllBrands();
    List<Brand> getAllBrandsOrderedByName();
    boolean brandExists(String name);
    void deleteBrand(Long id);
    long countBrands();
}
