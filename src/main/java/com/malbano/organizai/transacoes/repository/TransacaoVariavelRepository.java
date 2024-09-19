package com.malbano.organizai.transacoes.repository;

import com.malbano.organizai.transacoes.entity.TipoTransacao;
import com.malbano.organizai.transacoes.entity.TransacaoVariavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoVariavelRepository extends JpaRepository<TransacaoVariavel, Long> {
}
