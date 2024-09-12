package com.malbano.organizai.usuario.repository;

import com.malbano.organizai.usuario.entity.PerfilUsuario;
import com.malbano.organizai.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Long> {


    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Usuario u WHERE u.perfilUsuario = :perfilUsuario")
    boolean isPerfilUsuarioAssociado(PerfilUsuario perfilUsuario);
}
