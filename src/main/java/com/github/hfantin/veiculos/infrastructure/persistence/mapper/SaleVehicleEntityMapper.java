package com.github.hfantin.veiculos.infrastructure.persistence.mapper;

import com.github.hfantin.veiculos.domain.model.Sale;
import com.github.hfantin.veiculos.domain.model.SaleVehicle;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.SaleEntity;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.SaleVehicleEntity;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Component;

@Component
public class SaleVehicleEntityMapper {

    public SaleVehicleEntity toEntity(SaleVehicle saleVehicle) {
        if (saleVehicle == null) {
            return null;
        }

        SaleVehicleEntity entity = new SaleVehicleEntity();
        entity.setId(saleVehicle.getId());
        if(saleVehicle.getSaleId() != null) {
            entity.setSale(SaleEntity.builder().id(saleVehicle.getSaleId()).build());
        }
        if(saleVehicle.getVehicleId() != null) {
            entity.setVehicle(VehicleEntity.builder().id(saleVehicle.getVehicleId()).build());
        }
        entity.setSalePrice(saleVehicle.getSalePrice());
        entity.setCreatedAt(saleVehicle.getCreatedAt());

        return entity;
    }

    public SaleVehicle toDomain(SaleVehicleEntity entity) {
        if (entity == null) {
            return null;
        }

        return SaleVehicle.builder()
                .id(entity.getId())
                .saleId(entity.getSale().getId())
                .vehicleId(entity.getVehicle().getId())
                .salePrice(entity.getSalePrice())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
