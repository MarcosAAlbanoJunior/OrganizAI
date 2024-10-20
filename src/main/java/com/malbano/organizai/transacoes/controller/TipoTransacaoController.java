package com.malbano.organizai.transacoes.controller;

import com.malbano.organizai.shared.util.UriLocationUtil;
import com.malbano.organizai.transacoes.dto.TipoTransacaoRequest;
import com.malbano.organizai.transacoes.entity.TipoTransacao;
import com.malbano.organizai.transacoes.service.TipoTransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-transacao")
public class TipoTransacaoController {

    TipoTransacaoService service;

    @Autowired
    public TipoTransacaoController(TipoTransacaoService service) {
        this.service = service;
    }

    @Operation(summary = "Procurar Tipo Transacao por Id")
    @GetMapping("/{id}")
    public ResponseEntity<TipoTransacao> buscarPorId(@PathVariable("id") Long id) {
        TipoTransacao statusSimulacao = service.buscarTipoTransacaoPorId(id);
        return ResponseEntity.ok(statusSimulacao);
    }

    @Operation(summary = "Listar Status Simulacoes")
    @GetMapping
    public ResponseEntity<List<TipoTransacao>> listar() {
        List<TipoTransacao> statusSimulacao = service.listarTipoTransacao();
        return ResponseEntity.ok(statusSimulacao);
    }

    @Operation(summary = "Cadastra um Tipo Transacao")
    @PostMapping()
    public ResponseEntity<Void> insert(@RequestBody TipoTransacaoRequest request) {
        Long perfilId = service.criarTipoTransacao(request).getTipoTransacaoId();
        return ResponseEntity.created(UriLocationUtil.buildLocationUri(perfilId)).build();
    }

    @Operation(summary = "Editar um Tipo Transacao")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Long id, @RequestBody TipoTransacaoRequest request) {
        service.editarTipoTransacao(id, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletar um Tipo Transacao")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.deletarTipoTransacao(id);
        return ResponseEntity.accepted().build();
    }
}
