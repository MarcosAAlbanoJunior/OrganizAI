package com.malbano.organizai.usuario.service;

import com.malbano.organizai.shared.exception.NotFoundException;
import com.malbano.organizai.shared.exception.PerfilAssociadoException;
import com.malbano.organizai.usuario.dto.PerfilUsuarioRequest;
import com.malbano.organizai.usuario.dto.UsuarioRequest;
import com.malbano.organizai.usuario.entity.PerfilUsuario;
import com.malbano.organizai.usuario.entity.Usuario;
import com.malbano.organizai.usuario.mapper.UsuarioMapper;
import com.malbano.organizai.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    UsuarioRepository repository;

    PerfilUsuarioService perfilUsuarioService;

    @Autowired
    public UsuarioService(UsuarioRepository repository,  PerfilUsuarioService perfilUsuarioService) {
        this.repository = repository;
        this.perfilUsuarioService = perfilUsuarioService;
    }

    @Transactional
    public Usuario cadastrarUsuario(UsuarioRequest request){
        PerfilUsuario perfilUsuario = perfilUsuarioService.buscarPerfilUsuarioPorId(request.perfilId());
        Usuario entity = UsuarioMapper.toEntity(request, perfilUsuario);
        entity.setStatusUsuario("ATIVO");
        return repository.save(entity);
    }

    @Transactional
    public Usuario editarUsuario(Long id, UsuarioRequest request){
        String statusUsuario = statusUsuario(id);
        PerfilUsuario perfilUsuario = perfilUsuarioService.buscarPerfilUsuarioPorId(request.perfilId());
        Usuario entity = UsuarioMapper.toEntity(request, perfilUsuario);
        entity.setUsuarioId(id);
        entity.setStatusUsuario(statusUsuario);
        return repository.save(entity);
    }

    public List<Usuario> listarUsuarios(){
        return repository.findAll();
    }

    public Usuario buscarUsuarioPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Usuario não encontrado"));
    }

    @Transactional
    public void desativarUsuario(Long id){
        Usuario usuario = buscarUsuarioPorId(id);
        usuario.setStatusUsuario("DESATIVADO");
        repository.save(usuario);
    }

    @Transactional
    public void ativarUsuario(Long id){
        Usuario usuario = buscarUsuarioPorId(id);
        usuario.setStatusUsuario("ATIVADO");
        repository.save(usuario);
    }

    public String statusUsuario(Long id){
        return repository.buscarStatusUsuario(id);
    }
}
