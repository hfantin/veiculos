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
    private Long id;
    private Long saleId;
    private Long vehicleId;
    private BigDecimal salePrice;
    private LocalDateTime createdAt;

    public SaleVehicle(Long saleId, Long vehicleId, BigDecimal salePrice) {
        this.saleId = saleId;
        this.vehicleId = vehicleId;
        this.salePrice = salePrice;
        this.createdAt = LocalDateTime.now();
    }
}
