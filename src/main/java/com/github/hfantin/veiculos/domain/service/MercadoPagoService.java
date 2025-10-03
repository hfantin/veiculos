package com.github.hfantin.veiculos.domain.service;

import com.github.hfantin.veiculos.domain.model.PagamentoRequest;
import com.github.hfantin.veiculos.domain.model.PagamentoResponse;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;

import java.util.List;
import java.util.Map;

public interface MercadoPagoService {
    PagamentoResponse criarPagamento(PagamentoRequest request) throws Exception;
    String verificarStatusPagamento(String paymentId);
    String obterIdPagamento(Map<String, Object> payload, Map<String, String> params);
}
