package com.github.hfantin.veiculos.infrastructure.web.dto;

import com.github.hfantin.veiculos.domain.model.Sale;
import com.github.hfantin.veiculos.domain.model.enums.SaleStatus;
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
public class InitiateSaleResponse {
    private Integer saleId;
    private Integer vehicleId;
    private BigDecimal totalAmount;
    private SaleStatus status;
    private LocalDateTime createdAt;
    private String paymentUrl; // URL para simular pagamento (Mercado Pago, etc.)
}
