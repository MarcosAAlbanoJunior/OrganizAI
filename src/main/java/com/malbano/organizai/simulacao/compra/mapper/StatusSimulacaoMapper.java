package com.malbano.organizai.simulacao.compra.mapper;

import com.malbano.organizai.simulacao.compra.dto.StatusSimulacaoRequest;
import com.malbano.organizai.simulacao.compra.entity.StatusSimulacao;
import com.malbano.organizai.usuario.dto.PerfilUsuarioRequest;
import com.malbano.organizai.usuario.entity.PerfilUsuario;

public class StatusSimulacaoMapper {
    public static StatusSimulacao toEntity(StatusSimulacaoRequest request) {
        StatusSimulacao statusSimulacao = new StatusSimulacao();
        statusSimulacao.setDescricaoStatus(request.descricaoStatus());
        return statusSimulacao;
    }
}
