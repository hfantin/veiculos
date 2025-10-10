package com.github.hfantin.veiculos.application.service;

import com.github.hfantin.veiculos.config.AppConfig;
import com.github.hfantin.veiculos.domain.model.PagamentoRequest;
import com.github.hfantin.veiculos.domain.model.PagamentoResponse;
import com.github.hfantin.veiculos.domain.service.MercadoPagoService;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MercadoPagoServiceImpl implements MercadoPagoService {

    @Autowired
    private AppConfig appConfig;

    @Override
    public PagamentoResponse criarPagamento(PagamentoRequest request) throws Exception {
        PreferenceClient client = new PreferenceClient();

        // Criar o item da preferência
        PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                .title(request.descricao())
                .quantity(request.quantidade())
                .unitPrice(request.preco())
                .build();
        List<PreferenceItemRequest> items = List.of(itemRequest);
        // Configurar o pagador (opcional para testes)
        PreferencePayerRequest payer = null;
        if (request.emailComprador() != null) {
            payer = PreferencePayerRequest.builder()
                    .email(request.emailComprador())
                    .build();
        }

        // Criar a requisição da preferência
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .payer(payer)
                .externalReference(request.idPedidoVenda())
                .backUrls(PreferenceBackUrlsRequest.builder()
                        .success(String.format("%s%s", appConfig.getBaseUrl(), "/success"))
                        .failure(String.format("%s%s", appConfig.getBaseUrl(), "/failure"))
                        .pending(String.format("%s%s", appConfig.getBaseUrl(), "/pending"))
                        .build())
                .autoReturn("approved")
                .notificationUrl(appConfig.getBaseUrl() + "/webhook")
                .build();

        // Criar a preferência
        Preference preference = client.create(preferenceRequest);

        return new PagamentoResponse(
                preference.getSandboxInitPoint(),
                preference.getId(),
                "created"
        );
    }

    @Override
    public String verificarStatusPagamento(String idPagamento) {
        if (idPagamento != null) {
            try {
                PaymentClient paymentClient = new PaymentClient();
                Payment payment = paymentClient.get(Long.parseLong(idPagamento));
                log.info("💳 Pagamento ID: {}", payment.getId());
                log.info("💰 Valor: {}", payment.getTransactionAmount());
                log.info("📧 Comprador: {}", payment.getPayer().getEmail());
                log.info("📦 Status: {}", payment.getStatus());
                log.info("📝 Status detail: {}", payment.getStatusDetail());
                log.info("idPedidoVenda: {}", payment.getExternalReference());

                return payment.getStatus();

            } catch (MPApiException e) {
                log.error("Erro da API do Mercado Pago: {} - {}", e.getStatusCode(), e.getApiResponse().getContent(), e);
                // Se a exceção indicar 404 Not Found, o ID pode estar errado.
                // Se indicar 401 Unauthorized, o Access Token está incorreto.
            } catch (MPException e) {
                log.error("Erro do Mercado Pago: {}", e.getMessage(), e);
            } catch (Exception e) {
                log.error("falha ao obter informações do pagamento: {}", e.getMessage(), e);
            }
        }
        return null;
    }

    @Override
    public String obterIdPagamento(Map<String, Object> payload, Map<String, String> params) {
        String pagamentoId = null;
        if (params.containsKey("data.id")) {
            pagamentoId = params.get("data.id");
        } else if (payload.containsKey("data")) {
            Map<String, Object> data = (Map<String, Object>) payload.get("data");
            pagamentoId = String.valueOf(data.get("id"));
        }
        return pagamentoId;
    }

}
