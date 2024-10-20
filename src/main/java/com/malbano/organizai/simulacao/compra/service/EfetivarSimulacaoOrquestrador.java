package com.malbano.organizai.simulacao.compra.service;

import com.malbano.organizai.simulacao.compra.entity.SimulacaoCompraParcelas;
import com.malbano.organizai.simulacao.compra.mapper.SimulacaoCompraParcelasMapper;
import com.malbano.organizai.simulacao.compra.repository.SimulacaoCompraParcelasRepository;
import com.malbano.organizai.transacoes.dto.TransacaoVariavelRequest;
import com.malbano.organizai.transacoes.service.TipoTransacaoService;
import com.malbano.organizai.transacoes.service.TransacaoVariavelService;
import com.malbano.organizai.usuario.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EfetivarSimulacaoOrquestrador {


    TransacaoVariavelService transacaoVariavelService;
    SimulacaoCompraParcelasService simulacaoCompraParcelasService;

    public EfetivarSimulacaoOrquestrador(TransacaoVariavelService transacaoVariavelService, SimulacaoCompraParcelasService simulacaoCompraParcelasService) {
        this.transacaoVariavelService = transacaoVariavelService;
        this.simulacaoCompraParcelasService = simulacaoCompraParcelasService;
    }

    @Transactional
    public void efetivarCompra(Long idSimulacaoParcelasCompra){
        SimulacaoCompraParcelas simulacaoCompraParcelas = simulacaoCompraParcelasService.buscarSimulacaoCompraParcelasPorId(idSimulacaoParcelasCompra);
        transacaoVariavelService.salvarTransacaoParcelada(SimulacaoCompraParcelasMapper.toTransacaoVariavelRequest(simulacaoCompraParcelas));
        simulacaoCompraParcelasService.alterarStatusSimulacao(idSimulacaoParcelasCompra);
    }
}
