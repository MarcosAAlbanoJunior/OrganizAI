package com.malbano.organizai.transacoes.mapper;

import com.malbano.organizai.simulacao.compra.entity.SimulacaoCompraParcelas;
import com.malbano.organizai.transacoes.dto.TipoTransacaoRequest;
import com.malbano.organizai.transacoes.dto.TransacaoVariavelRequest;
import com.malbano.organizai.transacoes.entity.TipoTransacao;
import com.malbano.organizai.transacoes.entity.TransacaoVariavel;
import com.malbano.organizai.usuario.entity.Usuario;

public class TransacaoVariavelMapper {

    public static TransacaoVariavel toEntity(TransacaoVariavelRequest request,
                                             SimulacaoCompraParcelas simulacaoCompraParcelas,
                                             Usuario usuario, TipoTransacao tipoTransacao){
        TransacaoVariavel transacaoVariavel = new TransacaoVariavel();
        transacaoVariavel.setUsuario(usuario);
        transacaoVariavel.setTipoTransacao(tipoTransacao);
        transacaoVariavel.setDescricaoTransacao(request.descricaoTransacao());
        transacaoVariavel.setValorTransacao(request.valorTransacao());
        transacaoVariavel.setDataTransacao(request.dataTransacao());
        if(request.simulacaoCompraParcelas() != null){
            transacaoVariavel.setSimulacaoCompra(simulacaoCompraParcelas);
        }

        return transacaoVariavel;
    }

    public static TransacaoVariavelRequest toRequest(TransacaoVariavel entity, int parcelas){
        return new TransacaoVariavelRequest(
                entity.getUsuario().getUsuarioId(),
                entity.getTipoTransacao().getTipoTransacaoId(),
                entity.getSimulacaoCompra().getSimulacaoCompraId(),
                entity.getDescricaoTransacao(),
                entity.getValorTransacao(),
                entity.getDataTransacao(),
                parcelas
        );
    }

    public static  TransacaoVariavelRequest simulacaoToRequest(SimulacaoCompraParcelas simulacaoCompraParcelas){
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
