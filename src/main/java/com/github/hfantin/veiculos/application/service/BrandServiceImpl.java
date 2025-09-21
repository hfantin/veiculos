package com.github.hfantin.veiculos.application.service;

import com.github.hfantin.veiculos.domain.model.Brand;
import com.github.hfantin.veiculos.domain.repository.BrandRepository;
import com.github.hfantin.veiculos.domain.service.BrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand createBrand(Brand brand) {
        brand.validate();

        if (brandRepository.existsByName(brand.getName())) {
            throw new IllegalArgumentException("Brand with name '" + brand.getName() + "' already exists");
        }

        return brandRepository.save(brand);
    }

    @Override
    public Brand updateBrand(Long id, Brand brand) {
        brand.validate();

        return brandRepository.findById(id)
                .map(existingBrand -> {
                    // Check if new name conflicts with other brands
                    if (!existingBrand.getName().equals(brand.getName()) &&
                            brandRepository.existsByName(brand.getName())) {
                        throw new IllegalArgumentException("Brand with name '" + brand.getName() + "' already exists");
                    }

                    existingBrand.setName(brand.getName());
                    return brandRepository.save(existingBrand);
                })
                .orElseThrow(() -> new IllegalArgumentException("Brand not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Brand> getBrandById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Brand> getBrandByName(String name) {
        return brandRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Brand> getAllBrandsOrderedByName() {
        return brandRepository.findAllOrderedByName();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean brandExists(String name) {
        return brandRepository.existsByName(name);
    }

    @Override
    public void deleteBrand(Long id) {
        if (!brandRepository.existsById(id)) {
            throw new IllegalArgumentException("Brand not found with id: " + id);
        }
        brandRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long countBrands() {
        return brandRepository.count();
    }
}
