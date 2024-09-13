package com.malbano.organizai.simulacao.compra.repository;

import com.malbano.organizai.simulacao.compra.entity.StatusSimulacao;
import com.malbano.organizai.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusSimulacaoRepository extends JpaRepository<StatusSimulacao, Long> {

    @Query("SELECT CASE WHEN COUNT(scp) > 0 THEN true ELSE false END FROM SimulacaoCompraParcelas scp WHERE scp.statusSimulacao = :statusSimulacao")
    boolean isStatusSimulacaoAssociado(StatusSimulacao statusSimulacao);
}
