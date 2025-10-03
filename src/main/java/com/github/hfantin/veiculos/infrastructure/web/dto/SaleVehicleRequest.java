package com.github.hfantin.veiculos.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleVehicleRequest {
    private Integer saleId;
    private Integer vehicleId;
    private BigDecimal salePrice;
}
