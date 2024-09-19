package com.malbano.organizai.simulacao.compra.repository;

import com.malbano.organizai.simulacao.compra.entity.SimulacaoCompraParcelas;
import com.malbano.organizai.simulacao.compra.entity.StatusSimulacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulacaoCompraParcelasRepository extends JpaRepository<SimulacaoCompraParcelas, Long> {

    @Modifying
    @Query("UPDATE SimulacaoCompraParcelas scp SET scp.statusSimulacao = :statusSimulacao WHERE scp.simulacaoCompraId = :simulacaoCompraId")
    void mudarStatusSimulacao(StatusSimulacao statusSimulacao, Long simulacaoCompraId);
}
