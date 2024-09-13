package com.malbano.organizai.simulacao.compra.controller;

import com.malbano.organizai.shared.util.UriLocationUtil;
import com.malbano.organizai.simulacao.compra.dto.StatusSimulacaoRequest;
import com.malbano.organizai.simulacao.compra.entity.StatusSimulacao;
import com.malbano.organizai.simulacao.compra.service.StatusSimulacaoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status-simulacao")
public class StatusSimulacaoController {

    StatusSimulacaoService service;

    @Autowired
    public StatusSimulacaoController(StatusSimulacaoService service) {
        this.service = service;
    }

    @Operation(summary = "Procurar Status Simulacao por Id")
    @GetMapping("/{id}")
    public ResponseEntity<StatusSimulacao> buscarPorId(@PathVariable("id") Long id) {
        StatusSimulacao statusSimulacao = service.buscarStatusSimulacaoPorId(id);
        return ResponseEntity.ok(statusSimulacao);
    }

    @Operation(summary = "Listar Status Simulacoes")
    @GetMapping
    public ResponseEntity<List<StatusSimulacao>> listar() {
        List<StatusSimulacao> statusSimulacao = service.listarStatusSimulacao();
        return ResponseEntity.ok(statusSimulacao);
    }

    @Operation(summary = "Cadastra um Status Simulacao")
    @PostMapping()
    public ResponseEntity<Void> insert(@RequestBody StatusSimulacaoRequest request) {
        Long perfilId = service.cadastrarStatusSimulacao(request).getStatusSimulacaoId();
        return ResponseEntity.created(UriLocationUtil.buildLocationUri(perfilId)).build();
    }

    @Operation(summary = "Editar um Status Simulacao")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Long id, @RequestBody StatusSimulacaoRequest request) {
        service.editarStatusSimulacao(id, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletar um Status Simulacao")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.deletarStatusSimulacao(id);
        return ResponseEntity.accepted().build();
    }
}
