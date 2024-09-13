package com.malbano.organizai.simulacao.compra.repository;

import com.malbano.organizai.simulacao.compra.entity.SimulacaoCompraParcelas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulacaoCompraParcelasRepository extends JpaRepository<SimulacaoCompraParcelas, Long> {

}
