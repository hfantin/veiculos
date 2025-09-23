package com.github.hfantin.veiculos.domain.service;

import com.github.hfantin.veiculos.domain.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    Brand createBrand(Brand brand);
    Brand updateBrand(Integer id, Brand brand);
    Optional<Brand> getBrandById(Integer id);
    Optional<Brand> getBrandByName(String name);
    List<Brand> getAllBrands();
    List<Brand> getAllBrandsOrderedByName();
    void deleteBrand(Integer id);
    boolean brandExists(String name);
    long countBrands();
}
