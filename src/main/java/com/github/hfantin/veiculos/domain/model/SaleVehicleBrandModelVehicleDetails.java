package com.github.hfantin.veiculos.domain.model;

import com.github.hfantin.veiculos.domain.model.enums.SaleStatus;
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
public class SaleVehicleBrandModelVehicleDetails {

    private Integer brandId;
    private String brandName;
    private Integer modelId;
    private String modelName;
    private Integer year;
    private String color;
    private BigDecimal price;
    private VehicleStatus vehicleStatus;
    private String paymentMethod;
    private String transactionId;
    private SaleStatus status;

}
