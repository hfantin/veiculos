package com.github.hfantin.veiculos.domain.service;

import com.github.hfantin.veiculos.domain.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    List<Brand> getAllBrands();
    List<Brand> getAllBrandsOrderedByName();
    Optional<Brand> getBrandById(Integer id);
    Optional<Brand> getBrandByName(String name);
    Brand createBrand(Brand brand);
    Brand updateBrand(Integer id, Brand brand);
    void deleteBrand(Integer id);
}
