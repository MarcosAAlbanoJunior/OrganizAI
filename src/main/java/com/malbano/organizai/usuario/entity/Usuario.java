package com.malbano.organizai.usuario.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Integer usuarioId;

    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private PerfilUsuario perfilUsuario;

    @Column(name = "nome_usuario", nullable = false, length = 255)
    private String nomeUsuario;

    @Column(name = "data_nasc", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "senha", nullable = false, length = 255)
    private String senha;

    @Column(name = "renda_fixa", precision = 10, scale = 2)
    private BigDecimal rendaFixa;
}
