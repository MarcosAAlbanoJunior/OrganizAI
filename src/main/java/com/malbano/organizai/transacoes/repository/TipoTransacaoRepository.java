package com.malbano.organizai.transacoes.repository;

import com.malbano.organizai.transacoes.entity.TipoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTransacaoRepository extends JpaRepository<TipoTransacao, Long> {
}
