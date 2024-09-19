package com.malbano.organizai.simulacao.compra.controller;

import com.malbano.organizai.shared.util.UriLocationUtil;
import com.malbano.organizai.simulacao.compra.dto.ListaParcelasCompraDTO;
import com.malbano.organizai.simulacao.compra.dto.SimulacaoCompraParcelasRequest;
import com.malbano.organizai.simulacao.compra.entity.SimulacaoCompraParcelas;
import com.malbano.organizai.simulacao.compra.service.SimulacaoCompraParcelasService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simulacao-compra")
public class SimulacaoCompraParcelasController {

    SimulacaoCompraParcelasService service;

    @Autowired
    public SimulacaoCompraParcelasController(SimulacaoCompraParcelasService service) {
        this.service = service;
    }

    @Operation(summary = "Procurar Simulacao Compra Parcelas por Id")
    @GetMapping("/{id}")
    public ResponseEntity<SimulacaoCompraParcelas> buscarPorId(@PathVariable("id") Long id) {
        SimulacaoCompraParcelas statusSimulacao = service.buscarSimulacaoCompraParcelasPorId(id);
        return ResponseEntity.ok(statusSimulacao);
    }

    @Operation(summary = "Listar Simulacoes compras Parcelas")
    @GetMapping
    public ResponseEntity<List<SimulacaoCompraParcelas>> listar() {
        List<SimulacaoCompraParcelas> statusSimulacao = service.listarSimulacaoCompraParcelas();
        return ResponseEntity.ok(statusSimulacao);
    }

    @Operation(summary = "Simular Parcelas de uma compra")
    @GetMapping("/simular-parcelas/{id}")
    public ResponseEntity<ListaParcelasCompraDTO> simularBuscarPorId(@PathVariable("id") Long id) {
        ListaParcelasCompraDTO listaParcelasCompraDTO = service.listarParcelasCompra(id);
        return ResponseEntity.ok(listaParcelasCompraDTO);
    }

    @Operation(summary = "Cadastra um Simulacao Compra Parcelas")
    @PostMapping()
    public ResponseEntity<Void> insert(@RequestBody SimulacaoCompraParcelasRequest request) {
        Long perfilId = service.cadastrarSimulacaoCompraParcelas(request).getSimulacaoCompraId();
        return ResponseEntity.created(UriLocationUtil.buildLocationUri(perfilId)).build();
    }

    @Operation(summary = "Editar um Simulacao Compra Parcelas")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Long id, @RequestBody SimulacaoCompraParcelasRequest request) {
        service.editarSimulacaoCompraParcelas(id, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Simular Parcelas de uma compra")
    @PatchMapping("/efetivar-compra/{id}")
    public ResponseEntity<?> efetivarCompra(@PathVariable("id") Long id) {
        service.efetivarCompra(id);
        return ResponseEntity.noContent().build();
    }
}
