package com.malbano.organizai.transacoes.service;

import com.malbano.organizai.shared.exception.NotFoundException;
import com.malbano.organizai.transacoes.dto.TipoTransacaoRequest;
import com.malbano.organizai.transacoes.entity.TipoTransacao;
import com.malbano.organizai.transacoes.entity.TransacaoVariavel;
import com.malbano.organizai.transacoes.mapper.TipoTransacaoMapper;
import com.malbano.organizai.transacoes.repository.TipoTransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoTransacaoService {

    TipoTransacaoRepository repository;

    public TipoTransacaoService(TipoTransacaoRepository repository) {
        this.repository = repository;
    }

    public TipoTransacao buscarTipoTransacaoPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Tipo Transacao não encontrada"));
    }

    public List<TipoTransacao>listarTipoTransacao(){
        return repository.findAll();
    }

    @Transactional
    public TipoTransacao criarTipoTransacao(TipoTransacaoRequest request){
        return repository.save(TipoTransacaoMapper.toEntity(request));
    }

    @Transactional
    public TipoTransacao editarTipoTransacao(Long id, TipoTransacaoRequest request){
        TipoTransacao tipoTransacao = buscarTipoTransacaoPorId(id);
        tipoTransacao.setDescricaoTransacao(request.descricaoTransacao());
        return repository.save(tipoTransacao);
    }

    @Transactional
    public void deletarTipoTransacao(Long id){
        repository.delete(buscarTipoTransacaoPorId(id));
    }
}
