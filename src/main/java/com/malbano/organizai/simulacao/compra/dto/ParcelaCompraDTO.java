package com.malbano.organizai.simulacao.compra.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ParcelaCompraDTO{
    private BigDecimal valor;
    private LocalDate dataParcela;
}
