package com.malbano.organizai.transacoes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tipo_transacao")
public class TipoTransacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_transacao_id")
    private Long tipoTransacaoId;

    @Column(name = "descricao_transacao", nullable = false, length = 20)
    private String descricaoTransacao;
}
