package com.malbano.organizai.transacoes.mapper;

import com.malbano.organizai.transacoes.dto.TipoTransacaoRequest;
import com.malbano.organizai.transacoes.entity.TipoTransacao;

public class TipoTransacaoMapper {

    public static TipoTransacao toEntity(TipoTransacaoRequest request){
        TipoTransacao tipoTransacao = new TipoTransacao();
        tipoTransacao.setDescricaoTransacao(request.descricaoTransacao());

        return tipoTransacao;
    }
}
