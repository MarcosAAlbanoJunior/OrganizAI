package com.malbano.organizai.usuario.mapper;

import com.malbano.organizai.usuario.dto.PerfilUsuarioRequest;
import com.malbano.organizai.usuario.dto.UsuarioRequest;
import com.malbano.organizai.usuario.entity.PerfilUsuario;
import com.malbano.organizai.usuario.entity.Usuario;

public class UsuarioMapper {
    public static Usuario toEntity(UsuarioRequest usuarioRequest, PerfilUsuario perfilUsuario) {
        Usuario usuario = new Usuario();
        usuario.setPerfilUsuario(perfilUsuario);
        usuario.setNomeUsuario(usuarioRequest.nomeUsuario());
        usuario.setDataNascimento(usuarioRequest.dataNascimento());
        usuario.setEmail(usuarioRequest.email());
        usuario.setSenha(usuarioRequest.senha());
        usuario.setRendaFixa(usuarioRequest.rendaFixa());
        return usuario;
    }
}
