package com.malbano.organizai.usuario.repository;

import com.malbano.organizai.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u.statusUsuario FROM Usuario u WHERE u.usuarioId = :id")
    String buscarStatusUsuario(Long id);
}
