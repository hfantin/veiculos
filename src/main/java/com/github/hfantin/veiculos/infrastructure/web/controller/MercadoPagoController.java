package com.github.hfantin.veiculos.infrastructure.web.controller;

import com.github.hfantin.veiculos.domain.service.MercadoPagoService;
import com.github.hfantin.veiculos.domain.service.VehicleSaleService;
import com.github.hfantin.veiculos.infrastructure.web.events.StatusPedidoEvent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
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

    // TODO testes

    @GetMapping("/success")
    public ResponseEntity<?> paymentSuccess(
            @RequestParam("collection_id") String collectionId,
            @RequestParam("collection_status") String collectionStatus,
            @RequestParam("external_reference") String externalReference,
            @RequestParam("payment_type") String paymentType,
            @RequestParam("merchant_order_id") String merchantOrderId,
            @RequestParam("preference_id") String preferenceId) {
//            HttpServletRequest request) {

        log.info("Pagamento APROVADO - Detalhes:");
        log.info("Collection ID: {}", collectionId);
        log.info("Status: {}", collectionStatus);
        log.info("External Reference (ID Pedido): {}", externalReference);
        log.info("Payment Type: {}", paymentType);
        log.info("Merchant Order ID: {}", merchantOrderId);
        log.info("Preference ID: {}", preferenceId);

        vehicleSaleService.completeSale(Integer.valueOf(externalReference), merchantOrderId, paymentType);

        return ResponseEntity.ok(Map.of("msg", "pagamento aprovado com sucesso", "id", externalReference, "orderId", merchantOrderId));

        // Aqui você pode:
        // 1. Atualizar o status do pedido no banco de dados
        // 2. Enviar email de confirmação
        // 3. Registrar o pagamento

//        return ResponseEntity.ok()
//                .contentType(MediaType.TEXT_HTML)
//                .body("""
//                        <html>
//                            <head>
//                                <title>Pagamento Aprovado</title>
//                                <style>
//                                    body { font-family: Arial, sans-serif; text-align: center; padding: 50px; }
//                                    .success { color: #28a745; }
//                                    .button { background-color: #007bff; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; }
//                                </style>
//                            </head>
//                            <body>
//                                <h1 class="success">✅ Pagamento Aprovado!</h1>
//                                <p>Seu pagamento foi processado com sucesso.</p>
//                                <p>Número do pedido: <strong>%s</strong></p>
//                                <br>
//                                <a href="/pedidos/%s" class="button">Ver Meu Pedido</a>
//                                <br><br>
//                                <a href="/">Voltar à Loja</a>
//                            </body>
//                        </html>
//                        """.formatted(externalReference, externalReference));
    }

    @GetMapping("/failure")
    public ResponseEntity<?> paymentFailure(
            @RequestParam(value = "external_reference", required = false) String externalReference,
            @RequestParam(value = "collection_status", required = false) String collectionStatus,
            HttpServletRequest request) {

        log.warn("Pagamento REJEITADO/FALHOU");
        log.warn("External Reference: {}", externalReference);
        log.warn("Status: {}", collectionStatus);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body("""
                        <html>
                            <head>
                                <title>Pagamento Não Aprovado</title>
                                <style>
                                    body { font-family: Arial, sans-serif; text-align: center; padding: 50px; }
                                    .error { color: #dc3545; }
                                    .button { background-color: #007bff; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; }
                                </style>
                            </head>
                            <body>
                                <h1 class="error">❌ Pagamento Não Aprovado</h1>
                                <p>Houve um problema com seu pagamento. Tente novamente.</p>
                                <br>
                                <a href="/carrinho" class="button">Tentar Novamente</a>
                                <br><br>
                                <a href="/">Voltar à Loja</a>
                            </body>
                        </html>
                        """);
    }

    @GetMapping("/pending")
    public ResponseEntity<?> paymentPending(
            @RequestParam("external_reference") String externalReference,
            @RequestParam("collection_status") String collectionStatus,
            @RequestParam("preference_id") String preferenceId) {

        log.info("Pagamento PENDENTE");
        log.info("External Reference: {}", externalReference);
        log.info("Status: {}", collectionStatus);
        log.info("Preference ID: {}", preferenceId);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body("""
                        <html>
                            <head>
                                <title>Pagamento Pendente</title>
                                <style>
                                    body { font-family: Arial, sans-serif; text-align: center; padding: 50px; }
                                    .warning { color: #ffc107; }
                                    .button { background-color: #007bff; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; }
                                </style>
                            </head>
                            <body>
                                <h1 class="warning">⏳ Pagamento Pendente</h1>
                                <p>Seu pagamento está sendo processado. Você receberá uma confirmação em breve.</p>
                                <p>Número do pedido: <strong>%s</strong></p>
                                <br>
                                <a href="/pedidos/%s" class="button">Acompanhar Pedido</a>
                                <br><br>
                                <a href="/">Voltar à Loja</a>
                            </body>
                        </html>
                        """.formatted(externalReference, externalReference));
    }


}