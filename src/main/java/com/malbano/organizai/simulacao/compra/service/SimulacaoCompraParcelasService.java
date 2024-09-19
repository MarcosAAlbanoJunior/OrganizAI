package com.malbano.organizai.simulacao.compra.service;

import com.malbano.organizai.shared.exception.NotFoundException;
import com.malbano.organizai.shared.exception.PerfilAssociadoException;
import com.malbano.organizai.simulacao.compra.dto.ListaParcelasCompraDTO;
import com.malbano.organizai.simulacao.compra.dto.ParcelaCompraDTO;
import com.malbano.organizai.simulacao.compra.dto.SimulacaoCompraParcelasRequest;
import com.malbano.organizai.simulacao.compra.entity.SimulacaoCompraParcelas;
import com.malbano.organizai.simulacao.compra.entity.StatusSimulacao;
import com.malbano.organizai.simulacao.compra.mapper.SimulacaoCompraParcelasMapper;
import com.malbano.organizai.simulacao.compra.repository.SimulacaoCompraParcelasRepository;
import com.malbano.organizai.transacoes.entity.TipoTransacao;
import com.malbano.organizai.transacoes.entity.TransacaoVariavel;
import com.malbano.organizai.transacoes.service.TipoTransacaoService;
import com.malbano.organizai.transacoes.service.TransacaoVariavelService;
import com.malbano.organizai.usuario.entity.Usuario;
import com.malbano.organizai.usuario.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Service
public class SimulacaoCompraParcelasService {

    SimulacaoCompraParcelasRepository repository;

    UsuarioService usuarioService;

    StatusSimulacaoService statusSimulacaoService;

    TipoTransacaoService tipoTransacaoService;

    TransacaoVariavelService transacaoVariavelService;

    public SimulacaoCompraParcelasService(SimulacaoCompraParcelasRepository repository, UsuarioService usuarioService,
                                          StatusSimulacaoService statusSimulacaoService, TipoTransacaoService tipoTransacaoService,
                                          TransacaoVariavelService transacaoVariavelService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.statusSimulacaoService = statusSimulacaoService;
        this.tipoTransacaoService = tipoTransacaoService;
        this.transacaoVariavelService = transacaoVariavelService;
    }

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

    @Transactional
    public void efetivarCompra(Long idSimulacaoParcelasCompra){
        ListaParcelasCompraDTO listaParcelasCompraDTO = listarParcelasCompra(idSimulacaoParcelasCompra);
        criarTransacoesSimulacao(listaParcelasCompraDTO);
        repository.mudarStatusSimulacao(statusSimulacaoService.buscarStatusSimulacaoPorId(1L), idSimulacaoParcelasCompra);
    }

    public ListaParcelasCompraDTO listarParcelasCompra(Long idSimulacaoParcelasCompra){
        ListaParcelasCompraDTO listaParcelasCompraDTO = new ListaParcelasCompraDTO();

        SimulacaoCompraParcelas simulacaoCompraParcelas = buscarSimulacaoCompraParcelasPorId(idSimulacaoParcelasCompra);

        int numeroParcelas = simulacaoCompraParcelas.getQuantidadeParcelas();

        List<ParcelaCompraDTO> listaParcelas = new ArrayList<>();

        BigDecimal valorParcelas = calcularValorParcela(simulacaoCompraParcelas.getValor(), simulacaoCompraParcelas.getQuantidadeParcelas());

        IntStream.range(0, numeroParcelas).forEach(mesParcela -> {
            ParcelaCompraDTO parcelaDTO = new ParcelaCompraDTO();
            parcelaDTO.setDataParcela(LocalDate.now().plusMonths(mesParcela));
            parcelaDTO.setValor(valorParcelas);
            listaParcelas.add(parcelaDTO);
        });

        listaParcelasCompraDTO.setSimulacaoCompraId(simulacaoCompraParcelas.getSimulacaoCompraId());
        listaParcelasCompraDTO.setUsuarioId(simulacaoCompraParcelas.getUsuario().getUsuarioId());
        listaParcelasCompraDTO.setStatusSimulacao(simulacaoCompraParcelas.getStatusSimulacao().getDescricaoStatus());
        listaParcelasCompraDTO.setValor(simulacaoCompraParcelas.getValor());
        listaParcelasCompraDTO.setParcelas(listaParcelas);

        return listaParcelasCompraDTO;
    }

    public BigDecimal calcularValorParcela(BigDecimal valorTotal, int quantidadeParcelas){
        return valorTotal.divide(BigDecimal.valueOf(quantidadeParcelas),  2, RoundingMode.HALF_UP);
    }

    @Transactional
    public void criarTransacoesSimulacao(ListaParcelasCompraDTO listaParcelasCompra){
        Usuario usuario = usuarioService.buscarUsuarioPorId(listaParcelasCompra.getUsuarioId());
        SimulacaoCompraParcelas simulacaoCompraParcelas = buscarSimulacaoCompraParcelasPorId(listaParcelasCompra.getSimulacaoCompraId());
        listaParcelasCompra.getParcelas().forEach(parcela ->{
            TransacaoVariavel transacaoVariavel = new TransacaoVariavel();
            transacaoVariavel.setUsuario(usuario);
            transacaoVariavel.setDataTransacao(parcela.getDataParcela());
            transacaoVariavel.setSimulacaoCompra(simulacaoCompraParcelas);
            transacaoVariavel.setValorTransacao(parcela.getValor());
            transacaoVariavel.setDescricaoTransacao(simulacaoCompraParcelas.getDescricaoCompra());
            transacaoVariavel.setTipoTransacao(tipoTransacaoService.buscarTipoTransacaoPorId(1L));

            transacaoVariavelService.salvarTransacaoVariavelSimulacao(transacaoVariavel);

        });
    }
}
