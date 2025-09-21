package com.github.hfantin.veiculos.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class SaleVehicle {
    private Integer id;
    private Integer saleId;
    private Integer vehicleId;
    private BigDecimal salePrice;
    private LocalDateTime createdAt;

    public SaleVehicle(Integer saleId, Integer vehicleId, BigDecimal salePrice) {
        this.saleId = saleId;
        this.vehicleId = vehicleId;
        this.salePrice = salePrice;
        this.createdAt = LocalDateTime.now();
    }

    public void validate() {
        if (saleId == null) {
            throw new IllegalArgumentException("Sale ID cannot be null");
        }
        if (vehicleId == null) {
            throw new IllegalArgumentException("Vehicle ID cannot be null");
        }
        if (salePrice == null || salePrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Sale price must be greater than 0");
        }
    }
}
