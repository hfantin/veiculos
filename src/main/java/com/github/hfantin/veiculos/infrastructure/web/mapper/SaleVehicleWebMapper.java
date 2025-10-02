package com.github.hfantin.veiculos.infrastructure.web.mapper;

import com.github.hfantin.veiculos.domain.model.SaleVehicle;
import com.github.hfantin.veiculos.infrastructure.web.dto.SaleVehicleRequest;
import com.github.hfantin.veiculos.infrastructure.web.dto.SaleVehicleResponse;

public class SaleVehicleWebMapper {

    public static SaleVehicle toEntity(SaleVehicleRequest request) {
        return SaleVehicle.builder()
                .saleId(request.getSaleId())
                .vehicleId(request.getVehicleId())
                .salePrice(request.getSalePrice())
                .build();
    }

    public static SaleVehicleResponse toResponse(SaleVehicle saleVehicle) {
        return SaleVehicleResponse.builder()
                .id(saleVehicle.getId())
                .saleId(saleVehicle.getSaleId())
                .vehicleId(saleVehicle.getVehicleId())
                .salePrice(saleVehicle.getSalePrice())
                .createdAt(saleVehicle.getCreatedAt())
                .build();
    }
}
