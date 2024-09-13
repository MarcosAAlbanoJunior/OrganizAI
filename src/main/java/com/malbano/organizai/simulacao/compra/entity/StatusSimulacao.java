package com.malbano.organizai.simulacao.compra.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "status_simulacao")
@Getter
@Setter
public class StatusSimulacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_simulacao_id")
    private Long statusSimulacaoId;

    @Column(name = "descricao_status", nullable = false, length = 50)
    private String descricaoStatus;
}
