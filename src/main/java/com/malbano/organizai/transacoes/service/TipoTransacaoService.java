package com.malbano.organizai.transacoes.service;

import com.malbano.organizai.shared.exception.NotFoundException;
import com.malbano.organizai.transacoes.entity.TipoTransacao;
import com.malbano.organizai.transacoes.entity.TransacaoVariavel;
import com.malbano.organizai.transacoes.repository.TipoTransacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoTransacaoService {

    TipoTransacaoRepository repository;

    public TipoTransacaoService(TipoTransacaoRepository repository) {
        this.repository = repository;
    }

    public TipoTransacao buscarTipoTransacaoPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Tipo Transacao não encontrada"));
    }
}
