package com.github.hfantin.veiculos.infrastructure.persistence.mapper;

import com.github.hfantin.veiculos.domain.model.Vehicle;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.ModelEntity;
import com.github.hfantin.veiculos.infrastructure.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public VehicleEntity toEntity(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }

        VehicleEntity entity = new VehicleEntity();
        entity.setId(vehicle.getId());

        // Criar uma ModelEntity mínima apenas com o ID
        if (vehicle.getModelId() != null) {
            var modelEntity = new ModelEntity();
            modelEntity.setId(vehicle.getModelId());
            entity.setModel(modelEntity);
        }

        entity.setYear(vehicle.getYear());
        entity.setColor(vehicle.getColor());
        entity.setPrice(vehicle.getPrice());
        entity.setStatus(vehicle.getStatus());
        entity.setCreatedAt(vehicle.getCreatedAt());
        entity.setUpdatedAt(vehicle.getUpdatedAt());
        entity.setSoldAt(vehicle.getSoldAt());
        return entity;
    }

    public Vehicle toDomain(VehicleEntity entity) {
        if (entity == null) {
            return null;
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setId(entity.getId());
        vehicle.setModelId(entity.getModel() != null ? entity.getModel().getId() : null);
        vehicle.setYear(entity.getYear());
        vehicle.setColor(entity.getColor());
        vehicle.setPrice(entity.getPrice());
        vehicle.setStatus(entity.getStatus());
        vehicle.setCreatedAt(entity.getCreatedAt());
        vehicle.setUpdatedAt(entity.getUpdatedAt());
        vehicle.setSoldAt(entity.getSoldAt());

        return vehicle;
    }

    public Vehicle toDomainWithDetails(VehicleEntity entity) {
        if (entity == null) {
            return null;
        }

        Vehicle vehicle = toDomain(entity);

        // Adicionar detalhes do modelo e marca
        if (entity.getModel() != null) {
            vehicle.setModelName(entity.getModel().getName());

            if (entity.getModel().getBrand() != null) {
                vehicle.setBrandId(entity.getModel().getBrand().getId());
                vehicle.setBrandName(entity.getModel().getBrand().getName());
            }
        }

        return vehicle;
    }
}
