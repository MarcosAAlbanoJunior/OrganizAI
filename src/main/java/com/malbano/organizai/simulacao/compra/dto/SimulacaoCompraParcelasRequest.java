package com.malbano.organizai.simulacao.compra.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SimulacaoCompraParcelasRequest(
        Long usuarioId,
        Long statusSimulacaoId,
        String descricaoCompra,
        BigDecimal valor,
        Integer quantidadeParcelas,
        Boolean financiamento
) {}
