package com.github.hfantin.veiculos.infrastructure.web.mapper;

import com.github.hfantin.veiculos.infrastructure.web.dto.VehicleResponse;
import com.github.hfantin.veiculos.domain.model.Vehicle;
import com.github.hfantin.veiculos.infrastructure.web.dto.VehicleRequest;
import org.springframework.stereotype.Component;

@Component
public class VehicleWebMapper {

    public Vehicle toDomain(VehicleRequest request) {
        Vehicle vehicle = new Vehicle();
        vehicle.setModelId(request.getModelId());
        vehicle.setYear(request.getYear());
        vehicle.setColor(request.getColor());
        vehicle.setPrice(request.getPrice());
        return vehicle;
    }

    public VehicleResponse toResponse(Vehicle vehicle) {
        VehicleResponse response = new VehicleResponse();
        response.setId(vehicle.getId());
        response.setModelId(vehicle.getModelId());
        response.setModelName(vehicle.getModelName());
        response.setBrandId(vehicle.getBrandId());
        response.setBrandName(vehicle.getBrandName());
        response.setYear(vehicle.getYear());
        response.setColor(vehicle.getColor());
        response.setPrice(vehicle.getPrice());
        response.setStatus(vehicle.getStatus());
        response.setCreatedAt(vehicle.getCreatedAt());
        response.setUpdatedAt(vehicle.getUpdatedAt());
        response.setSoldAt(vehicle.getSoldAt());
        return response;
    }
}
