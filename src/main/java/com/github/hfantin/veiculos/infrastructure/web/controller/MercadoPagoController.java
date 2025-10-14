package com.github.hfantin.veiculos.infrastructure.web.controller;

import com.github.hfantin.veiculos.domain.service.MercadoPagoService;
import com.github.hfantin.veiculos.domain.service.VehicleSaleService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/public/pagamentos")
@Hidden
public class MercadoPagoController {

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @Autowired
    private VehicleSaleService vehicleSaleService;


    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping("/webhook")
    public ResponseEntity<String> receberWebhook(@RequestBody(required = false) Map<String, Object> payload,
                                                 @RequestParam Map<String, String> params) {
        //Outra forma de obter o status do pagamento é via webhook
        String idPagamento = mercadoPagoService.obterIdPagamento(payload, params);
        log.info("pagamentos webhook - queryParams={}, payload={}, idPagamento={}", params, payload, idPagamento);
        String status = mercadoPagoService.verificarStatusPagamento(idPagamento);
        log.info("status={}", status);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/success")
    public ResponseEntity<?> paymentSuccess(
            @RequestParam("collection_id") String collectionId,
            @RequestParam("collection_status") String collectionStatus,
            @RequestParam("external_reference") String externalReference,
            @RequestParam("payment_type") String paymentType,
            @RequestParam("merchant_order_id") String merchantOrderId,
            @RequestParam("preference_id") String preferenceId) {
        vehicleSaleService.completeSale(Integer.valueOf(externalReference), merchantOrderId, paymentType);
        return ResponseEntity.ok(Map.of(
                "msg", "pagamento aprovado com sucesso!",
                "collectionId", collectionId,
                "status", collectionStatus,
                "saleId", externalReference,
                "paymentType", paymentType,
                "orderId", merchantOrderId,
                "preferenceId", preferenceId
        ));
    }

    @GetMapping("/failure")
    public ResponseEntity<?> paymentFailure(
            @RequestParam(value = "external_reference", required = false) String externalReference,
            @RequestParam(value = "collection_status", required = false) String collectionStatus) {

        vehicleSaleService.cancelSale(Integer.valueOf(externalReference));
        return ResponseEntity.ok(Map.of(
                "msg", "pagamento rejeitado.",
                "saleId", externalReference,
                "status", collectionStatus));
    }

    @GetMapping("/pending")
    public ResponseEntity<?> paymentPending(
            @RequestParam("external_reference") String externalReference,
            @RequestParam("collection_status") String collectionStatus,
            @RequestParam("preference_id") String preferenceId) {

        return ResponseEntity.ok(Map.of(
                "msg", "pagamento pendente.",
                "saleId", externalReference,
                "status", collectionStatus,
                "preferenceId", preferenceId
        ));

    }

}