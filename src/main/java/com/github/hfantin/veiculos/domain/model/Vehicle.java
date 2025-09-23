package com.github.hfantin.veiculos.domain.model;

import com.github.hfantin.veiculos.domain.model.enums.VehicleStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Vehicle {
    private Integer id;
    private Integer modelId;
    private String modelName; // Novo campo
    private Integer brandId; // Novo campo
    private String brandName; // Novo campo
    private Integer year;
    private String color;
    private BigDecimal price;
    private VehicleStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime soldAt;

    public Vehicle(Integer modelId, Integer year, String color, BigDecimal price) {
        this.modelId = modelId;
        this.year = year;
        this.color = color;
        this.price = price;
        this.status = VehicleStatus.AVAILABLE;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void validate() {
        if (modelId == null) {
            throw new IllegalArgumentException("Model ID cannot be null");
        }
        if (year == null || year < 1886 || year > LocalDateTime.now().getYear() + 1) {
            throw new IllegalArgumentException("Year must be between 1886 and next year");
        }
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color cannot be null or empty");
        }
        if (color.length() > 50) {
            throw new IllegalArgumentException("Color cannot exceed 50 characters");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
    }

    public void markAsSold() {
        this.status = VehicleStatus.SOLD;
        this.soldAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void reserve() {
        this.status = VehicleStatus.RESERVED;
        this.updatedAt = LocalDateTime.now();
    }

    public void makeAvailable() {
        this.status = VehicleStatus.AVAILABLE;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateVehicle(Integer year, String color, BigDecimal price) {
        this.year = year;
        this.color = color;
        this.price = price;
        this.updatedAt = LocalDateTime.now();
    }
}