package com.github.hfantin.veiculos.infrastructure.web.mapper;

import com.github.hfantin.veiculos.domain.model.Sale;
import com.github.hfantin.veiculos.infrastructure.web.dto.InitiateSaleResponse;

public class VehicleSaleWebMapper {

    public static InitiateSaleResponse toInitiateResponse(Sale sale, Integer vehicleId) {
        return InitiateSaleResponse.builder()
                .saleId(sale.getId())
                .vehicleId(vehicleId)
                .totalAmount(sale.getTotalAmount())
                .status(sale.getStatus())
                .createdAt(sale.getCreatedAt())
                .paymentUrl(generatePaymentUrl(sale.getId())) // Simular URL de pagamento
                .build();
    }

    private static String generatePaymentUrl(Integer saleId) {
        // Simular geração de URL de pagamento (Mercado Pago, etc.)
        return "https://api.mercadopago.com/checkout/v1/redirect?preference_id=" + saleId + "_" + System.currentTimeMillis();
    }
}
