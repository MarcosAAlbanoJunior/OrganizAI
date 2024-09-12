package com.malbano.organizai.usuario.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "perfil_usuario")
public class PerfilUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perfil_id", nullable = false)
    private Long perfilId;

    @Column(name = "descricao_perfil", nullable = false)
    private String descricaoPerfil;
}
