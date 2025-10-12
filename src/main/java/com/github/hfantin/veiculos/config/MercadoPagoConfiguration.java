package com.github.hfantin.veiculos.config;

import com.mercadopago.MercadoPagoConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Slf4j
@Configuration
public class MercadoPagoConfiguration {

    @Value("${mercadopago.access-token}")
    private String accessToken;

    @PostConstruct
    public void configurar() {
        log.info("configura token do mercado pago");
        MercadoPagoConfig.setAccessToken(accessToken);
    }

}