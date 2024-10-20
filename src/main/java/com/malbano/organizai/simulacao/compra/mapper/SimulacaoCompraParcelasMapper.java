package com.malbano.organizai.simulacao.compra.mapper;

import com.malbano.organizai.simulacao.compra.dto.ListaParcelasCompraDTO;
import com.malbano.organizai.simulacao.compra.dto.SimulacaoCompraParcelasRequest;
import com.malbano.organizai.simulacao.compra.dto.StatusSimulacaoRequest;
import com.malbano.organizai.simulacao.compra.entity.SimulacaoCompraParcelas;
import com.malbano.organizai.simulacao.compra.entity.StatusSimulacao;
import com.malbano.organizai.transacoes.dto.TransacaoVariavelRequest;
import com.malbano.organizai.transacoes.entity.TipoTransacao;
import com.malbano.organizai.transacoes.entity.TransacaoVariavel;
import com.malbano.organizai.usuario.entity.Usuario;

import java.time.LocalDate;

public class SimulacaoCompraParcelasMapper {
    public static SimulacaoCompraParcelas toEntity(SimulacaoCompraParcelasRequest request, Usuario usuario, StatusSimulacao statusSimulacao, TipoTransacao tipoTransacao) {
        SimulacaoCompraParcelas simulacaoCompraParcelas = new SimulacaoCompraParcelas();
        simulacaoCompraParcelas.setUsuario(usuario);
        simulacaoCompraParcelas.setStatusSimulacao(statusSimulacao);
        simulacaoCompraParcelas.setDescricaoCompra(request.descricaoCompra());
        simulacaoCompraParcelas.setValor(request.valor());
        simulacaoCompraParcelas.setQuantidadeParcelas(request.quantidadeParcelas());
        simulacaoCompraParcelas.setDataSimulacao(LocalDate.now());
        simulacaoCompraParcelas.setFinanciamento(request.financiamento());
        simulacaoCompraParcelas.setTipoTransacao(tipoTransacao);

        return simulacaoCompraParcelas;
    }

    public static TransacaoVariavelRequest toTransacaoVariavelRequest(SimulacaoCompraParcelas simulacaoCompraParcelas) {
        return new TransacaoVariavelRequest(
                simulacaoCompraParcelas.getUsuario().getUsuarioId(),
                simulacaoCompraParcelas.getTipoTransacao().getTipoTransacaoId(),
                simulacaoCompraParcelas.getSimulacaoCompraId(),
                simulacaoCompraParcelas.getDescricaoCompra(),
                simulacaoCompraParcelas.getValor(),
                simulacaoCompraParcelas.getDataSimulacao(),
                simulacaoCompraParcelas.getQuantidadeParcelas()
        );
    }
}
