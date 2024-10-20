package com.malbano.organizai.transacoes.service;

import com.malbano.organizai.shared.exception.NotFoundException;
import com.malbano.organizai.simulacao.compra.entity.SimulacaoCompraParcelas;
import com.malbano.organizai.simulacao.compra.service.SimulacaoCompraParcelasService;
import com.malbano.organizai.transacoes.dto.TransacaoVariavelRequest;
import com.malbano.organizai.transacoes.entity.TipoTransacao;
import com.malbano.organizai.transacoes.entity.TransacaoVariavel;
import com.malbano.organizai.transacoes.mapper.TransacaoVariavelMapper;
import com.malbano.organizai.transacoes.repository.TransacaoVariavelRepository;
import com.malbano.organizai.usuario.entity.Usuario;
import com.malbano.organizai.usuario.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
public class TransacaoVariavelService {

    private final TransacaoVariavelRepository repository;
    private final UsuarioService usuarioService;
    private final SimulacaoCompraParcelasService simulacaoService;
    private final TipoTransacaoService tipoTransacaoService;

    public TransacaoVariavelService(TipoTransacaoService tipoTransacaoService,
                                    SimulacaoCompraParcelasService simulacaoService,
                                    UsuarioService usuarioService,
                                    TransacaoVariavelRepository repository) {
        this.tipoTransacaoService = tipoTransacaoService;
        this.simulacaoService = simulacaoService;
        this.usuarioService = usuarioService;
        this.repository = repository;
    }

    @Transactional
    public TransacaoVariavel salvarTransacaoVariavel(TransacaoVariavelRequest request) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(request.usuario());
        TipoTransacao tipoTransacao = tipoTransacaoService.buscarTipoTransacaoPorId(request.tipoTransacao());
        SimulacaoCompraParcelas simulacaoCompraParcelas = buscarSimulacaoSeExistir(request.simulacaoCompraParcelas());

        return repository.save(TransacaoVariavelMapper.toEntity(request, simulacaoCompraParcelas, usuario, tipoTransacao));
    }

    @Transactional
    public void salvarTransacaoParcelada(TransacaoVariavelRequest request) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(request.usuario());
        TipoTransacao tipoTransacao = tipoTransacaoService.buscarTipoTransacaoPorId(request.tipoTransacao());
        SimulacaoCompraParcelas simulacaoCompraParcelas = buscarSimulacaoSeExistir(request.simulacaoCompraParcelas());

        BigDecimal valorParcelas = calcularValorParcela(request.valorTransacao(), request.parcelas());
        String grupoTransacao = UUID.randomUUID().toString();

        IntStream.range(0, request.parcelas()).forEach(parcela -> {
            TransacaoVariavel transacaoVariavel = new TransacaoVariavel();
            transacaoVariavel.setUsuario(usuario);
            transacaoVariavel.setDataTransacao(LocalDate.now().plusMonths(parcela));
            transacaoVariavel.setSimulacaoCompra(simulacaoCompraParcelas);
            transacaoVariavel.setValorTransacao(valorParcelas);
            transacaoVariavel.setDescricaoTransacao(request.descricaoTransacao());
            transacaoVariavel.setTipoTransacao(tipoTransacao);
            transacaoVariavel.setGrupoTransacao(grupoTransacao);

            salvarTransacaoVariavel(TransacaoVariavelMapper.toRequest(transacaoVariavel, request.parcelas()));
        });
    }

    public BigDecimal calcularValorParcela(BigDecimal valorTotal, int quantidadeParcelas) {
        return valorTotal.divide(BigDecimal.valueOf(quantidadeParcelas), 2, RoundingMode.HALF_UP);
    }

    @Transactional
    public TransacaoVariavel editarTransacaoVariavel(Long id, TransacaoVariavelRequest request) {
        buscarTransacaoVariavelPorId(id);
        return salvarTransacaoVariavel(request);
    }

    @Transactional
    public TransacaoVariavel deletarTransacaoVariavelSimulacao(Long id) {
        return repository.save(buscarTransacaoVariavelPorId(id));
    }

    public TransacaoVariavel buscarTransacaoVariavelPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Transacao não encontrada"));
    }

    public List<TransacaoVariavel> listarTransacaoVariavel() {
        return repository.findAll();
    }

    private SimulacaoCompraParcelas buscarSimulacaoSeExistir(Long simulacaoId) {
        return simulacaoId != null ? simulacaoService.buscarSimulacaoCompraParcelasPorId(simulacaoId) : null;
    }
}
