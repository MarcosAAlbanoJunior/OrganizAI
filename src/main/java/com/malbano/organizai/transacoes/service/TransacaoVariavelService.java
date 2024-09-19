package com.malbano.organizai.transacoes.service;

import com.malbano.organizai.shared.exception.NotFoundException;
import com.malbano.organizai.transacoes.entity.TransacaoVariavel;
import com.malbano.organizai.transacoes.repository.TransacaoVariavelRepository;
import org.springframework.stereotype.Service;

@Service
public class TransacaoVariavelService {

    TransacaoVariavelRepository repository;

    public TransacaoVariavelService(TransacaoVariavelRepository repository) {
        this.repository = repository;
    }

    public TransacaoVariavel salvarTransacaoVariavelSimulacao(TransacaoVariavel transacaoVariavel){
        return repository.save(transacaoVariavel);
    }

    public TransacaoVariavel buscarTransacaoVariavelPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Transacao não encontrada"));
    }
}
