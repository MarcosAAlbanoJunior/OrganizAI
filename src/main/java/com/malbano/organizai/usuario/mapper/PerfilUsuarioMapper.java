package com.malbano.organizai.usuario.mapper;

import com.malbano.organizai.usuario.dto.PerfilUsuarioRequest;
import com.malbano.organizai.usuario.entity.PerfilUsuario;

public class PerfilUsuarioMapper {
    public static PerfilUsuario toEntity(PerfilUsuarioRequest request) {
        PerfilUsuario perfilUsuario = new PerfilUsuario();
        perfilUsuario.setDescricaoPerfil(request.descricaoPerfil());
        return perfilUsuario;
    }
}
