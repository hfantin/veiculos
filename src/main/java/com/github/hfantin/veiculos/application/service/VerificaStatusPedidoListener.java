package com.github.hfantin.veiculos.application.service;

import com.github.hfantin.veiculos.domain.service.MercadoPagoService;
import com.github.hfantin.veiculos.infrastructure.web.events.StatusPedidoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VerificaStatusPedidoListener {

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @EventListener
    public void handleVerificaPagmento(StatusPedidoEvent event) {
        log.info("verifica pagamento do pedido {}", event.id());
        String status = mercadoPagoService.verificarStatusPagamento(event.id());
        log.info("status {}", status);

    }
}
