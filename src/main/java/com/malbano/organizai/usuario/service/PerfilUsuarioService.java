package com.malbano.organizai.usuario.service;

import com.malbano.organizai.shared.exception.NotFoundException;
import com.malbano.organizai.shared.exception.PerfilAssociadoException;
import com.malbano.organizai.usuario.dto.PerfilUsuarioRequest;
import com.malbano.organizai.usuario.entity.PerfilUsuario;
import com.malbano.organizai.usuario.mapper.PerfilUsuarioMapper;
import com.malbano.organizai.usuario.repository.PerfilUsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilUsuarioService {

    PerfilUsuarioRepository repository;

    @Autowired
    public PerfilUsuarioService(PerfilUsuarioRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public PerfilUsuario cadastrarPerfilUsuario(PerfilUsuarioRequest request){
        PerfilUsuario entity = PerfilUsuarioMapper.toEntity(request);
        return repository.save(entity);
    }

    @Transactional
    public PerfilUsuario editarPerfilUsuario(Long id, PerfilUsuarioRequest request){
        buscarPerfilUsuarioPorId(id);

        PerfilUsuario entity = PerfilUsuarioMapper.toEntity(request);
        entity.setPerfilId(id);
        return repository.save(entity);
    }

    public List<PerfilUsuario> listarPerfisUsuario(){
        return repository.findAll();
    }

    public PerfilUsuario buscarPerfilUsuarioPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Perfil de Usuario não encontrado"));
    }

    public void deletarPerfilUsuario(Long id){
        PerfilUsuario perfilUsuario = buscarPerfilUsuarioPorId(id);
        if(repository.isPerfilUsuarioAssociado(perfilUsuario)){
            throw new PerfilAssociadoException(id);
        };
        repository.delete(perfilUsuario);
    }
}
