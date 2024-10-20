package com.malbano.organizai.transacoes.dto;

import com.malbano.organizai.simulacao.compra.entity.SimulacaoCompraParcelas;
import com.malbano.organizai.transacoes.entity.TipoTransacao;
import com.malbano.organizai.usuario.entity.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransacaoVariavelRequest(
        Long usuario,
        Long tipoTransacao,
        Long simulacaoCompraParcelas,
        String descricaoTransacao,
        BigDecimal valorTransacao,
        LocalDate dataTransacao,
        int parcelas) {
}
