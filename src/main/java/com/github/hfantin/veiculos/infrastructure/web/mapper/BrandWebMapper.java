package com.github.hfantin.veiculos.infrastructure.web.mapper;

import com.github.hfantin.veiculos.domain.model.Brand;
import com.github.hfantin.veiculos.infrastructure.web.dto.BrandRequest;
import com.github.hfantin.veiculos.infrastructure.web.dto.BrandResponse;
import org.springframework.stereotype.Component;

@Component
public class BrandWebMapper {

    public Brand toDomain(BrandRequest request) {
        return Brand.builder().name(request.name()).build();
    }

    public BrandResponse toResponse(Brand brand) {
        return new BrandResponse(
                brand.getId(),
                brand.getName(),
                brand.getCreatedAt()
        );
    }
}
