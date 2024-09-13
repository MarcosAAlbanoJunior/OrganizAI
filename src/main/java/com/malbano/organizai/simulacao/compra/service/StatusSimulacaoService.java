package com.malbano.organizai.simulacao.compra.service;

import com.malbano.organizai.shared.exception.NotFoundException;
import com.malbano.organizai.shared.exception.PerfilAssociadoException;
import com.malbano.organizai.simulacao.compra.dto.StatusSimulacaoRequest;
import com.malbano.organizai.simulacao.compra.entity.StatusSimulacao;
import com.malbano.organizai.simulacao.compra.mapper.StatusSimulacaoMapper;
import com.malbano.organizai.simulacao.compra.repository.StatusSimulacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusSimulacaoService {

    StatusSimulacaoRepository repository;

    @Autowired
    public StatusSimulacaoService(StatusSimulacaoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public StatusSimulacao cadastrarStatusSimulacao(StatusSimulacaoRequest request){
        StatusSimulacao entity = StatusSimulacaoMapper.toEntity(request);
        return repository.save(entity);
    }

    @Transactional
    public StatusSimulacao editarStatusSimulacao(Long id, StatusSimulacaoRequest request){
        buscarStatusSimulacaoPorId(id);
        StatusSimulacao entity = StatusSimulacaoMapper.toEntity(request);
        entity.setStatusSimulacaoId(id);
        return repository.save(entity);
    }

    public List<StatusSimulacao> listarStatusSimulacao(){
        return repository.findAll();
    }

    public StatusSimulacao buscarStatusSimulacaoPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Status de Simulação não encontrado"));
    }

    public void deletarStatusSimulacao(Long id){
        StatusSimulacao statusSimulacao = buscarStatusSimulacaoPorId(id);
        if(repository.isStatusSimulacaoAssociado(statusSimulacao)){
            throw new PerfilAssociadoException(id);
        };
        repository.delete(statusSimulacao);
    }
}
