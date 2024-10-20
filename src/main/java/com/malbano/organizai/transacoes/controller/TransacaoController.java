package com.malbano.organizai.transacoes.controller;

import com.malbano.organizai.shared.util.UriLocationUtil;
import com.malbano.organizai.simulacao.compra.dto.ListaParcelasCompraDTO;
import com.malbano.organizai.transacoes.dto.TransacaoVariavelRequest;
import com.malbano.organizai.transacoes.entity.TransacaoVariavel;
import com.malbano.organizai.transacoes.service.TransacaoVariavelService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacao-variavel")
public class TransacaoController {

    TransacaoVariavelService service;

    @Autowired
    public TransacaoController(TransacaoVariavelService service) {
        this.service = service;
    }

    @Operation(summary = "Procurar Transacao Variavel por Id")
    @GetMapping("/{id}")
    public ResponseEntity<TransacaoVariavel> buscarPorId(@PathVariable("id") Long id) {
        TransacaoVariavel statusSimulacao = service.buscarTransacaoVariavelPorId(id);
        return ResponseEntity.ok(statusSimulacao);
    }

    //TO DO:Listar a partit de um id de usuario
    @Operation(summary = "Listar Transacoes Variaveis")
    @GetMapping
    public ResponseEntity<List<TransacaoVariavel>> listar() {
        List<TransacaoVariavel> statusSimulacao = service.listarTransacaoVariavel();
        return ResponseEntity.ok(statusSimulacao);
    }


    @Operation(summary = "Cadastra um Transacao Variavel")
    @PostMapping()
    public ResponseEntity<Void> insert(@RequestBody TransacaoVariavelRequest request) {
        Long perfilId = service.salvarTransacaoVariavel(request).getTransacaoId();
        return ResponseEntity.created(UriLocationUtil.buildLocationUri(perfilId)).build();
    }

    @Operation(summary = "Editar um Transacao Variavel")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Long id, @RequestBody TransacaoVariavelRequest request) {
        service.editarTransacaoVariavel(id, request);
        return ResponseEntity.noContent().build();
    }

}
