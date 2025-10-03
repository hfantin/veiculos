package com.github.hfantin.veiculos.domain.model;

import java.math.BigDecimal;

public record PagamentoResponse(
        String linkPagamento,
        String idPreferencia,
        String status
) {
}
