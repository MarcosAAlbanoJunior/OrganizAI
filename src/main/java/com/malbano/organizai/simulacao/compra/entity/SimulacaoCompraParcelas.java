package com.malbano.organizai.simulacao.compra.entity;

import com.malbano.organizai.usuario.entity.Usuario;
import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "simulacao_compra_parcelas")
@Setter
@Getter
public class SimulacaoCompraParcelas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "simulacao_compra_id")
    private Integer simulacaoCompraId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "status_simulacao_id", nullable = false)
    private StatusSimulacao statusSimulacao;

    @Column(name = "descricao_compra", nullable = false, length = 255)
    private String descricaoCompra;

    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "quantidade_parcelas", nullable = false)
    private Integer quantidadeParcelas;

    @Column(name = "valor_parcela", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorParcela;

    @Column(name = "data_simulacao", nullable = false)
    private LocalDate dataSimulacao;

    @Column(name = "financiamento", nullable = false)
    private Boolean financiamento = false;
}

