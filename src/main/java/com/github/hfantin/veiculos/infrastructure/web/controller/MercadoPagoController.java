package com.github.hfantin.veiculos.infrastructure.web.controller;

import com.github.hfantin.veiculos.domain.service.MercadoPagoService;
import com.github.hfantin.veiculos.domain.service.VehicleSaleService;
import com.github.hfantin.veiculos.infrastructure.web.events.StatusPedidoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/public/pagamentos")
public class MercadoPagoController {

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @Autowired
    private VehicleSaleService vehicleSaleService;


    @Autowired
    private ApplicationEventPublisher eventPublisher;

    // Criar pagamento (mantém o original)
    /*@PostMapping("/criar")
    public ResponseEntity<PagamentoResponse> criarPagamento(@RequestBody PagamentoRequest request) {
        try {
            PagamentoResponse response = mercadoPagoService.criarPagamento(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("erro ao criar pagamento: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }*/


    /*@GetMapping("/status/{paymentId}")
    public ResponseEntity<Map<String, String>> verificarStatus(@PathVariable String paymentId) {
        try {
            String status = mercadoPagoService.verificarStatusPagamento(paymentId);
            Map<String, String> response = new HashMap<>();
            response.put("paymentId", paymentId);
            response.put("status", status);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }*/


    @PostMapping("/webhook")
    public ResponseEntity<String> receberWebhook(@RequestBody(required = false) Map<String, Object> payload,
                                                 @RequestParam Map<String, String> params) {
        String idPagamento = mercadoPagoService.obterIdPagamento(payload, params);
//        String status = mercadoPagoService.verificarStatusPagamento(idPagamento);
        //TODO obter sale ID a partir do metodo verificarStatusPagamento e atualizar status do pedido
//        Integer saleId = 1;
//        vehicleSaleService.completeSale(saleId, idPagamento);
        log.info("pagamentos webhook - queryParams={}, payload={}, idPagamento={}", params, payload, idPagamento);
        eventPublisher.publishEvent(new StatusPedidoEvent(idPagamento));
        return ResponseEntity.status(201).build();
    }


}