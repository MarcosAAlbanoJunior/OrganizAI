package com.malbano.organizai.simulacao.financiamento.entity;

import com.malbano.organizai.simulacao.compra.entity.SimulacaoCompraParcelas;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "simulacao_financiamento")
public class SimulacaoFinanciamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "simulacao_financiamento_id")
    private Integer simulacaoFinanciamentoId;

    @OneToOne
    @JoinColumn(name = "simulacao_compra_id", nullable = false)
    private SimulacaoCompraParcelas simulacaoCompra;

    @Column(name = "taxa_juros", nullable = false, precision = 10, scale = 2)
    private BigDecimal taxaJuros;
}
