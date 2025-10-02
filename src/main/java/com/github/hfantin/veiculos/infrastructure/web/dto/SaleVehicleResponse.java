package com.github.hfantin.veiculos.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleVehicleResponse {
    private Integer id;
    private Integer saleId;
    private Integer vehicleId;
    private String vehicleInfo;
    private BigDecimal salePrice;
    private LocalDateTime createdAt;
}
