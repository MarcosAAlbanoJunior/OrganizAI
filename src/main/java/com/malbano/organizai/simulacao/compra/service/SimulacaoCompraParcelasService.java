package com.malbano.organizai.simulacao.compra.service;

import com.malbano.organizai.shared.exception.NotFoundException;
import com.malbano.organizai.shared.exception.PerfilAssociadoException;
import com.malbano.organizai.simulacao.compra.dto.SimulacaoCompraParcelasRequest;
import com.malbano.organizai.simulacao.compra.entity.SimulacaoCompraParcelas;
import com.malbano.organizai.simulacao.compra.entity.StatusSimulacao;
import com.malbano.organizai.simulacao.compra.mapper.SimulacaoCompraParcelasMapper;
import com.malbano.organizai.simulacao.compra.repository.SimulacaoCompraParcelasRepository;
import com.malbano.organizai.usuario.entity.Usuario;
import com.malbano.organizai.usuario.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SimulacaoCompraParcelasService {

    SimulacaoCompraParcelasRepository repository;

    UsuarioService usuarioService;

    StatusSimulacaoService statusSimulacaoService;

    public SimulacaoCompraParcelasService(SimulacaoCompraParcelasRepository repository,
                                          UsuarioService usuarioService,
                                          StatusSimulacaoService statusSimulacaoService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.statusSimulacaoService = statusSimulacaoService;
    }

    @Autowired


    @Transactional
    public SimulacaoCompraParcelas cadastrarSimulacaoCompraParcelas(SimulacaoCompraParcelasRequest request) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(request.usuarioId());
        StatusSimulacao statusSimulacao = statusSimulacaoService.buscarStatusSimulacaoPorId(request.statusSimulacaoId());

        SimulacaoCompraParcelas entity = SimulacaoCompraParcelasMapper.toEntity(request, usuario, statusSimulacao);
        return repository.save(entity);
    }

    @Transactional
    public SimulacaoCompraParcelas editarSimulacaoCompraParcelas(Long id, SimulacaoCompraParcelasRequest request) {
        SimulacaoCompraParcelas simulacaoCompraParcelas = buscarSimulacaoCompraParcelasPorId(id);
        Usuario usuario = simulacaoCompraParcelas.getUsuario();
        StatusSimulacao statusSimulacao = simulacaoCompraParcelas.getStatusSimulacao();

        if(!Objects.equals(simulacaoCompraParcelas.getUsuario().getUsuarioId(), request.usuarioId())){
            usuario = usuarioService.buscarUsuarioPorId(request.usuarioId());
        }

        if (!Objects.equals(simulacaoCompraParcelas.getStatusSimulacao().getStatusSimulacaoId(), request.statusSimulacaoId())){
            statusSimulacao = statusSimulacaoService.buscarStatusSimulacaoPorId(request.statusSimulacaoId());
        }

        SimulacaoCompraParcelas entity = SimulacaoCompraParcelasMapper.toEntity(request, usuario, statusSimulacao);
        entity.setSimulacaoCompraId(id);
        return repository.save(entity);
    }

    public List<SimulacaoCompraParcelas> listarSimulacaoCompraParcelas() {
        return repository.findAll();
    }

    public SimulacaoCompraParcelas buscarSimulacaoCompraParcelasPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Status de Simulação não encontrado"));
    }
}
