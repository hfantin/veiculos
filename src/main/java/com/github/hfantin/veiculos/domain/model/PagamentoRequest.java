package com.github.hfantin.veiculos.domain.model;

import java.math.BigDecimal;

public record PagamentoRequest(String descricao,
                               BigDecimal preco,
                               Integer quantidade,
                               String emailComprador,
                               String idPedidoVenda) {
}
