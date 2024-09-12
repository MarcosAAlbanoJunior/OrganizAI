package com.malbano.organizai.transacoes.entity;

import com.malbano.organizai.simulacao.compra.entity.SimulacaoCompraParcelas;
import com.malbano.organizai.usuario.entity.Usuario;
import jakarta.persistence.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transacoes_variaveis")
public class TransacoesVariaveis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transacao_id")
    private Long transacaoId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tipo_transacao_id", nullable = false)
    private TipoTransacao tipoTransacao;

    @ManyToOne
    @JoinColumn(name = "simulacao_compra_id")
    private SimulacaoCompraParcelas simulacaoCompra;

    @Column(name = "descricao_transacao", nullable = false, length = 50)
    private String descricaoTransacao;

    @Column(name = "valor_transacao", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTransacao;

    @Column(name = "data_transacao", nullable = false)
    private LocalDate dataTransacao;
}

