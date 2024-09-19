package com.malbano.organizai.simulacao.compra.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListaParcelasCompraDTO {
    private Long usuarioId;
    private Long simulacaoCompraId;
    private String statusSimulacao;
    private BigDecimal valor;
    private List<ParcelaCompraDTO> parcelas;
}
